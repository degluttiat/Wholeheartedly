package com.sergeipis.alena.wholeheartedly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;

import java.util.List;


public class QuizActivity extends AppCompatActivity
        implements View.OnClickListener, PurchasesUpdatedListener {

    private ViewPager mViewPager;
    private ImageView imagePrev;
    private ImageView imageNext;
    private TextView textViewSend;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private SharedPreferences mShPref;
    private int currentPosition;
    private TextView mTextView;

    private BillingClient mBillingClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mShPref = getApplicationContext().getSharedPreferences(MainActivity.SHPREF_PRICES, MODE_PRIVATE);
        mTextView = findViewById(R.id.section_label);
        setToolBar();
        setViewsAndListeners();
        setViewPager();
        setBilling();
    }

    private void setBilling() {
        mBillingClient = BillingClient.newBuilder(this).setListener(this).build();
        mBillingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@BillingClient.BillingResponse int responseCode) {
                if (responseCode == BillingClient.BillingResponse.OK) {
                    // The billing client is ready. You can query purchases here.
                    onBillingConnected();
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        });
    }

    private void onBillingConnected() {
        Log.d("ZAQ", "Biling connected");
        //testPurchase();
    }

    //PurchasesUpdatedListener
    @Override
    public void onPurchasesUpdated(int responseCode, @Nullable List<Purchase> purchases) {
        Log.d("ZAQ", "onPurchasesUpdated, responseCode: " + responseCode);
        if (purchases != null) {
            for (Purchase purchase : purchases) {
                Log.d("ZAQ", " Product ID: " + purchase.getSku());
                Log.d("ZAQ", " Order ID: " + purchase.getOrderId());
                Log.d("ZAQ", " Purchase time: " + purchase.getPurchaseTime());
                sendMail(purchase.getSku(), purchase.getOrderId());
            }
        }

    }

    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.quiz_);
    }

    private void setViewsAndListeners() {
        imagePrev = findViewById(R.id.quizButtonPrev);
        imageNext = findViewById(R.id.quizButtonNext);
        textViewSend = findViewById(R.id.quizButtonSend);

        imagePrev.setOnClickListener(this);
        imageNext.setOnClickListener(this);
        textViewSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.quizButtonPrev:
                mViewPager.arrowScroll(View.FOCUS_LEFT);
                break;
            case R.id.quizButtonNext:
                mViewPager.arrowScroll(View.FOCUS_RIGHT);
                break;
            case R.id.quizButtonSend:
                onBtnSendClicked();
                break;
        }
    }

    private void saveTextToShPref(int currentItem, String text) {
        SharedPreferences.Editor editor = mShPref.edit();
        editor.putString("q" + currentItem, text);
        editor.apply();
    }

    private QuestionFragment getCurrentFragment() {
        int currentItem = mViewPager.getCurrentItem();
        return (QuestionFragment) mSectionsPagerAdapter
                .instantiateItem(mViewPager, currentItem);
    }

    private void onBtnSendClicked() {
        QuestionFragment fragment = getCurrentFragment();
        String selectedProductId = fragment.getSelectedProduct();

        BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                .setSku(selectedProductId)
                .setType(BillingClient.SkuType.INAPP) // SkuType.SUB for subscription
                .build();
        int responseCode = mBillingClient.launchBillingFlow(this, flowParams);
        Log.d("ZAQ", "responseCode: " + responseCode);
    }

    private void sendMail(String sku, String orderId) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                getString(R.string.mailto), getString(R.string.email), null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.mail_subject));
        emailIntent.putExtra(Intent.EXTRA_TEXT, getAnswersText(sku, orderId));
        startActivity(Intent.createChooser(emailIntent, getString(R.string.chooser_intent_title)));
    }

    private void setViewPager() {
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (currentPosition != 19 && currentPosition != 0) {
                    QuestionFragment questionFragment = (QuestionFragment) mSectionsPagerAdapter
                            .instantiateItem(mViewPager, currentPosition);
                    String text = questionFragment.getEditTextString();
                    saveTextToShPref(currentPosition -1, text);
                }
                currentPosition = position;

                switch (position) {
                    case 0:
                        imagePrev.setVisibility(View.INVISIBLE);
                        mTextView.setVisibility(View.GONE);
                        break;
                    case 1:
                        imagePrev.setVisibility(View.VISIBLE);
                        mTextView.setVisibility(View.VISIBLE);
                        break;
                    case 18:
                        imageNext.setVisibility(View.VISIBLE);
                        textViewSend.setVisibility(View.INVISIBLE);
                        break;
                    case 19:
                        imageNext.setVisibility(View.INVISIBLE);
                        textViewSend.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private String getAnswersText(String sku, String orderId) {
        String[] answers = new String[18];
        for (int i = 0; i < answers.length; i++) {
            answers[i] = mShPref.getString("q" + i, "Null");
        }
        QuestionFragment fragment = getCurrentFragment();


        return "Option: " + fragment.getSelectedOptionText() + "\nName: \n" + answers[0] + "\nProfession: \n" + answers[1]
                + "\nSkills: \n" + answers[2] +
                "\nHobby: \n" + answers[3] + "\nLanguages s/he knows: \n" + answers[4] +
                "\nA sphere s/he is an expert in: \n" + answers[5] + "\nFavorite book: \n" + answers[6] +
                "\nFavorite movie: \n" + answers[7] + "\nFavorite sport activity: \n" + answers[8] +
                "\nFavorite meal: \n" + answers[9] + "\nPersonality traits that you appreciate: \n" + answers[10] +
                "\nEvents and recollections: \n" + answers[11] + "\nGeographic location: \n" + answers[12] +
                "\nOutlook: \n" + answers[13] + "\nGood or bad habits: \n" + answers[14] +
                "\nHow has that person impacted you? \n" + answers[15] +
                "\nSimilarities or differences between you and that person: \n" + answers[16] +
                "\nWhat would you like to wish to your dear person? \n" + answers[17] +
                "\nProduct ID user paid for: \n" + sku +
                "\nOrder ID: \n" + orderId;
    }
}
