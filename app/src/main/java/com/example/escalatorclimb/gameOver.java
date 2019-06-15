package com.example.escalatorclimb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static java.security.AccessController.getContext;

public class gameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_game_over);
        //open the game activity over the game over one
        Intent startIntent = new Intent(getApplicationContext(),gameplayActivity.class);
        //use start activity for result to return the score
        startActivityForResult(startIntent,1);

        //create button for returning to the main menu
        Button mainMenuBtn = (Button) findViewById(R.id.mainMenu);
        mainMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set flag to start title screen music upon return
                Intent i = new Intent();
                setResult(RESULT_OK,i);
                finish();
            }
        });

    }
    @Override
    //disable the back button on the gameOver menu
    public void onBackPressed(){

    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        //code displays the score after the player has died
        if((requestCode == 1)&& (resultCode == RESULT_OK)){
            int score = data.getIntExtra("score",0);
            TextView scoreView = findViewById(R.id.scoreView);
            String scoreString = "Your score is: " + score;
            scoreView.setText(scoreString);
        }
    }
}
