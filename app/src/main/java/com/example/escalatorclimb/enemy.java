package com.example.escalatorclimb;
//create a class for the enemy information and variables
public class enemy {
    private int lane;
    private boolean active;
    //which stair position
    private int position;
    //constructor sets attributes for object
    public enemy(int lane, boolean active,int position) {
        this.lane = lane;
        this.active = active;
        this.position = position;
    }

    public int getLane(){
        return lane;
    }
    public int getPosition(){
        return position;
    }
    public boolean getActive(){
        return active;
    }

    public void setLane(int lane){
        this.lane = lane;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public void setActive(boolean active){
        this.active = active;
    }
}

