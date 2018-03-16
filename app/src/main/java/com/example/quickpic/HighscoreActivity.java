package com.example.quickpic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HighscoreActivity extends AppCompatActivity {

    Button btnStartseite;
    TextView tVcurrentPoints;
    TextView tVHighscore;
    LinearLayout LlNameInput;
    Button btnSaveName;
    String KEY = "HIGHSCORE";
    String KEY_NAME = "HIGHSCORE_NAME";

    SharedPreferences preferences;
    SharedPreferences.Editor preferencesEditor;

    int currentPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        btnStartseite = findViewById(R.id.btnStartseite);
        btnStartseite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
            }
        });

        tVcurrentPoints = findViewById(R.id.tVcurrentPoints);
        tVHighscore = findViewById(R.id.tVHighscore);

        preferences = this.getSharedPreferences("GAME", MODE_PRIVATE);
        preferencesEditor = preferences.edit();
        preferencesEditor.apply();

        currentPoints = getIntent().getIntExtra("Points", 0);

        showHighscore(1);
        showHighscore(2);
        showHighscore(3);
        showPoints();

        LlNameInput = findViewById(R.id.LlNameInput);
        LlNameInput.setVisibility(View.GONE);

        if (currentPoints > getHighscore(3)) {
            LlNameInput.setVisibility(View.VISIBLE);
        }

        // Debugging
        String get1 = "1: " + getHighscore(1);
        String get2 = "2: " + getHighscore(2);
        String get3 = "3: " + getHighscore(3);
        Log.d("get1", get1);
        Log.d("get2", get2);
        Log.d("get3", get3);

        btnSaveName = findViewById(R.id.btnSaveName);
        btnSaveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeHighscore(currentPoints);
                showHighscore(1);
                showHighscore(2);
                showHighscore(3);
                LlNameInput.setVisibility(View.GONE);

                //Debugging
                String top1 = "top1: " + preferences.getString(KEY_NAME+"1","") + " " + preferences.getInt(KEY+"1",0);
                String top2 = "top2: " + preferences.getString(KEY_NAME+"2","") + " " + preferences.getInt(KEY+"2",0);
                String top3 = "top3: " + preferences.getString(KEY_NAME+"3","") + " " + preferences.getInt(KEY+"3",0);
                Log.d("top1",top1);
                Log.d("top2", top2);
                Log.d("top3", top3);
            }
        });

    }

    /**
     * get the points of highscore list on given position keynumber from shared preferences
     *
     * @param keynumber position in highscore list
     * @return highscore points
     */
    private int getHighscore(int keynumber) {

        return preferences.getInt(KEY+keynumber,0);
    }

    /**
     * get the name of highscore list on given position keynumber from shared preferences
     *
     * @param keynumber position in highscore list
     * @return highscore name
     */
    private String getHighscoreName(int keynumber) {
        return preferences.getString(KEY_NAME+keynumber, "");
    }

    /**
     * writes names and points of top 3 highscores to shared preferences
     *
     * @param highscore new highscore points to add
     */
    private void writeHighscore(int highscore) {
        EditText eTName = findViewById(R.id.eTName);
        String name = eTName.getText().toString().trim();
        String name1 = preferences.getString(KEY_NAME+"1","");
        String name2 = preferences.getString(KEY_NAME+"2","");
        String name3 = preferences.getString(KEY_NAME+"3","");
        int topscore1 = preferences.getInt(KEY+"1",0);
        int topscore2 = preferences.getInt(KEY+"2",0);
        int topscore3 = preferences.getInt(KEY+"3",0);
        if (highscore >= topscore1) {
            preferencesEditor.putInt(KEY+"1",highscore);
            preferencesEditor.putString(KEY_NAME+"1", name);
            preferencesEditor.putInt(KEY+"2",topscore1);
            preferencesEditor.putString(KEY_NAME+"2", name1);
            preferencesEditor.putInt(KEY+"3",topscore2);
            preferencesEditor.putString(KEY_NAME+"3", name2);
        } else if (highscore >= topscore2) {
            preferencesEditor.putInt(KEY+"1", topscore1);
            preferencesEditor.putString(KEY_NAME+"1", name1);
            preferencesEditor.putInt(KEY+"2", highscore);
            preferencesEditor.putString(KEY_NAME+"2", name);
            preferencesEditor.putInt(KEY+"3", topscore2);
            preferencesEditor.putString(KEY_NAME+"3", name2);
        } else {
            preferencesEditor.putInt(KEY+"1", topscore1);
            preferencesEditor.putString(KEY_NAME+"1", name1);
            preferencesEditor.putInt(KEY+"2", topscore2);
            preferencesEditor.putString(KEY_NAME+"2", name2);
            preferencesEditor.putInt(KEY+"3", highscore);
            preferencesEditor.putString(KEY_NAME+"3", name);
        }
        preferencesEditor.commit();
    }

    /**
     * displays highscores on given position keynumber in textView
     *
     * @param keynumber
     */
     private void showHighscore(int keynumber) {
        int highscore = getHighscore(keynumber);
        if (highscore > 0) {
            if (keynumber == 1) {
                tVHighscore.setText(Integer.toString(highscore) + getString(R.string.HighscoreFrom) + getHighscoreName(keynumber) + "\n");
            } else{
                tVHighscore.append(Integer.toString(highscore) + getString(R.string.HighscoreFrom) + getHighscoreName(keynumber) + "\n");
            }
        } else {
            tVHighscore.append("-\n");
        }
    }

    /**
     * displays message with reached points
     */
    private void showPoints() {
        if (currentPoints !=0) {
            tVcurrentPoints.setText(getString(R.string.youHaveReached) + currentPoints + getString(R.string.reachedPoints));
        }
    }
}
