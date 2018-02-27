package com.example.quickpic;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.view.ViewGroup.*;

public class QuizActivity extends AppCompatActivity {

    //LOG:
    public static final String LOGTAG = "Quickpic"; //for logcat

    Button btn1, btn2, btn3, btn4;
    TextView tvLevel, tvPoints, tvRound, tvQuestion, tvTime;
    ImageView imgQuiz;
    LinearLayout livesLayout;
    FrameLayout barTime;
    //float screenDensity;
    LayoutParams lpTime;

    // Handler to delay the next question, to have time for changing the button color (correct: green, incorrect: red)
    Handler doItLater = new Handler();
    // Handler for timeout
    Handler timeoutHandler = new Handler();
    // seconds till timeout
    int timeout;
    // adaptable value for time-bar size:
    int screenSizer;
    // CountdownTimer for countdown till timeout
    CountDownTimer timer;

    SoundPool player;
    int rightSound;
    int falseSound;

    Quickpic game = new Quickpic();

    //New arraylist, fill with all possible ids (0-14):
    ArrayList<Integer> possibleAnswers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        tvLevel = (TextView) findViewById(R.id.tvLevel);
        tvRound = (TextView) findViewById(R.id.tvRound);
        tvPoints = (TextView) findViewById(R.id.tvPoints);
        tvTime = (TextView) findViewById(R.id.tvTime);
        imgQuiz = (ImageView) findViewById(R.id.imgQuiz);
        livesLayout = (LinearLayout) findViewById(R.id.livesLayout);

        player = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        rightSound = player.load(this, R.raw.right_answer, 1);
        falseSound = player.load(this, R.raw.wrong_answer, 1);

        timeout = 10500;
        screenSizer = 12;
        barTime = (FrameLayout) findViewById(R.id.barTime);
        //screenDensity = getResources().getDisplayMetrics().density;
        //Log.d("Quickpic","screenDensity "+ screenDensity); //= 3.0
        lpTime = barTime.getLayoutParams();
        //lpTime.width = Math.round(screenDensity * (timeout/30));
        lpTime.width = (timeout/screenSizer);
        Log.d("Quickpic","lpTime.width initial "+lpTime.width);

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
        tvPoints.setText(getString(R.string.points) + game.getPoints());

        //Start new level:
        startNewLevel(game.getLevel(), game.topic);
        //Start new round:
        startNewRound(game.getRound());
    }

    /**
     * Starts a new level: sets the level textview and question for this level, based on the topic
     *
     * @param level
     * @param topic
     */
    public void startNewLevel(int level, char topic) {
        game.setLevel(level);
        tvLevel.setText(getString(R.string.level) + game.getLevel());
        tvQuestion.setText(game.getQuestion(topic, game.getLevel()));
        //int questionID =
        //String questionID = game.getQuestion(topic, game.getLevel());
        //tvQuestion.setText(getString(R.string.questionID));
        //tvQuestion.setText(getResources().getText());
        //                imgQuiz.setImageDrawable(getResources().getDrawable(getRandomPicture(game.id)));

    }

    /**
     * starts a new round:
     * - sets Round to textview
     * - gets a random picture, sets correct answer to a random button, sets 3 wrong answers to the other buttons
     * - sets Timeout, with each new round 500 ms less
     *
     * @param round
     */
    public void startNewRound(int round) {
        game.setRound(round);
        tvRound.setText(getString(R.string.round) + game.getRound());

        //Draw lives (hearts) at the beginning of every round according to no. in game.lives:
        drawLives(livesLayout);

        //get random id for this round (between 0 and 14): if id exists already, get new random id.
        int x = 0;
        do {
            x = getRandomId();
        } while (possibleAnswers.contains(x));
        game.id = x;
        possibleAnswers.add(game.id);

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
        int wrong1 = index.remove((int) (Math.random() * index.size()));
        int wrong2 = index.remove((int) (Math.random() * index.size()));
        int wrong3 = index.remove((int) (Math.random() * index.size()));

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

        timeout -= 500;
        resetTimeout();

    }

    /**
     * get a random number between 0 and 14 to be used as id for the current round
     *
     * @return the new id for this round
     */
    public int getRandomId() {
        //eine Zufallszahl zwischen 0 und 14:
        int current_id = (int) (Math.random() * game.answer.length);
        return current_id;
    }

    /**
     * get the resource id from the picture id
     *
     * @param id
     * @return resource id (integer)
     */
    public int getRandomPicture(int id) {
        //first string from array = img_..., position [?][0]
        game.img_resource = game.answer[id][0];

        //int of the img_resource
        int resource_ID = getResources().getIdentifier(game.img_resource, "drawable", getPackageName());
        return resource_ID;
    }

    /**
     * This method calls the GameOverActivity after a certain amount of time (int timeout)
     * and displays the current amount of ms (int timeout)
     */
    public void resetTimeout() {
        timeoutHandler.removeCallbacksAndMessages(null);
        timer = new CountDownTimer(timeout, 1000) {

            public void onTick(long millisUntilFinished) {
                tvTime.setText(millisUntilFinished / 1000 + "");
                lpTime.width = (int) (millisUntilFinished/screenSizer);
            }

            public void onFinish() {
                tvTime.setText("0");
                lpTime.width = (0);
            }
        };
        timer.start();

        //tvTime.setText(timeout+"");
        //lpTime.width = (timeout/screenSizer);
        Log.d("Quickpic","lpTime.width round: "+game.round+", "+lpTime.width);
        timeoutHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkGameOver(0);
            }
        }, timeout);
    }

    /**
     * This method evaluates the answers and continues the game to the next round/level or ends the game.
     *
     * @param view
     */

    public void btnAnswerPressed(View view) {

        timer.cancel();
        timeoutHandler.removeCallbacksAndMessages(null);

        //set view to the clicked button
        final Button clicked = (Button) view;
        //get the string from the clicked button
        String buttontext = clicked.getText().toString();

        //if correct, update points/rounds and change Button color to green, otherwise to red and just update rounds
        if (checkAnswer(game.id, buttontext)) {
            clicked.setBackgroundColor(GREEN);
            player.play(rightSound, 1, 1, 1, 0, 1);
            game.points++;
            tvPoints.setText(getString(R.string.tvPoints) + game.getPoints());
            game.round++;
        } else {
            clicked.setBackgroundColor(RED);
            player.play(falseSound, 1, 1, 1, 0, 1);
            if (checkAnswer(game.id, btn1.getText().toString())) {
                btn1.setBackgroundColor(GREEN);
            }
            if (checkAnswer(game.id, btn2.getText().toString())) {
                btn2.setBackgroundColor(GREEN);
            }
            if (checkAnswer(game.id, btn3.getText().toString())) {
                btn3.setBackgroundColor(GREEN);
            }
            if (checkAnswer(game.id, btn4.getText().toString())) {
                btn4.setBackgroundColor(GREEN);
            }
            game.round++;
            game.lives--;
            checkGameOver(game.lives);
        }

        doItLater.removeCallbacksAndMessages(null);

        if (game.lives != 0) {

            // delay further game to get time to see the changed button color
            doItLater.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // set button color back to default before showing new question
                    // clicked.setBackgroundResource(android.R.drawable.btn_default);
                    btn1.setBackgroundResource(android.R.drawable.btn_default);
                    btn2.setBackgroundResource(android.R.drawable.btn_default);
                    btn3.setBackgroundResource(android.R.drawable.btn_default);
                    btn4.setBackgroundResource(android.R.drawable.btn_default);

                /* continue the game with the next round.
                After 5 rounds, update level, start new level, start at round 1.
                After 3 levels, go to HighScoreAcitivity, save points.
                */
                    if (game.getRound() > 5) {
                        game.level++;
                        if (game.getLevel() > 3) {
                            Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
                            intent.putExtra("Points", game.points);
                            startActivity(intent);
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), R.string.new_level, Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER,0,0);
                            //give the toast a visible background color and a nice width:
                            View toastView = toast.getView();
                            toastView.setBackgroundColor(getResources().getColor(R.color.toast_background_color));
                            toastView.setMinimumWidth(800);
                            //toastView.setBackgroundResource(R.drawable.toast_background_color);
                            toast.show();
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
            }, 1000);
        }
    }

    /**
     * check the answer on the clicked button
     *
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

    public void checkGameOver(int lives) {
        timeoutHandler.removeCallbacksAndMessages(null);
        if (lives < 1) {
            Intent gameover = new Intent(this, GameOverActivity.class);
            startActivity(gameover);
        }
    }

    public void drawLives(LinearLayout livesLayout) {

        //clear livesLayout before drawing new hearts:
        livesLayout.removeAllViewsInLayout();

        //draw as many hearts as lives: create new imageview, set lives.png
        //set params to wrap_content, add to livesLayout
        for (int i = 0; i < game.lives; i++) {
            ImageView heart = new ImageView(this);
            heart.setImageResource(R.drawable.lives);
            LayoutParams layoutParams = (LayoutParams) new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            livesLayout.addView(heart, layoutParams);
        }

    }


}
