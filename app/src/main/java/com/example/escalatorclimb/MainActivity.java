package com.example.escalatorclimb;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//activity for the title screen
public class MainActivity extends AppCompatActivity {
    //global media player used for title screen music
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //force portrait mode at all times
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        //create the button for starting the game
        Button startBtn = (Button) findViewById(R.id.startBtn);
        //create button for viewing the instructions
        Button instructionsBtn = (Button) findViewById(R.id.instructionsBtn);
        //create button for exiting the game
        Button exitBtn = (Button) findViewById(R.id.exitBtn);
        //initialize the title screen music
        player = MediaPlayer.create(getApplicationContext(),R.raw.characterselect);
        //start title screen music
        player.start();
        instructionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this intent will start the instructions activity when the instructions button is pressed
                Intent startIntent = new Intent(getApplicationContext(),instructionsActivity.class);
                startActivity(startIntent);
            }
        });
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //stop the media player when the game starts
                player.stop();
                //create a new intent and link it to the second activity. provide link from main activity to second one
                Intent startIntent = new Intent(getApplicationContext(),gameOver.class);
                startActivityForResult(startIntent,2);
                //all happens within a button press
            }
        });
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //close when exit button pressed
                finish();
                System.exit(0);
            }
        });
    }
    @Override
    //disable the back button on the start menu
    public void onBackPressed(){

    }

    public void onActivityResult(int requestCode,int resultCode,Intent data){
        //code restarts music upon returning to this class
        if((requestCode == 2)&& (resultCode == RESULT_OK)){
            player = MediaPlayer.create(getApplicationContext(),R.raw.characterselect);
            player.start();
        }
    }
}
