package com.adaptionsoft.games.uglytrivia;

public class Player {

    private String name;
    private int places;
    private int purses;
    private boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
        this.places = 0;
        this.purses = 0;
        this.inPenaltyBox = false;
    }

    public boolean isWin() {
        return this.purses == 6;
    }

    public void addPlaces(int roll) {
        this.places = this.places + roll;
        if (this.places > 11) {
            this.places = this.places - 12;
        }
    }

    public String getName() {
        return name;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public int getPurses() {
        return purses;
    }

    public void setPurses(int purses) {
        this.purses = purses;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }
}
