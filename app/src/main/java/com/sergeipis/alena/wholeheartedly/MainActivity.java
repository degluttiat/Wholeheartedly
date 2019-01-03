package com.sergeipis.alena.wholeheartedly;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        //toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        setClickListeners();
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
