package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

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
    }

}