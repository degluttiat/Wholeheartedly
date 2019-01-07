package com.sergeipis.alena.wholeheartedly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


public class PricesActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prices);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle(R.string.prices_);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        setViews();
        setClickListeners();

        getPrices();
    }

    private void setViews() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
    }

    private void getPrices() {
        SharedPreferences pref = getSharedPreferences(MainActivity.SHPREF_PRICES, MODE_PRIVATE);
        String price1 = pref.getString(MainActivity.QUATRAINS_1, "");
        String price2 = pref.getString(MainActivity.QUATRAINS_2, "");
        String price3 = pref.getString(MainActivity.QUATRAINS_3, "");

        button1.setText(String.format("%s - %s", button1.getText(), price1));
        button2.setText(String.format("%s - %s", button2.getText(), price2));
        button3.setText(String.format("%s - %s", button3.getText(), price3));
    }

    private void setClickListeners() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        SharedPreferences shPref = getApplicationContext().getSharedPreferences(MainActivity.SHPREF_PRICES, MODE_PRIVATE);
        SharedPreferences.Editor editor = shPref.edit();

        switch (view.getId()) {
            case R.id.button1:
                editor.putInt(getString(R.string.key_price_tag), 1);
                break;
            case R.id.button2:
                editor.putInt(getString(R.string.key_price_tag), 2);
                break;
            case R.id.button3:
                editor.putInt(getString(R.string.key_price_tag), 3);
                break;
        }
        editor.apply();

        Intent intent = new Intent(PricesActivity.this, QuizActivity.class);
        startActivity(intent);
    }
}
