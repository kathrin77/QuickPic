package com.example.quickpic;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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
                btnAnswerPressed(view);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAnswerPressed(view);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAnswerPressed(view);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAnswerPressed(view);
            }
        });

        loadGame('m'); //es soll vorerst automatisch Berge gestartet werden.


    }

    /**
     * starts a new game
     * @param topic
     */
    public void loadGame(char topic) {
        switch(topic) {
            case 'm': loadNewMountainGame();
            //case 'f': loadFishGame();
            //case 't': loadTreeGame();
            default: loadNewMountainGame();
        }

    }

    /**
     * Creates initial start screen for mountain game
     */

    public void loadNewMountainGame() {

        //New game: set textviews
        tvLives.setText("Lives: "+game.getLives());
        tvPoints.setText("Points: "+game.getPoints());

        //Start new level:
        startNewLevel(game.getLevel());
        //Start new round:
        startNewRound(game.getRound());

    }

    /**
     * Starts a new level: sets the level textview and question for this level
     * @param level
     */
    public void startNewLevel(int level) {
        game.setLevel(level);
        tvLevel.setText("Level: "+game.getLevel());
        tvQuestion.setText(game.getMountainQuestions(game.getLevel()));
    }

    /**
     * starts a new round:
     * - sets Round to textview
     * - gets a random picture, sets correct answer to a random button, sets 3 wrong answers to the other buttons
     * @param round
     */
    public void startNewRound(int round) {

        game.setRound(round);
        tvRound.setText("Round: "+game.getRound());

        //get random id for this round:
        game.id = getRandomId();
        //get the picture:
        imgQuiz.setImageDrawable(getResources().getDrawable(getRandomPicture(game.id)));

        //New arraylist, fill with all possible ids (0-14):
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i < game.mountains.length; i++) {
            index.add(i);
        }
        //remove index with correct answer
        index.remove(game.id);
        //generate 3 wrong answer-ids from the remaining index
        int wrong1 = index.remove((int)(Math.random()*index.size()));
        int wrong2 = index.remove((int)(Math.random()*index.size()));
        int wrong3 = index.remove((int)(Math.random()*index.size()));

        //set 1 correct and 3 wrong answers to the buttons:
        //make sure, that the correct answer does not always appear on the same button: switch
        int btnNumber = (int) (Math.random() * 4);
        switch (btnNumber) {
            case 0:
                btn1.setText(game.mountains[game.id][game.getLevel()]);
                btn2.setText(game.mountains[wrong1][game.getLevel()]);
                btn3.setText(game.mountains[wrong2][game.getLevel()]);
                btn4.setText(game.mountains[wrong3][game.getLevel()]);
                break;
            case 1:
                btn1.setText(game.mountains[wrong3][game.getLevel()]);
                btn2.setText(game.mountains[game.id][game.getLevel()]);
                btn3.setText(game.mountains[wrong1][game.getLevel()]);
                btn4.setText(game.mountains[wrong2][game.getLevel()]);
                break;
            case 2:
                btn1.setText(game.mountains[wrong2][game.getLevel()]);
                btn2.setText(game.mountains[wrong3][game.getLevel()]);
                btn3.setText(game.mountains[game.id][game.getLevel()]);
                btn4.setText(game.mountains[wrong1][game.getLevel()]);
                break;
            case 3:
                btn1.setText(game.mountains[wrong1][game.getLevel()]);
                btn2.setText(game.mountains[wrong2][game.getLevel()]);
                btn3.setText(game.mountains[wrong3][game.getLevel()]);
                btn4.setText(game.mountains[game.id][game.getLevel()]);
                break;
        }

    }

    /**
     * get a random number between 0 and 14 to be used as id for the current round
     * @return the new id for this round
     */
    public int getRandomId() {
        //eine Zufallszahl zwischen 0 und 14:
        int current_id = (int) (Math.random() * game.mountains.length);
        return current_id;
    }

    /**
     * get the resource id from the picture id
     * @param id
     * @return resource id (integer)
     */
    public int getRandomPicture(int id) {

        //first string from array = img_..., position [?][0]
        game.img_resource = game.mountains[id][0];
        //int of the img_resource
        int resource_ID = getResources().getIdentifier(game.img_resource,"drawable", getPackageName());
        return resource_ID;
    }

    public void btnAnswerPressed(View view) {

        //set view to the clicked button
        final Button clicked = (Button) view;
        //get the string from the clicked button
        String buttontext = clicked.getText().toString();

        if (checkAnswer(game.id, buttontext)) {
            game.points++; //update points
            tvPoints.setText("Points: "+game.getPoints());
            game.round++; //update round

        } else {
            game.round++; //only update round
        }
        //continue the game:
        if (game.getRound() >5) { //last round per level is over
            game.level++;
            if (game.getLevel() >3) { //all 3 levels done: end game
                Intent intent = new Intent(this, HighscoreActivity.class);
                intent.putExtra("Points", game.points);
                startActivity(intent);
            } else { //continue with next level
                game.setRound(1); //start at round 1
                startNewLevel(game.getLevel()); //start new level
                startNewRound(game.getRound()); //start new round
            }
        } else { //continue with next round
            startNewRound(game.getRound());
        }
    }

    public boolean checkAnswer(int id, String btnText) {
        boolean question = false;
        String correctAnswer = game.mountains[game.id][game.getLevel()];
        if (correctAnswer.equals(btnText)) {
            question = true;
        }
        return question;
    }

}
