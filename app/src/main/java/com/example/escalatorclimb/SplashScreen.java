package com.example.escalatorclimb;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //force portrait mode at all times
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_splash_screen);
        //code to add a splash screen delay
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                try{
                    sleep(5000);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally{
                    Intent startIntent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(startIntent);
                    finish();
                }
            }
        };
        thread.start();
    }
    @Override
    //disable the back button on the splash screen
    public void onBackPressed(){

    }
}
