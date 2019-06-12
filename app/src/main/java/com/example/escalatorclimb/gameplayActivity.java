package com.example.escalatorclimb;

import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class gameplayActivity extends AppCompatActivity {

    //creating an object of player character class
    private PlayerControlledCharacterView gameView;
    private Handler handler = new Handler();
    //the clock for the timer and the canvas activity
    private final static long interval = 30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //set the initial view for this activity to the player image
        gameView = new PlayerControlledCharacterView(this);
        //display it as the content view
        setContentView(gameView);

        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                handler.post(new Runnable() {
                    @Override
                    public void run()
                    {
                        gameView.invalidate();
                    }
                });
            }
        },0,interval);

    }
    @Override
    //disable the back button when in game
    public void onBackPressed(){

    }
}
