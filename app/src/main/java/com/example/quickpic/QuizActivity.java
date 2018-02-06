package com.example.quickpic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4;
    TextView tvLevel, tvPoints, tvRound, tvQuestion, tvLives, tvTime;
    ImageView imgQuiz;

    Quickpic game = new Quickpic();

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
                //TODO
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

        loadGame('m'); //es soll vorerst automatisch Berge gestartet werden.


    }

    public void loadGame(char topic) {
        switch(topic) {
            case 'm': loadNewMountainGame();
            //case 'f': loadFishGame();
            //case 't': loadTreeGame();
            //default: startMountainGame();
        }

    }

    /**
     * Baut den Startbildschirm auf:
     * level, runde, punkte auf 1,1,0 gesetzt
     * Frage für diese Runde gesetzt
     * erstes Bild geladen
     * 4 Antworten geladen
     *
     */

    public void loadNewMountainGame() {

        //Level, Runde, Leben, Punkte setzen
        game.setLevel(1);
        tvLevel.setText("Level: "+game.getLevel());
        game.setPoints(0);
        tvPoints.setText("Points: "+game.getPoints());
        game.setRound(1);
        tvRound.setText("Round: "+game.getRound());
        game.setLives(3);
        tvLives.setText("Lives: "+game.getLives());

        tvQuestion.setText(game.getMountainQuestions(game.getLevel()));

        /*for (int i = game.getRound(); i < game.mountainTopics.size(); i++) {
            Object o = game.mountainTopics.get(i);

            imgQuiz.setImageResource(R.id.(toString(o[0]));
             //vorerst wird einfach mal das 1. Bild geholt
        }*/
    }

    //Methode: startnewLevel
    //Methode: startnewRound





}
