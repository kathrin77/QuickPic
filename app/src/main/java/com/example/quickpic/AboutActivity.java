package com.example.quickpic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AboutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // zum Testen der Highscoreliste - einfach value veraendern, Run app, und dann vom
        // Startbildschirm aus direkt mit dieser Punktzahl in den Highscorebildschirm springen
        Button Highscore = findViewById(R.id.btnhighscore);
        Highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
                intent.putExtra("Points",10);
                startActivity(intent);
            }
        });


    }
}
