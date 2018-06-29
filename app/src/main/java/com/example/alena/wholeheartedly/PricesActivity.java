package com.example.alena.wholeheartedly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PricesActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prices);

        setClickListeners();
    }

    private void setClickListeners() {
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        SharedPreferences shPref = getApplicationContext().getSharedPreferences("shpref", MODE_PRIVATE);
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
