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
            default: loadNewMountainGame();
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

        //Neues Spiel, erste Runde: Level = 1, Punkte = 0, Leben = 3
        game.setLevel(1);
        game.setPoints(0);
        game.setLives(3);
        tvLives.setText("Lives: "+game.getLives());
        tvLevel.setText("Level: "+game.getLevel());
        tvPoints.setText("Points: "+game.getPoints());

        //Neues Level starten:
        startNewLevel(game.getLevel());
        //Neue Runde starten:
        game.setRound(1);
        tvRound.setText("Round: "+game.getRound());
        startNewRound(game.getRound());


    }

    /**
     * Starts a new level: sets the question for this level
     * @param level
     */

    public void startNewLevel(int level) {
        tvQuestion.setText(game.getMountainQuestions(game.getLevel()));
    }

    /**
     * starts a new round:
     * gets a random picture,
     * sets correct answer to a random button,
     * sets 3 random answers to the other buttons
     * @param round
     */
    public void startNewRound(int round) {

        //get random id for this round:
        game.random_id = getRandomId();
        //get the picture:
        imgQuiz.setImageDrawable(getResources().getDrawable(getRandomPicture(game.random_id)));

        //get the correct answer: method getCorrectAnswer
        //get 3 false answers: method getWrongAnswer
        //set the correct answer to random button: setText(getCorrectAnswer...)
        // set the false answers to the other 3 buttons
        //make sure, that the correct answer does not always appear on the same button: switch
        int btnNumber = (int) (Math.random() * (4-1) + 1);

        switch (btnNumber) {
            case 1:
                btn1.setText(getCorrectAnswer(game.random_id));
                btn2.setText(getWrongAnswer(game.random_id));
                btn3.setText(getWrongAnswer(game.random_id));
                btn4.setText(getWrongAnswer(game.random_id));
                break;
            case 2:
                btn1.setText(getWrongAnswer(game.random_id));
                btn2.setText(getCorrectAnswer(game.random_id));
                btn3.setText(getWrongAnswer(game.random_id));
                btn4.setText(getWrongAnswer(game.random_id));
                break;
            case 3:
                btn1.setText(getWrongAnswer(game.random_id));
                btn2.setText(getWrongAnswer(game.random_id));
                btn3.setText(getCorrectAnswer(game.random_id));
                btn4.setText(getWrongAnswer(game.random_id));
                break;
            case 4:
                btn1.setText(getWrongAnswer(game.random_id));
                btn2.setText(getWrongAnswer(game.random_id));
                btn3.setText(getWrongAnswer(game.random_id));
                btn4.setText(getCorrectAnswer(game.random_id));
                break;
        }

        //call method: buttonPressed...

    }

    /**
     * get a random number between 0 and 14 to be used as id for the current round
     * @return id
     */
    public int getRandomId() {
        //eine Zufallszahl zwischen 0 und 14:
        int id = (int) (Math.random() * 15);
        return id;
    }

    /**
     * get the resource id from the picture id
     * @param id
     * @return resource id (integer)
     */
    public int getRandomPicture(int id) {

        //erster string aus dem array = img_...
        game.img_resource = game.mountains[id][0];
        //int der img_resource
        int resID = getResources().getIdentifier(game.img_resource,"drawable", getPackageName());
        //img_resource als int setzen:
        return resID;
    }

    /**
     * get the answer to the picture id according to the current level
     * @param id
     * @return String answer
     */
    public String getCorrectAnswer(int id) {

        game.answer = game.mountains[id][game.level];
        return game.answer;

    }

    public String getWrongAnswer(int id) {

        int wrongid = getRandomId();
        if (wrongid != id) {
            game.answer = game.mountains[wrongid][game.level];
        } else {
            if (wrongid != 0) {
                game.answer = game.mountains[(wrongid-1)][game.level];
            } else {
                game.answer = game.mountains[(wrongid+1)][game.level];
            }
        }
        return game.answer;
    }





}
