package com.example.alena.wholeheartedly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PricesActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prices);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case  R.id.button1:
                QuizActivity.pricesTag = 1;
                break;
            case  R.id.button2:
                QuizActivity.pricesTag = 2;
                break;
            case  R.id.button3:
                QuizActivity.pricesTag = 3;
                break;
        }
        Intent intent = new Intent(PricesActivity.this, QuizActivity.class);
        startActivity(intent);
    }
}
