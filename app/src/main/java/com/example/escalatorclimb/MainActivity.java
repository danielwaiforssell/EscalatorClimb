package com.example.escalatorclimb;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//activity for the title screen
public class MainActivity extends AppCompatActivity {

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
                //create a new intent and link it to the second activity. provide link from main activity to second one
                Intent startIntent = new Intent(getApplicationContext(),gameOver.class);
                startActivity(startIntent);
                //all happens within a button press
            }
        });
    }
    @Override
    //disable the back button on the start menu
    public void onBackPressed(){

    }
}
