package com.sergeipis.alena.wholeheartedly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, PurchasesUpdatedListener {

    public static final String SHPREF_PRICES = "Prices";
    public static final String QUATRAINS_1 = "2_3_quatrains";
    public static final String QUATRAINS_2 = "4_6_quatrains";
    public static final String QUATRAINS_3 = "7_10_quatrains";

    private BillingClient mBillingClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        //toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        setClickListeners();

        setBilling();
    }

    private void setBilling() {
        mBillingClient = BillingClient.newBuilder(this)
                .enablePendingPurchases()
                .setListener(this).build();
        mBillingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
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
//        Log.d("ZAQ", "Biling connected");
        getPrices();

        Purchase.PurchasesResult purchasesResult = mBillingClient.queryPurchases(BillingClient.SkuType.INAPP);
        for (Purchase purchase : purchasesResult.getPurchasesList()) {
//            Log.d("ZAQ", " Product ID: " + purchase.getSku());
//            Log.d("ZAQ", " Order ID: " + purchase.getOrderId());
//            Log.d("ZAQ", " Purchase time: " + purchase.getPurchaseTime());
            acknowledgePurchase(purchase);
            consumePurchase(purchase);
        }
    }

    private void consumePurchase(final Purchase purchase) {

        ConsumeResponseListener listener = new ConsumeResponseListener() {
            @Override
            public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
//                Log.d("ZAQ", "onConsumePurchaseResponse: " + billingResult.getResponseCode());
            }
        };

        ConsumeParams consumeParams =
                ConsumeParams.newBuilder()
                        .setPurchaseToken(purchase.getPurchaseToken())
                        .build();

        mBillingClient.consumeAsync(consumeParams, listener);
    }

    private void acknowledgePurchase(final Purchase purchase) {
        AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener = new AcknowledgePurchaseResponseListener() {
            @Override
            public void onAcknowledgePurchaseResponse(BillingResult billingResult) {
                //sendMail(purchase.getSku(), purchase.getOrderId());
//                Log.d("ZAQ", "onAcknowledgePurchaseResponse: " + billingResult.getResponseCode());
            }
        };

        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
            if (!purchase.isAcknowledged()) {
                AcknowledgePurchaseParams acknowledgePurchaseParams =
                        AcknowledgePurchaseParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();
                mBillingClient.acknowledgePurchase(acknowledgePurchaseParams,
                        acknowledgePurchaseResponseListener);
            }
        }
    }

    private void getPrices() {
        List<String> skuList = new ArrayList<>();
        skuList.add(QUATRAINS_1);
        skuList.add(QUATRAINS_2);
        skuList.add(QUATRAINS_3);
        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
        params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
        mBillingClient.querySkuDetailsAsync(params.build(),
                new SkuDetailsResponseListener() {
                    @Override
                    public void onSkuDetailsResponse(BillingResult billingResult,
                                                     List<SkuDetails> skuDetailsList) {
                        onPricesReceived(skuDetailsList);
                    }
                });
    }

    private void onPricesReceived(List<SkuDetails> skuDetailsList) {
        SharedPreferences.Editor editor = getSharedPreferences(SHPREF_PRICES, MODE_PRIVATE).edit();
        for (SkuDetails skuDetails : skuDetailsList) {
            /*Log.d("ZAQ", "Name: " + skuDetails.getTitle() + " Price: " + skuDetails.getPrice()
                    + " " + skuDetails.getType());*/
            editor.putString(skuDetails.getSku(), skuDetails.getPrice());
            editor.putString(skuDetails.getSku() + "json", skuDetails.getOriginalJson());
        }
        editor.apply();
    }

    //PurchasesUpdatedListener
    @Override
    public void onPurchasesUpdated(BillingResult billingResult, @Nullable List<Purchase> purchases) {
//        Log.d("ZAQ", "onPurchasesUpdated");
    }

    private void setClickListeners() {
        findViewById(R.id.start_button).setOnClickListener(this);
        findViewById(R.id.examples_button).setOnClickListener(this);
        findViewById(R.id.about_button).setOnClickListener(this);
        findViewById(R.id.prices_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_button:
                startActivity(new Intent(MainActivity.this, QuizActivity.class));
                break;
            case R.id.examples_button:
                startActivity(new Intent(MainActivity.this, ExamplesActivity.class));
                break;
            case R.id.about_button:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                break;
            case R.id.prices_button:
                startActivity(new Intent(MainActivity.this, PricesActivity.class));
                break;
        }
    }
}
