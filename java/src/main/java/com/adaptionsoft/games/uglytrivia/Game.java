package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

import static com.adaptionsoft.games.uglytrivia.Category.getCategory;

public class Game {

    boolean isGettingOutOfPenaltyBox;

    QuestionSet questionSet;
    List<Player> players;
    int currentPlayer;

    Console console;

    public Game() {
        questionSet = new QuestionSet();
        players = new ArrayList<>();
        currentPlayer = 0;
        console = new Console();
    }

    public Player add(String playerName) {
        Player player = new Player(playerName);
        players.add(player);
        console.printAddPlayer(player, players.size());
        return player;
    }

    public void roll(int roll) {
        Player player = players.get(currentPlayer);
        console.printRoll(player, roll);

        if (player.isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;
                console.printOutOfThePenaltyBox(player);
                addPlacesAndAskQuestion(player, roll);
            } else {
                console.printNotOutOfThePenaltyBox(player);
                isGettingOutOfPenaltyBox = false;
            }
        } else {
            addPlacesAndAskQuestion(player, roll);
        }
    }

    public boolean wasCorrectlyAnswered() {
        boolean notAWinner;
        Player player = players.get(currentPlayer);
        if (player.isInPenaltyBox() && !isGettingOutOfPenaltyBox) {
            nextPlayer();
            notAWinner = true;
        } else {
            player.setPurses(player.getPurses() + 1);
            console.printCorrectlyAnswer(player);
            nextPlayer();
            notAWinner = !player.isWin();
        }
        return notAWinner;
    }

    public void wrongAnswer() {
        Player player = players.get(currentPlayer);
        player.setInPenaltyBox(true);
        console.printWrongAnswer(player);
        nextPlayer();
    }

    private void addPlacesAndAskQuestion(Player player, int roll) {
        player.addPlaces(roll);
        console.printPlayerLocationAndCategory(player, currentCategory(player));
        console.printQuestion(questionSet.nextQuestion(getCategory(player.getPlaces())));
    }

    private Category currentCategory(Player player) {
        return getCategory(player.getPlaces());
    }

    private void nextPlayer() {
        if (currentPlayer + 1 == players.size()) {
            currentPlayer = 0;
        } else {
            currentPlayer++;
        }
    }

}
