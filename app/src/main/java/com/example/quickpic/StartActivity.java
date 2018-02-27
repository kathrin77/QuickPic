package com.example.quickpic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class StartActivity extends AppCompatActivity {

    ImageButton imgBtnMountain;
    ImageButton imgBtnFish;
    ImageButton imgBtnTree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        imgBtnMountain = findViewById(R.id.imgBtnMountain);
        imgBtnMountain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                intent.putExtra("TOPIC",'m');
                startActivity(intent);
            }
        });
        imgBtnFish = findViewById(R.id.imgBtnFish);
        imgBtnFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                intent.putExtra("TOPIC",'f');
                startActivity(intent);
            }
        });
        imgBtnTree = findViewById(R.id.imgBtnTree);
        imgBtnTree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                intent.putExtra("TOPIC",'t');
                startActivity(intent);
            }
        });

        //About-Button
        Button about = findViewById(R.id.button);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
            }
        });



    }
}
