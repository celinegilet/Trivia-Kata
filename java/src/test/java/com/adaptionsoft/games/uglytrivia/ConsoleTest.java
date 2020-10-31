package com.adaptionsoft.games.uglytrivia;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.adaptionsoft.games.uglytrivia.Category.POP;
import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleTest {

    Console console;
    Player player;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void init() {
        console = new Console();
        player = new Player("Paul");
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void end() {
        System.setOut(null);
    }

    @Test
    public void printAddPlayer() {
        // Given
        int playersSize = 2;
        // When
        console.printAddPlayer(player, playersSize);
        // Then
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 2\n");
    }

    @Test
    public void printRoll() {
        // Given
        int roll = 2;
        // When
        console.printRoll(player, roll);
        // Then
        assertThat(outContent.toString()).isEqualTo(
                "Paul is the current player\n" +
                "They have rolled a 2\n");
    }

    @Test
    public void printWrongAnswer() {
        // When
        console.printWrongAnswer(player);
        // Then
        assertThat(outContent.toString()).isEqualTo(
                "Question was incorrectly answered\n" +
                "Paul was sent to the penalty box\n");
    }

    @Test
    public void printCorrectlyAnswer() {
        // When
        player.setPurses(5);
        console.printCorrectlyAnswer(player);
        // Then
        assertThat(outContent.toString()).isEqualTo(
                "Answer was correct!!!!\n" +
                        "Paul now has 5 Gold Coins.\n");
    }

    @Test
    public void printPlayerLocationAndCategory() {
        // Given
        Category category = POP;
        player.setPlaces(2);
        // When
        console.printPlayerLocationAndCategory(player, category);
        // Then
        assertThat(outContent.toString()).isEqualTo(
                "Paul's new location is 2\n" +
                        "The category is Pop\n");
    }

    @Test
    public void printQuestion() {
        // Given
        String question = "Question Pop 1";
        // When
        console.printQuestion(question);
        // Then
        assertThat(outContent.toString()).isEqualTo("Question Pop 1\n");
    }

    @Test
    public void printOutOfThePenaltyBox() {
        // When
        console.printOutOfThePenaltyBox(player);
        // Then
        assertThat(outContent.toString()).isEqualTo("Paul is getting out of the penalty box\n");
    }

    @Test
    public void printNotOutOfThePenaltyBox() {
        // When
        console.printNotOutOfThePenaltyBox(player);
        // Then
        assertThat(outContent.toString()).isEqualTo("Paul is not getting out of the penalty box\n");

    }

}
