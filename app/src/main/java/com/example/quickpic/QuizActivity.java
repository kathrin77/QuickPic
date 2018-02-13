package com.example.quickpic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.ViewGroup.*;

public class QuizActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4;
    TextView tvLevel, tvPoints, tvRound, tvQuestion, tvLives, tvTime;
    ImageView imgQuiz;

    //für die Leben: ein Versuch, besser wohl mit bitmaps wie in Tutorial von Nora.
    //float measure = getResources().getDisplayMetrics().density;
    //FrameLayout barLives = (FrameLayout) findViewById(R.id.barLives);
    //LayoutParams lpBarLives = barLives.getLayoutParams();
    //lpBarLives.width = Math.round(measure * 300 * game.lives);

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

        loadGame();
    }

    /**
     * starts a new game, based on the topic passed on by the intent from startactivity
     */
    public void loadGame() {

        //get the selected topic from StartActivity and set game.topic:
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            game.setTopic(extras.getChar("TOPIC"));
        }
        //get the correct Array based on topic
        game.answer = game.getAnswer(game.topic);

        //New game: set textviews
        tvPoints.setText("Points: "+game.getPoints());

        //Start new level:
        startNewLevel(game.getLevel(), game.topic);
        //Start new round:
        startNewRound(game.getRound());
    }

    /**
     * Starts a new level: sets the level textview and question for this level, based on the topic
     * @param level
     * @param topic
     */
    public void startNewLevel(int level, char topic) {
        game.setLevel(level);
        tvLevel.setText("Level: "+game.getLevel());
        tvQuestion.setText(game.getQuestion(topic, game.getLevel()));
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
        //Leben werden bei jedem Rundenstart neu auf tvLives gesetzt.
        tvLives.setText("Lives: "+game.getLives());

        //get random id for this round (between 0 and 14):
        game.id = getRandomId();
        //get the picture:
        imgQuiz.setImageDrawable(getResources().getDrawable(getRandomPicture(game.id)));

        //New arraylist, fill with all possible ids (0-14):
        ArrayList<Integer> index = new ArrayList<>();

        for (int i = 0; i < game.answer.length; i++) {
            index.add(i);
        }
        //remove index with correct answer
        index.remove(game.id);
        //generate 3 wrong answer-ids from the remaining index
        int wrong1 = index.remove((int)(Math.random()*index.size()));
        int wrong2 = index.remove((int)(Math.random()*index.size()));
        int wrong3 = index.remove((int)(Math.random()*index.size()));

        //set answers, switch, so that the correct answer does not always appear on the same button
        int btnNumber = (int) (Math.random() * 4);

        switch (btnNumber) {
            case 0:
                btn1.setText(game.answer[game.id][game.getLevel()]);
                btn2.setText(game.answer[wrong1][game.getLevel()]);
                btn3.setText(game.answer[wrong2][game.getLevel()]);
                btn4.setText(game.answer[wrong3][game.getLevel()]);
                break;
            case 1:
                btn1.setText(game.answer[wrong3][game.getLevel()]);
                btn2.setText(game.answer[game.id][game.getLevel()]);
                btn3.setText(game.answer[wrong1][game.getLevel()]);
                btn4.setText(game.answer[wrong2][game.getLevel()]);
                break;
            case 2:
                btn1.setText(game.answer[wrong2][game.getLevel()]);
                btn2.setText(game.answer[wrong3][game.getLevel()]);
                btn3.setText(game.answer[game.id][game.getLevel()]);
                btn4.setText(game.answer[wrong1][game.getLevel()]);
                break;
            case 3:
                btn1.setText(game.answer[wrong1][game.getLevel()]);
                btn2.setText(game.answer[wrong2][game.getLevel()]);
                btn3.setText(game.answer[wrong3][game.getLevel()]);
                btn4.setText(game.answer[game.id][game.getLevel()]);
                break;
        }

    }

    /**
     * get a random number between 0 and 14 to be used as id for the current round
     * @return the new id for this round
     */
    public int getRandomId() {
        //eine Zufallszahl zwischen 0 und 14:
        int current_id = (int) (Math.random() * game.answer.length);
        return current_id;
    }

    /**
     * get the resource id from the picture id
     * @param id
     * @return resource id (integer)
     */
    public int getRandomPicture(int id) {
        //first string from array = img_..., position [?][0]
        game.img_resource = game.answer[id][0];

        //int of the img_resource
        int resource_ID = getResources().getIdentifier(game.img_resource,"drawable", getPackageName());
        return resource_ID;
    }

    /**
     * This method evaluates the answers and continues the game to the next round/level or ends the game.
     * @param view
     */

    public void btnAnswerPressed(View view) {
        //set view to the clicked button
        final Button clicked = (Button) view;
        //get the string from the clicked button
        String buttontext = clicked.getText().toString();

        //if correct, update points/rounds, otherwise just rounds
        if (checkAnswer(game.id, buttontext)) {
            game.points++;
            tvPoints.setText("Points: "+game.getPoints());
            game.round++;
        } else {
            game.round++;
            game.lives--;
            checkGameOver(game.lives);
        }
/*        continue the game with the next round.
        After 5 rounds, update level, start new level, start at round 1.
        After 3 levels, go to HighScoreAcitivity, save points.
        */
        if (game.getRound() >5) {
            game.level++;
            if (game.getLevel() >3) {
                Intent intent = new Intent(this, HighscoreActivity.class);
                intent.putExtra("Points", game.points);
                startActivity(intent);
            } else {
                game.setRound(1);
                if (game.lives < 3) {
                    game.lives++;
                }
                startNewLevel(game.getLevel(), game.topic);
                startNewRound(game.getRound());
            }
        } else {
            startNewRound(game.getRound());
        }
    }

    /**
     * check the answer on the clicked button
     * @param id
     * @param btnText
     * @return true or false
     */

    public boolean checkAnswer(int id, String btnText) {
        boolean question = false;
        String correctAnswer = game.answer[game.id][game.getLevel()];
        if (correctAnswer.equals(btnText)) {
            question = true;
        }
        return question;
    }

    public void checkGameOver (int lives) {
        if (lives < 1) {
            Intent gameover = new Intent(this, GameOverActivity.class);
            startActivity(gameover);
        }
    }


}
