package com.example.alena.wholeheartedly;

import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.start_button);
        Button examplesButton = findViewById(R.id.examples_button);
        Button aboutButton = findViewById(R.id.about_button);
        Button pricesButton = findViewById(R.id.prices_button);

        startButton.setOnClickListener(this);
        examplesButton.setOnClickListener(this);
        aboutButton.setOnClickListener(this);
        pricesButton.setOnClickListener(this);
        //startButton.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0xFFAA0000));
        examplesButton.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0xFFAA0000));
        aboutButton.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0xFFAA0000));
        pricesButton.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0xFFAA0000));

    }

    @Override
    public void onClick(View view) {
        switch(view.getId())  {
            case  R.id.start_button:
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
                break;

            case  R.id.examples_button:
                Intent intent2 = new Intent(MainActivity.this, ExamplesActivity.class);
                startActivity(intent2);
                break;

            case  R.id.about_button:
                Intent intent3 = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent3);
                break;

            case  R.id.prices_button:
                Intent intent4 = new Intent(MainActivity.this, PricesActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
