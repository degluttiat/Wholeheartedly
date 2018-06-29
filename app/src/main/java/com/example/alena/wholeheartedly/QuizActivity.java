package com.example.alena.wholeheartedly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private Button buttonPrev;
    private Button buttonNext;
    private Button buttonSend;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private SharedPreferences mShPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mShPref = getApplicationContext().getSharedPreferences("shpref", MODE_PRIVATE);
        setToolBar();
        setViewsAndListeners();
        setViewPager();
    }

    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Quiz");
    }

    private void setViewsAndListeners() {
        buttonPrev = findViewById(R.id.quizButtonPrev);
        buttonNext = findViewById(R.id.quizButtonNext);
        buttonSend = findViewById(R.id.quizButtonSend);

        buttonPrev.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
        buttonSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.quizButtonPrev:
                mViewPager.arrowScroll(View.FOCUS_LEFT);
                break;
            case R.id.quizButtonNext:
                onBtnNextClicked();
                break;
            case R.id.quizButtonSend:
                onBtnSendClicked();
                break;
        }
    }

    private void onBtnNextClicked() {
        QuestionFragment fragment = getCurrentFragment();
        String text = fragment.getEditTextString();
        saveTextToShPref(mViewPager.getCurrentItem(), text);
        mViewPager.arrowScroll(View.FOCUS_RIGHT);
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
        // TODO Save text when user change fragment by swipe
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                getString(R.string.mailto), getString(R.string.email), null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.mail_subject));
        emailIntent.putExtra(Intent.EXTRA_TEXT, getAnswersText());
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
                switch (position) {
                    case 0:
                        buttonPrev.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        buttonPrev.setVisibility(View.VISIBLE);
                        break;
                    case 17:
                        buttonNext.setVisibility(View.VISIBLE);
                        buttonSend.setVisibility(View.INVISIBLE);
                        break;
                    case 18:
                        buttonNext.setVisibility(View.INVISIBLE);
                        buttonSend.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private String getAnswersText() {
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
                "\nWhat would you like to wish to your dear person? \n" + answers[17];
    }


}
