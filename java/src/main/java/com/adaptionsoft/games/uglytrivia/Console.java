package com.adaptionsoft.games.uglytrivia;

public class Console {

    public void printAddPlayer(Player player, int playersSize) {
        System.out.println(player.getName() + " was added");
        System.out.println("They are player number " + playersSize);
    }

    public void printRoll(Player player, int roll) {
        System.out.println(player.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);
    }

    public void printWrongAnswer(Player player) {
        System.out.println("Question was incorrectly answered");
        System.out.println(player.getName() + " was sent to the penalty box");
    }

    public void printCorrectlyAnswer(Player player) {
        System.out.println("Answer was correct!!!!");
        System.out.println(player.getName() + " now has " + player.getPurses() + " Gold Coins.");
    }

    public void printPlayerLocationAndCategory(Player player, Category category) {
        System.out.println(player.getName() + "'s new location is " + player.getPlaces());
        System.out.println("The category is " + category.getLibelle());
    }

    public void printQuestion(String question) {
        System.out.println(question);
    }

    public void printOutOfThePenaltyBox(Player player) {
        System.out.println(player.getName() + " is getting out of the penalty box");
    }

    public void printNotOutOfThePenaltyBox(Player player) {
        System.out.println(player.getName() + " is not getting out of the penalty box");
    }

}
