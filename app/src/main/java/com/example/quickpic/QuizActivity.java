package com.example.quickpic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class QuizActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4;
    TextView tvLevel, tvPoints, tvRound, tvQuestion, tvLives, tvTime;
    ImageView imgQuiz;

    int points = 0;
    int round = 0;
    int level = 0;
    int lives = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        tvLevel = (TextView) findViewById(R.id.tvLevel);
        tvRound = (TextView) findViewById(R.id.tvRound);
        tvPoints = (TextView) findViewById(R.id.tvPoints);
        tvLives = (TextView) findViewById(R.id.tvLives);
        tvTime = (TextView) findViewById(R.id.tvTime);

        imgQuiz = (ImageView) findViewById(R.id.imgQuiz);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Methode aufrufen
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });


    }
}
