package com.example.quickpic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HighscoreActivity extends AppCompatActivity {

    Button btnStartseite;
    TextView tVcurrentPoints;
    TextView tVHighscore;
    private LinearLayout LlNameInput;
    private Button btnSaveName;

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

        showHighscore();
        showPoints();

        LlNameInput = findViewById(R.id.LlNameInput);
        LlNameInput.setVisibility(View.INVISIBLE);

        if (currentPoints > getHighscore()) {
            LlNameInput.setVisibility(View.VISIBLE);
        }

        btnSaveName = findViewById(R.id.btnSaveName);
        btnSaveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeHighscore(currentPoints);
                writeHighscoreName();
                showHighscore();
                LlNameInput.setVisibility(View.INVISIBLE);
            }
        });

    }

    private int getHighscore() {
        //SharedPreferences pref = getSharedPreferences("GAME", 0);
        return preferences.getInt("HIGHSCORE",0);
    }

    private String getHighscoreName() {
        //SharedPreferences pref = getSharedPreferences("GAME",0);
        return preferences.getString("HIGHSCORE_NAME", "");
    }

    private void writeHighscore(int highscore) {
        /*SharedPreferences pref = getSharedPreferences("GAME",0);
        SharedPreferences.Editor editor = pref.edit();*/
        preferencesEditor.putInt("HIGHSCORE", highscore);
        preferencesEditor.commit();
    }

    private void writeHighscoreName() {
        EditText eTName = findViewById(R.id.eTName);
        String name = eTName.getText().toString().trim();
        /*SharedPreferences pref = getSharedPreferences("GAME",0);
        SharedPreferences.Editor editor = pref.edit();*/
        preferencesEditor.putString("HIGHSCORE_NAME", name);
        preferencesEditor.commit();
    }

     private void showHighscore() {
        int highscore = getHighscore();
        if (highscore > 0) {
            tVHighscore.setText(Integer.toString(highscore) + " " + getString(R.string.HighscoreFrom) + " " + getHighscoreName());
        } else {
            tVHighscore.setText("-");
        }
    }

    private void showPoints() {
        tVcurrentPoints.setText(getString(R.string.currentPoints1) + " " + currentPoints + " " + getString(R.string.currentPoints2));
    }
}
