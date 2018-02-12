package com.example.quickpic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HighscoreActivity extends AppCompatActivity {

    Button btnStartseite;
    TextView tVcurrentPoints;

    SharedPreferences preferences;
    SharedPreferences.Editor preferencesEditor;

    int currentPoints;
    final String KEY = "savePreferences";

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

        preferences = this.getSharedPreferences("highscore", MODE_PRIVATE);
        preferencesEditor = preferences.edit();
        preferencesEditor.apply();

        currentPoints = getIntent().getIntExtra("Points", 0);

        showPoints();
    }

    private void showPoints() {
        if (preferences.getInt(KEY,0) < currentPoints) {
            tVcurrentPoints.setText("NEW HIGHSCORE! You have reached " + currentPoints + " Points!");

            preferencesEditor.putInt(KEY, currentPoints);
            preferencesEditor.commit();

        } else {
            tVcurrentPoints.setText("You have reached " + currentPoints+ " Points");
        }
    }
}
