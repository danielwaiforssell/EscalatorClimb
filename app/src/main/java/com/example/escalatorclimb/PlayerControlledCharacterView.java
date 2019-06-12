package com.example.escalatorclimb;
//class for the player controlled character
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

public class PlayerControlledCharacterView extends View {

    //move constant for moving the player left to right
    static final int MOVE_CONSTANT = 250;
    static final int PLAYER_X_POS_START = 370;
    static final int PLAYER_Y_POS_START = 900;
    //maximum number of enemies in the array
    static final int MAX_ENEMIES = 8;
    //variables for swipe event
    private float x1,x2;
    static final int MIN_DISTANCE = 150;
    //variables for bitmap of background and player
    private Bitmap playerCharacter;
    private Bitmap backgroundImage;
    private Bitmap enemyOne;
    private Bitmap enemyTwo;
    private Bitmap enemyThree;
    private Bitmap enemyFour;
    private Bitmap enemyFive;
    private Bitmap enemySix;
    //score variable

    private Paint scorePaint = new Paint();

    //player coordinates

    private int playerx = PLAYER_X_POS_START;
    private int playery = PLAYER_Y_POS_START;


    //enemy position (how far its gone down the stairs)

    private int enemyPosition = 0;
    //enemy counter to control how fast it moves
    private int enemySpeed = 18;
    //space out the enemies
    private int enemySpace = 1;

    //score
    private int score = 0;


    //score counter. determines the speed the score increments at
    private int scoreCounter = 12;

    //instantiate inactive enemy objects array
    //the max amount of enemies on the screen = MAX_ENEMIES
    enemy[] enemies = new enemy[MAX_ENEMIES];

    //check if a hit occurred

    private boolean hit = false;
    //integer to impact the enemy spawn rate
    private int enemySpawn = 5;

    public PlayerControlledCharacterView(Context context) {
        //referring to the parent object. this class is a subclass of the view class, a preexisting
        //class used for user interface and display components
        super(context);

        //inactive enemies have a lane and position of -1;
        for(int i = 0; i < MAX_ENEMIES;i++){
            enemies[i] = new enemy(-1,false,-1);
        }
        //store character in a bitmap from the picture in the resources section of the project
        playerCharacter = BitmapFactory.decodeResource(getResources(),R.drawable.player);

        //store the background image in bitmap from image file

        backgroundImage = BitmapFactory.decodeResource(getResources(),R.drawable.gamebackground);

        //enemy images

        enemyOne = BitmapFactory.decodeResource(getResources(),R.drawable.enemyone);
        enemyTwo = BitmapFactory.decodeResource(getResources(),R.drawable.enemytwo);
        enemyThree = BitmapFactory.decodeResource(getResources(),R.drawable.enemythree);
        enemyFour = BitmapFactory.decodeResource(getResources(),R.drawable.enemyfour);
        enemyFive = BitmapFactory.decodeResource(getResources(),R.drawable.enemyfive);
        enemySix = BitmapFactory.decodeResource(getResources(),R.drawable.enemysix);
        //setup display of the score

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(60);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);
    }

    @Override
    //new canvas method for this class
    protected void onDraw(Canvas canvas) {
        //check if a hit occurred
        if (hit) {
            Activity activity = (Activity)getContext();
            activity.finish();
        } else {
            //left player position
            if (playerx < (PLAYER_X_POS_START - MOVE_CONSTANT)) {
                //if player is to the left of the edge move it in bounds
                playerx += MOVE_CONSTANT;
            }
            //right player position
            else if (playerx > (PLAYER_X_POS_START + MOVE_CONSTANT)) {
                //same for right side
                playerx -= MOVE_CONSTANT;
            }
            super.onDraw(canvas);
            //draw the bitmap on the canvas
            canvas.drawBitmap(backgroundImage, 0, 0, null);
            //instantiate inactive enemy objects array
            //the max amount of enemies on the screen = 6

            //now fill array with enemies
            //use rng to determine how often
            //one in 5 for now
            //change the rng based on score

            if(score > 150){
                enemySpawn = 3;
            }
            else{
                enemySpawn = 5;
            }

            if (enemySpeed == 0) {
                if (((int) (Math.random() * enemySpawn)) == 0) {
                    int lane = (int) (Math.random() * 3);
                    for (int i = 0; i < MAX_ENEMIES; i++) {
                        if (!enemies[i].getActive()) {
                            enemies[i].setLane(lane);
                            enemies[i].setActive(true);
                            //break out of loop
                            break;
                        }
                    }
                }
            }
            //possible enemy positions
            //for middle

            //the following code controls the movement of the enemies
            //move all enemies based on class information
            for (int i = 0; i < MAX_ENEMIES; i++) {
                if (enemies[i].getActive()) {
                    if (enemies[i].getPosition() == 0) {
                        if (enemies[i].getLane() == 1) {
                            canvas.drawBitmap(enemyOne, 633, 130, null);
                        } else if (enemies[i].getLane() == 0) {
                            canvas.drawBitmap(enemyOne, 580, 130, null);
                        } else if (enemies[i].getLane() == 2) {
                            canvas.drawBitmap(enemyOne, 686, 130, null);
                        }
                    } else if (enemies[i].getPosition() == 1) {
                        if (enemies[i].getLane() == 1) {
                            canvas.drawBitmap(enemyOne, 633, 150, null);
                        } else if (enemies[i].getLane() == 0) {
                            canvas.drawBitmap(enemyOne, 580, 150, null);
                        } else if (enemies[i].getLane() == 2) {
                            canvas.drawBitmap(enemyOne, 686, 150, null);
                        }
                    } else if (enemies[i].getPosition() == 2) {
                        if (enemies[i].getLane() == 1) {
                            canvas.drawBitmap(enemyOne, 633, 170, null);
                        } else if (enemies[i].getLane() == 0) {
                            canvas.drawBitmap(enemyOne, 579, 170, null);
                        } else if (enemies[i].getLane() == 2) {
                            canvas.drawBitmap(enemyOne, 687, 170, null);
                        }
                    } else if (enemies[i].getPosition() == 3) {
                        if (enemies[i].getLane() == 1) {
                            canvas.drawBitmap(enemyTwo, 625, 193, null);
                        } else if (enemies[i].getLane() == 0) {
                            canvas.drawBitmap(enemyTwo, 570, 193, null);
                        } else if (enemies[i].getLane() == 2) {
                            canvas.drawBitmap(enemyTwo, 682, 193, null);
                        }
                    } else if (enemies[i].getPosition() == 4) {
                        if (enemies[i].getLane() == 1) {
                            canvas.drawBitmap(enemyTwo, 630, 206, null);
                        } else if (enemies[i].getLane() == 0) {
                            canvas.drawBitmap(enemyTwo, 565, 212, null);
                        } else if (enemies[i].getLane() == 2) {
                            canvas.drawBitmap(enemyTwo, 688, 213, null);
                        }
                    } else if (enemies[i].getPosition() == 5) {
                        if (enemies[i].getLane() == 1) {
                            canvas.drawBitmap(enemyTwo, 630, 265, null);
                        } else if (enemies[i].getLane() == 0) {
                            canvas.drawBitmap(enemyTwo, 561, 265, null);
                        } else if (enemies[i].getLane() == 2) {
                            canvas.drawBitmap(enemyTwo, 694, 266, null);
                        }
                    } else if (enemies[i].getPosition() == 6) {
                        if (enemies[i].getLane() == 1) {
                            canvas.drawBitmap(enemyThree, 620, 350, null);
                        } else if (enemies[i].getLane() == 0) {
                            canvas.drawBitmap(enemyThree, 530, 350, null);
                        } else if (enemies[i].getLane() == 2) {
                            canvas.drawBitmap(enemyThree, 700, 350, null);
                        }
                    } else if (enemies[i].getPosition() == 7) {
                        if (enemies[i].getLane() == 1) {
                            canvas.drawBitmap(enemyThree, 625, 400, null);
                        } else if (enemies[i].getLane() == 0) {
                            canvas.drawBitmap(enemyThree, 520, 400, null);
                        } else if (enemies[i].getLane() == 2) {
                            canvas.drawBitmap(enemyThree, 710, 400, null);
                        }
                    } else if (enemies[i].getPosition() == 8) {
                        if (enemies[i].getLane() == 1) {
                            canvas.drawBitmap(enemyFour, 610, 420, null);
                        } else if (enemies[i].getLane() == 0) {
                            canvas.drawBitmap(enemyFour, 500, 425, null);
                        } else if (enemies[i].getLane() == 2) {
                            canvas.drawBitmap(enemyFour, 720, 420, null);
                        }
                    } else if (enemies[i].getPosition() == 9) {
                        if (enemies[i].getLane() == 1) {
                            canvas.drawBitmap(enemyFour, 613, 505, null);
                        } else if (enemies[i].getLane() == 0) {
                            canvas.drawBitmap(enemyFour, 480, 510, null);
                        } else if (enemies[i].getLane() == 2) {
                            canvas.drawBitmap(enemyFour, 730, 510, null);
                        }
                    } else if (enemies[i].getPosition() == 10) {
                        if (enemies[i].getLane() == 1) {
                            canvas.drawBitmap(enemyFour, 610, 605, null);
                        } else if (enemies[i].getLane() == 0) {
                            canvas.drawBitmap(enemyFour, 460, 615, null);
                        } else if (enemies[i].getLane() == 2) {
                            canvas.drawBitmap(enemyFour, 733, 610, null);
                        }
                    } else if (enemies[i].getPosition() == 11) {
                        if (enemies[i].getLane() == 1) {
                            canvas.drawBitmap(enemyFive, 580, 630, null);
                        } else if (enemies[i].getLane() == 0) {
                            canvas.drawBitmap(enemyFive, 430, 640, null);
                        } else if (enemies[i].getLane() == 2) {
                            canvas.drawBitmap(enemyFive, 720, 640, null);
                        }
                    } else if (enemies[i].getPosition() == 12) {
                        if (enemies[i].getLane() == 1) {
                            canvas.drawBitmap(enemyFive, 580, 790, null);
                        } else if (enemies[i].getLane() == 0) {
                            canvas.drawBitmap(enemyFive, 390, 800, null);
                        } else if (enemies[i].getLane() == 2) {
                            canvas.drawBitmap(enemyFive, 740, 790, null);
                        }
                        //empty the array element and set to initial state
                    } else if (enemies[i].getPosition() == 13) {
                        if (enemies[i].getLane() == 1) {
                            canvas.drawBitmap(enemySix, 540, 870, null);
                        } else if (enemies[i].getLane() == 0) {
                            canvas.drawBitmap(enemySix, 330, 880, null);
                        } else if (enemies[i].getLane() == 2) {
                            canvas.drawBitmap(enemySix, 735, 875, null);
                        }
                    }
                }
            }
            canvas.drawBitmap(playerCharacter, playerx, playery, null);
            canvas.drawText("Score : " + score, 20, 60, scorePaint);
            //score count is independent and is done here
            scoreCounter--;
            if (scoreCounter == 0) {
                score += 1;
                scoreCounter = 12;
            }
            //increment all enemy positions
            Intent startIntent = new Intent(getContext().getApplicationContext(), gameOver.class);
            for (int i = 0; i < MAX_ENEMIES; i++) {
                if (enemies[i].getPosition() == 13) {
                    //if both the enemy and player are in the same lane
                    if ((enemies[i].getLane() == 0) && (playerx == (PLAYER_X_POS_START - MOVE_CONSTANT))) {
                        hit = true;
                    } else if ((enemies[i].getLane() == 1) && (playerx == PLAYER_X_POS_START)) {
                        hit = true;
                    } else if ((enemies[i].getLane() == 2) && (playerx == (PLAYER_X_POS_START + MOVE_CONSTANT))) {
                        hit = true;
                    } else {
                        if (enemySpeed == 0) {
                            enemies[i].setPosition(-1);
                            enemies[i].setLane(-1);
                            enemies[i].setActive(false);
                        }
                    }
                } else {
                    if (enemySpeed == 0) {
                        if (enemies[i].getActive()) {
                            enemies[i].setPosition((enemies[i].getPosition() + 1));
                        }
                    }
                }
            }
            if (enemySpeed == 0) {
                //increase the speed as the game goes on
                if(score > 100){
                    enemySpeed = 4;
                }
                else if (score > 50){
                    enemySpeed = 7;
                }
                else {
                    enemySpeed = 10;
                }
            } else {
                //decrement enemy speed counter
                enemySpeed--;
            }
        }
    }

    @Override
    //code for swipe event. works for left and right swipe

    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction())
        {
            //x2 is the end of the swipe and x1 is the beginning position
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
            break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;
                if(Math.abs(deltaX) > MIN_DISTANCE){
                    //left to right swipe
                    if(x2 > x1){
                        playerx += MOVE_CONSTANT;
                    }
                    //right to left swipe
                    else{
                        playerx -= MOVE_CONSTANT;
                    }
                }
             break;
        }
        return true;
    }
}





