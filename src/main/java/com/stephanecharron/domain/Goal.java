package com.stephanecharron.domain;

/**
 * Created by stephanecharron on 16-08-08.
 */
public class Goal {
    private int time;
    private String playerNane;

    public Goal(int time, String playerNane) {
        this.time = time;
        this.playerNane = playerNane;
    }


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getPlayerNane() {
        return playerNane;
    }

    public void setPlayerNane(String playerNane) {
        this.playerNane = playerNane;
    }

    @Override
    public String toString() {
        return this.playerNane + ' ' + this.time;
    }
}
