package com.adaptionsoft.games.uglytrivia;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void init() {
      System.setOut(new PrintStream(outContent));
    }

    @After
    public void end() {
      System.setOut(null);
    }

    @Test
    public void initGame_shouldInitPopQuestions() {
        // When
        Game game = new Game();

        // Then
        assertThat(game.popQuestions.size()).isEqualTo(50);
        for (int i = 0; i < 50; i++) {
            assertThat(game.popQuestions.get(i)).isEqualTo("Pop Question " + i);
        }
    }

    @Test
    public void initGame_shouldInitScienceQuestions() {
        // When
        Game game = new Game();

        // Then
        assertThat(game.scienceQuestions.size()).isEqualTo(50);
        for (int i = 0; i < 50; i++) {
            assertThat(game.scienceQuestions.get(i)).isEqualTo("Science Question " + i);
        }
    }

    @Test
    public void initGame_shouldInitSportsQuestions() {
        // When
        Game game = new Game();

        // Then
        assertThat(game.sportsQuestions.size()).isEqualTo(50);
        for (int i = 0; i < 50; i++) {
            assertThat(game.sportsQuestions.get(i)).isEqualTo("Sports Question " + i);
        }
    }

    @Test
    public void initGame_shouldInitRockQuestions() {
        // When
        Game game = new Game();

        // Then
        assertThat(game.rockQuestions.size()).isEqualTo(50);
        for (int i = 0; i < 50; i++) {
            assertThat(game.rockQuestions.get(i)).isEqualTo("Rock Question " + i);
        }
    }

    @Test
    public void addPlayerToGame_shouldInitDatasForPlayer() {
        // Given
        Game game = new Game();
        String player = "Chet";

        // When
        game.add(player);

        // Then
        assertThat(game.players.size()).isEqualTo(1);
        assertThat(game.players.get(0)).isEqualTo("Chet");
        assertThat(game.places[0]).isEqualTo(0);
        assertThat(game.purses[0]).isEqualTo(0);
        assertThat(game.inPenaltyBox[0]).isFalse();
        assertThat(outContent.toString()).isEqualTo(
                "Chet was added\n" +
                "They are player number 1\n");
    }

    @Test
    public void addTwoPlayersToGame_shouldInitDatasForPlayers() {
        // Given
        Game game = new Game();
        String player1 = "Chet";
        String player2 = "Pat";

        // When
        game.add(player1);
        game.add(player2);

        // Then
        assertThat(game.players.size()).isEqualTo(2);
        assertThat(game.players.get(0)).isEqualTo("Chet");
        assertThat(game.places[0]).isEqualTo(0);
        assertThat(game.purses[0]).isEqualTo(0);
        assertThat(game.inPenaltyBox[0]).isFalse();
        assertThat(game.players.get(1)).isEqualTo("Pat");
        assertThat(game.places[1]).isEqualTo(0);
        assertThat(game.purses[1]).isEqualTo(0);
        assertThat(game.inPenaltyBox[1]).isFalse();
        assertThat(outContent.toString()).isEqualTo(
                "Chet was added\n" +
                "They are player number 1\n" +
                "Pat was added\n" +
                "They are player number 2\n");
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void BUG_addSixPlayersToGame_shouldThrowException() {
        // Given
        Game game = new Game();
        String player1 = "Chet";
        String player2 = "Pat";
        String player3 = "Jessie";
        String player4 = "Peter";
        String player5 = "John";
        String player6 = "July";

        // When
        game.add(player1);
        game.add(player2);
        game.add(player3);
        game.add(player4);
        game.add(player5);
        game.add(player6);
    }

    @Test
    public void isPlayable_shouldReturnTrue_whenMinimumTwoPlayers() {
        // Given
        Game game = new Game();
        game.add("John");
        game.add("July");

        // When
        boolean isPlayable = game.isPlayable();

        // Then
        assertThat(isPlayable).isTrue();
        assertThat(outContent.toString()).isEqualTo(
                "John was added\n" +
                "They are player number 1\n" +
                "July was added\n" +
                "They are player number 2\n");
    }

    @Test
    public void isPlayable_shouldReturnFalse_whenNotMinimumTwoPlayers() {
        // Given
        Game game = new Game();
        game.add("Paul");

        // When
        boolean isPlayable = game.isPlayable();

        // Then
        assertThat(isPlayable).isFalse();
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n");
    }

    @Test
    public void rollPlayer_shouldAddRollToPlacesIfPlacesIsLessThan11() {
        // Given
        Game game = new Game();
        game.add("Paul");

        // When
        game.roll(5);

        // Then
        assertThat(game.places[0]).isEqualTo(5);
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 5\n" +
                "Paul's new location is 5\n" +
                "The category is Science\n" +
                "Science Question 0\n");
    }

    @Test
    public void rollPlayer_shouldMinus12ToPlacesIfPlacesIsEqualsTo12() {
        // Given
        Game game = new Game();
        game.add("Paul");

        // When
        game.roll(1);
        game.roll(11);

        // Then
        assertThat(game.places[0]).isEqualTo(0);
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 1\n" +
                "Paul's new location is 1\n" +
                "The category is Science\n" +
                "Science Question 0\n" +
                "Paul is the current player\n" +
                "They have rolled a 11\n" +
                "Paul's new location is 0\n" +
                "The category is Pop\n" +
                "Pop Question 0\n");
    }

    @Test
    public void rollPlayer_shouldMinus12ToPlacesIfPlacesIsEqualsTo22() {
        // Given
        Game game = new Game();
        game.add("Paul");

        // When
        game.roll(11);
        game.roll(11);

        // Then
        assertThat(game.places[0]).isEqualTo(10);
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 11\n" +
                "Paul's new location is 11\n" +
                "The category is Rock\n" +
                "Rock Question 0\n" +
                "Paul is the current player\n" +
                "They have rolled a 11\n" +
                "Paul's new location is 10\n" +
                "The category is Sports\n" +
                "Sports Question 0\n");
    }

    @Test
    public void rollPlayer_shouldQuestionCategoryPop_whenPlaces0() {
        // Given
        Game game = new Game();
        game.add("Paul");

        // When
        game.roll(0);

        // Then
        assertThat(game.places[0]).isEqualTo(0);
        assertThat(game.currentCategory()).isEqualTo("Pop");
        assertThat(game.popQuestions.size()).isEqualTo(49);
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 0\n" +
                "Paul's new location is 0\n" +
                "The category is Pop\n" +
                "Pop Question 0\n");
    }

    @Test
    public void rollPlayer_shouldQuestionCategoryPop_whenPlaces4() {
        // Given
        Game game = new Game();
        game.add("Paul");

        // When
        game.roll(4);

        // Then
        assertThat(game.places[0]).isEqualTo(4);
        assertThat(game.currentCategory()).isEqualTo("Pop");
        assertThat(game.popQuestions.size()).isEqualTo(49);
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 4\n" +
                "Paul's new location is 4\n" +
                "The category is Pop\n" +
                "Pop Question 0\n");
    }

    @Test
    public void rollPlayer_shouldQuestionCategoryPop_whenPlaces8() {
        // Given
        Game game = new Game();
        game.add("Paul");

        // When
        game.roll(8);

        // Then
        assertThat(game.places[0]).isEqualTo(8);
        assertThat(game.currentCategory()).isEqualTo("Pop");
        assertThat(game.popQuestions.size()).isEqualTo(49);
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 8\n" +
                "Paul's new location is 8\n" +
                "The category is Pop\n" +
                "Pop Question 0\n");
    }

    @Test
    public void rollPlayer_shouldQuestionCategoryScienceWithPlaces1() {
        // Given
        Game game = new Game();
        game.add("Paul");

        // When
        game.roll(1);

        // Then
        assertThat(game.places[0]).isEqualTo(1);
        assertThat(game.currentCategory()).isEqualTo("Science");
        assertThat(game.scienceQuestions.size()).isEqualTo(49);
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 1\n" +
                "Paul's new location is 1\n" +
                "The category is Science\n" +
                "Science Question 0\n");
    }

    @Test
    public void rollPlayer_shouldQuestionCategoryScienceWithPlaces5() {
        // Given
        Game game = new Game();
        game.add("Paul");

        // When
        game.roll(5);

        // Then
        assertThat(game.places[0]).isEqualTo(5);
        assertThat(game.currentCategory()).isEqualTo("Science");
        assertThat(game.scienceQuestions.size()).isEqualTo(49);
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 5\n" +
                "Paul's new location is 5\n" +
                "The category is Science\n" +
                "Science Question 0\n");
    }

    @Test
    public void rollPlayer_shouldQuestionCategoryScienceWithPlaces9() {
        // Given
        Game game = new Game();
        game.add("Paul");

        // When
        game.roll(9);

        // Then
        assertThat(game.places[0]).isEqualTo(9);
        assertThat(game.currentCategory()).isEqualTo("Science");
        assertThat(game.scienceQuestions.size()).isEqualTo(49);
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 9\n" +
                "Paul's new location is 9\n" +
                "The category is Science\n" +
                "Science Question 0\n");
    }

    @Test
    public void rollPlayer_shouldQuestionCategorySportsWithPlaces2() {
        // Given
        Game game = new Game();
        game.add("Paul");

        // When
        game.roll(2);

        // Then
        assertThat(game.places[0]).isEqualTo(2);
        assertThat(game.currentCategory()).isEqualTo("Sports");
        assertThat(game.sportsQuestions.size()).isEqualTo(49);
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 2\n" +
                "Paul's new location is 2\n" +
                "The category is Sports\n" +
                "Sports Question 0\n");
    }

    @Test
    public void rollPlayer_shouldQuestionCategorySportsWithPlaces6() {
        // Given
        Game game = new Game();
        game.add("Paul");

        // When
        game.roll(6);

        // Then
        assertThat(game.places[0]).isEqualTo(6);
        assertThat(game.currentCategory()).isEqualTo("Sports");
        assertThat(game.sportsQuestions.size()).isEqualTo(49);
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 6\n" +
                "Paul's new location is 6\n" +
                "The category is Sports\n" +
                "Sports Question 0\n");
    }

    @Test
    public void rollPlayer_shouldQuestionCategorySportsWithPlaces10() {
        // Given
        Game game = new Game();
        game.add("Paul");

        // When
        game.roll(10);

        // Then
        assertThat(game.places[0]).isEqualTo(10);
        assertThat(game.currentCategory()).isEqualTo("Sports");
        assertThat(game.sportsQuestions.size()).isEqualTo(49);
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 10\n" +
                "Paul's new location is 10\n" +
                "The category is Sports\n" +
                "Sports Question 0\n");
    }

    @Test
    public void rollPlayer_shouldQuestionCategoryRockWithPlaces3() {
        // Given
        Game game = new Game();
        game.add("Paul");

        // When
        game.roll(3);

        // Then
        assertThat(game.places[0]).isEqualTo(3);
        assertThat(game.currentCategory()).isEqualTo("Rock");
        assertThat(game.rockQuestions.size()).isEqualTo(49);
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 3\n" +
                "Paul's new location is 3\n" +
                "The category is Rock\n" +
                "Rock Question 0\n");
    }

    @Test
    public void rollPlayer_shouldQuestionCategoryRockWithPlaces7() {
        // Given
        Game game = new Game();
        game.add("Paul");

        // When
        game.roll(7);

        // Then
        assertThat(game.places[0]).isEqualTo(7);
        assertThat(game.currentCategory()).isEqualTo("Rock");
        assertThat(game.rockQuestions.size()).isEqualTo(49);
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 7\n" +
                "Paul's new location is 7\n" +
                "The category is Rock\n" +
                "Rock Question 0\n");
    }

    @Test
    public void rollPlayer_shouldQuestionCategoryRockWithPlaces11() {
        // Given
        Game game = new Game();
        game.add("Paul");

        // When
        game.roll(11);

        // Then
        assertThat(game.places[0]).isEqualTo(11);
        assertThat(game.currentCategory()).isEqualTo("Rock");
        assertThat(game.rockQuestions.size()).isEqualTo(49);
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 11\n" +
                "Paul's new location is 11\n" +
                "The category is Rock\n" +
                "Rock Question 0\n");
    }

    @Test
    public void rollPlayer_shouldQuestionCategoryPopWithPlaces12() {
        // Given
        Game game = new Game();
        game.add("Paul");

        // When
        game.roll(12);

        // Then
        assertThat(game.places[0]).isEqualTo(0);
        assertThat(game.currentCategory()).isEqualTo("Pop");
        assertThat(game.popQuestions.size()).isEqualTo(49);
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 12\n" +
                "Paul's new location is 0\n" +
                "The category is Pop\n" +
                "Pop Question 0\n");
    }

    @Test
    public void rollPlayer_shouldOutOfPenaltyBoxFalseIfRollIsEven() {
        // Given
        Game game = new Game();
        game.add("Paul");

        // When
        game.roll(1);
        game.wrongAnswer();
        game.roll(2);

        // Then
        assertThat(game.isGettingOutOfPenaltyBox).isFalse();
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 1\n" +
                "Paul's new location is 1\n" +
                "The category is Science\n" +
                "Science Question 0\n" +
                "Question was incorrectly answered\n" +
                "Paul was sent to the penalty box\n" +
                "Paul is the current player\n" +
                "They have rolled a 2\n" +
                "Paul is not getting out of the penalty box\n");
    }

    @Test
    public void rollPlayer_shouldOutOfPenaltyBoxIfRollIsNotEven() {
        // Given
        Game game = new Game();
        game.add("Paul");

        // When
        game.roll(1);
        game.wrongAnswer();
        game.roll(3);

        // Then
        assertThat(game.places[0]).isEqualTo(4);
        assertThat(game.isGettingOutOfPenaltyBox).isTrue();
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 1\n" +
                "Paul's new location is 1\n" +
                "The category is Science\n" +
                "Science Question 0\n" +
                "Question was incorrectly answered\n" +
                "Paul was sent to the penalty box\n" +
                "Paul is the current player\n" +
                "They have rolled a 3\n" +
                "Paul is getting out of the penalty box\n" +
                "Paul's new location is 4\n" +
                "The category is Pop\n" +
                "Pop Question 0\n");
    }

    @Test
    public void rollPlayer_shouldOutOfPenaltyBoxIfRollIsNotEvenAndPlacesGreaterThan11() {
        // Given
        Game game = new Game();
        game.add("Paul");

        // When
        game.roll(1);
        game.wrongAnswer();
        game.roll(13);

        // Then
        assertThat(game.places[0]).isEqualTo(2);
        assertThat(game.isGettingOutOfPenaltyBox).isTrue();
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 1\n" +
                "Paul's new location is 1\n" +
                "The category is Science\n" +
                "Science Question 0\n" +
                "Question was incorrectly answered\n" +
                "Paul was sent to the penalty box\n" +
                "Paul is the current player\n" +
                "They have rolled a 13\n" +
                "Paul is getting out of the penalty box\n" +
                "Paul's new location is 2\n" +
                "The category is Sports\n" +
                "Sports Question 0\n");
    }

    @Test
    public void wrongAnswer_shouldPutPlayerPaulInPenaltyBoxAndCurrentPlayerIsJean() {
        // Given
        Game game = new Game();
        game.add("Paul");
        game.add("Jean");
        game.roll(3);

        // When
        game.wrongAnswer();

        // Then
        assertThat(game.inPenaltyBox[0]).isTrue();
        assertThat(game.currentPlayer).isEqualTo(1);
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Jean was added\n" +
                "They are player number 2\n" +
                "Paul is the current player\n" +
                "They have rolled a 3\n" +
                "Paul's new location is 3\n" +
                "The category is Rock\n" +
                "Rock Question 0\n" +
                "Question was incorrectly answered\n" +
                "Paul was sent to the penalty box\n");
    }

    @Test
    public void wrongAnswer_shouldPutPlayerJeanInPenaltyBoxAndCurrentPlayerIsPaul() {
        // Given
        Game game = new Game();
        game.add("Paul");
        game.add("Jean");
        game.roll(3);
        game.wasCorrectlyAnswered();

        // When
        game.wrongAnswer();

        // Then
        assertThat(game.inPenaltyBox[0]).isFalse();
        assertThat(game.inPenaltyBox[1]).isTrue();
        assertThat(game.currentPlayer).isEqualTo(0);
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Jean was added\n" +
                "They are player number 2\n" +
                "Paul is the current player\n" +
                "They have rolled a 3\n" +
                "Paul's new location is 3\n" +
                "The category is Rock\n" +
                "Rock Question 0\n" +
                "Answer was corrent!!!!\n" +
                "Paul now has 1 Gold Coins.\n" +
                "Question was incorrectly answered\n" +
                "Jean was sent to the penalty box\n");
    }

    @Test
    public void wasCorrectlyAnswered_shouldAddOnePurseAndWinner() {
        // Given
        Game game = new Game();
        game.add("Paul");
        game.roll(3);

        // When
        boolean hasWinner = game.wasCorrectlyAnswered();

        // Then
        assertThat(game.purses[0]).isEqualTo(1);
        assertThat(game.currentPlayer).isEqualTo(0);
        assertThat(hasWinner).isTrue();
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 3\n" +
                "Paul's new location is 3\n" +
                "The category is Rock\n" +
                "Rock Question 0\n" +
                "Answer was corrent!!!!\n" +
                "Paul now has 1 Gold Coins.\n");
    }

    @Test
    public void wasCorrectlyAnswered_shouldAddSixPurseAndNotWinner() {
        // Given
        Game game = new Game();
        game.add("Paul");
        game.add("July");

        game.roll(1);
        game.wasCorrectlyAnswered();
        game.roll(1);
        game.wasCorrectlyAnswered();
        game.roll(1);
        game.wasCorrectlyAnswered();
        game.roll(1);
        game.wasCorrectlyAnswered();
        game.roll(1);
        game.wasCorrectlyAnswered();
        game.roll(1);
        game.wasCorrectlyAnswered();
        game.roll(1);
        game.wasCorrectlyAnswered();
        game.roll(1);
        game.wasCorrectlyAnswered();
        game.roll(1);
        game.wasCorrectlyAnswered();
        game.roll(1);
        game.wasCorrectlyAnswered();
        game.roll(1);

        // When
        boolean hasWinner = game.wasCorrectlyAnswered();

        // Then
        assertThat(game.purses[0]).isEqualTo(6);
        assertThat(game.currentPlayer).isEqualTo(1);
        assertThat(hasWinner).isFalse();
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "July was added\n" +
                "They are player number 2\n" +
                "Paul is the current player\n" +
                "They have rolled a 1\n" +
                "Paul's new location is 1\n" +
                "The category is Science\n" +
                "Science Question 0\n" +
                "Answer was corrent!!!!\n" +
                "Paul now has 1 Gold Coins.\n" +
                "July is the current player\n" +
                "They have rolled a 1\n" +
                "July's new location is 1\n" +
                "The category is Science\n" +
                "Science Question 1\n" +
                "Answer was corrent!!!!\n" +
                "July now has 1 Gold Coins.\n" +
                "Paul is the current player\n" +
                "They have rolled a 1\n" +
                "Paul's new location is 2\n" +
                "The category is Sports\n" +
                "Sports Question 0\n" +
                "Answer was corrent!!!!\n" +
                "Paul now has 2 Gold Coins.\n" +
                "July is the current player\n" +
                "They have rolled a 1\n" +
                "July's new location is 2\n" +
                "The category is Sports\n" +
                "Sports Question 1\n" +
                "Answer was corrent!!!!\n" +
                "July now has 2 Gold Coins.\n" +
                "Paul is the current player\n" +
                "They have rolled a 1\n" +
                "Paul's new location is 3\n" +
                "The category is Rock\n" +
                "Rock Question 0\n" +
                "Answer was corrent!!!!\n" +
                "Paul now has 3 Gold Coins.\n" +
                "July is the current player\n" +
                "They have rolled a 1\n" +
                "July's new location is 3\n" +
                "The category is Rock\n" +
                "Rock Question 1\n" +
                "Answer was corrent!!!!\n" +
                "July now has 3 Gold Coins.\n" +
                "Paul is the current player\n" +
                "They have rolled a 1\n" +
                "Paul's new location is 4\n" +
                "The category is Pop\n" +
                "Pop Question 0\n" +
                "Answer was corrent!!!!\n" +
                "Paul now has 4 Gold Coins.\n" +
                "July is the current player\n" +
                "They have rolled a 1\n" +
                "July's new location is 4\n" +
                "The category is Pop\n" +
                "Pop Question 1\n" +
                "Answer was corrent!!!!\n" +
                "July now has 4 Gold Coins.\n" +
                "Paul is the current player\n" +
                "They have rolled a 1\n" +
                "Paul's new location is 5\n" +
                "The category is Science\n" +
                "Science Question 2\n" +
                "Answer was corrent!!!!\n" +
                "Paul now has 5 Gold Coins.\n" +
                "July is the current player\n" +
                "They have rolled a 1\n" +
                "July's new location is 5\n" +
                "The category is Science\n" +
                "Science Question 3\n" +
                "Answer was corrent!!!!\n" +
                "July now has 5 Gold Coins.\n" +
                "Paul is the current player\n" +
                "They have rolled a 1\n" +
                "Paul's new location is 6\n" +
                "The category is Sports\n" +
                "Sports Question 2\n" +
                "Answer was corrent!!!!\n" +
                "Paul now has 6 Gold Coins.\n");
    }

    @Test
    public void wasCorrectlyAnswered_shouldAddPurseAndWinner_withOutOfPenaltyBox() {
        // Given
        Game game = new Game();
        game.add("Paul");
        game.roll(3);
        game.wrongAnswer();
        game.roll(3);

        // When
        boolean hasWinner = game.wasCorrectlyAnswered();

        // Then
        assertThat(game.purses[0]).isEqualTo(1);
        assertThat(game.currentPlayer).isEqualTo(0);
        assertThat(hasWinner).isTrue();
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 3\n" +
                "Paul's new location is 3\n" +
                "The category is Rock\n" +
                "Rock Question 0\n" +
                "Question was incorrectly answered\n" +
                "Paul was sent to the penalty box\n" +
                "Paul is the current player\n" +
                "They have rolled a 3\n" +
                "Paul is getting out of the penalty box\n" +
                "Paul's new location is 6\n" +
                "The category is Sports\n" +
                "Sports Question 0\n" +
                "Answer was correct!!!!\n" +
                "Paul now has 1 Gold Coins.\n");
    }

    @Test
    public void wasCorrectlyAnswered_shouldReturnTrue_withNotOutOfPenaltyBox() {
        // Given
        Game game = new Game();
        game.add("Paul");
        game.roll(3);
        game.wrongAnswer();
        game.roll(2);

        // When
        boolean hasWinner = game.wasCorrectlyAnswered();

        // Then
        assertThat(game.purses[0]).isEqualTo(0);
        assertThat(game.currentPlayer).isEqualTo(0);
        assertThat(hasWinner).isTrue();
        assertThat(outContent.toString()).isEqualTo(
                "Paul was added\n" +
                "They are player number 1\n" +
                "Paul is the current player\n" +
                "They have rolled a 3\n" +
                "Paul's new location is 3\n" +
                "The category is Rock\n" +
                "Rock Question 0\n" +
                "Question was incorrectly answered\n" +
                "Paul was sent to the penalty box\n" +
                "Paul is the current player\n" +
                "They have rolled a 2\n" +
                "Paul is not getting out of the penalty box\n");
    }

}