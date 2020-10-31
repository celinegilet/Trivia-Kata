package com.adaptionsoft.games.uglytrivia;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    public void isWin_shouldReturnTrue_whenPursesPlayerIs6() {
        // Given
        Player player = new Player("Paul");
        player.setPurses(6);

        // When
        boolean isWin = player.isWin();

        // Then
        assertThat(isWin).isTrue();
    }

    @Test
    public void isWin_shouldReturnFalse_whenPursesPlayerIsLessThan6() {
        // Given
        Player player = new Player("Paul");
        player.setPurses(3);

        // When
        boolean isWin = player.isWin();

        // Then
        assertThat(isWin).isFalse();
    }

    @Test
    public void addPlaces_shouldAddRoll_whenPlaceIsLessThan12() {
        // Given
        Player player = new Player("Paul");

        // When
        player.addPlaces(3);

        // Then
        assertThat(player.getPlaces()).isEqualTo(3);
    }

    @Test
    public void addPlaces_shouldResetRoll_whenPlace12() {
        // Given
        Player player = new Player("Paul");
        player.setPlaces(11);

        // When
        player.addPlaces(1);

        // Then
        assertThat(player.getPlaces()).isEqualTo(0);
    }


}
