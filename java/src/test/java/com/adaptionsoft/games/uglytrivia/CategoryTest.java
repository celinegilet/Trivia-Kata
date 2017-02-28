package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import static com.adaptionsoft.games.uglytrivia.Category.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CategoryTest {

    @Test
    public void init_categoryPop() {
        // When
        String libelle = POP.getLibelle();

        // Then
        assertThat(libelle).isEqualTo("Pop");
    }

    @Test
    public void getCategory_shouldReturnPop_whenPlaceIs0() {
        // Given
        int place = 0;

        // When
        Category category = getCategory(place);

        // Then
        assertThat(category).isEqualTo(POP);
    }

    @Test
    public void getCategory_shouldReturnPop_whenPlaceIs4() {
        // Given
        int place = 4;

        // When
        Category category = getCategory(place);

        // Then
        assertThat(category).isEqualTo(POP);
    }

    @Test
    public void getCategory_shouldReturnPop_whenPlaceIs8() {
        // Given
        int place = 8;

        // When
        Category category = getCategory(place);

        // Then
        assertThat(category).isEqualTo(POP);
    }

    @Test
    public void getCategory_shouldReturnPop_whenPlaceIs1() {
        // Given
        int place = 1;

        // When
        Category category = getCategory(place);

        // Then
        assertThat(category).isEqualTo(SCIENCE);
    }

    @Test
    public void getCategory_shouldReturnPop_whenPlaceIs5() {
        // Given
        int place = 5;

        // When
        Category category = getCategory(place);

        // Then
        assertThat(category).isEqualTo(SCIENCE);
    }

    @Test
    public void getCategory_shouldReturnPop_whenPlaceIs9() {
        // Given
        int place = 9;

        // When
        Category category = getCategory(place);

        // Then
        assertThat(category).isEqualTo(SCIENCE);
    }

    @Test
    public void getCategory_shouldReturnPop_whenPlaceIs2() {
        // Given
        int place = 2;

        // When
        Category category = getCategory(place);

        // Then
        assertThat(category).isEqualTo(SPORTS);
    }

    @Test
    public void getCategory_shouldReturnPop_whenPlaceIs6() {
        // Given
        int place = 6;

        // When
        Category category = getCategory(place);

        // Then
        assertThat(category).isEqualTo(SPORTS);
    }

    @Test
    public void getCategory_shouldReturnPop_whenPlaceIs10() {
        // Given
        int place = 10;

        // When
        Category category = getCategory(place);

        // Then
        assertThat(category).isEqualTo(SPORTS);
    }

    @Test
    public void getCategory_shouldReturnPop_whenPlaceIs7() {
        // Given
        int place = 7;

        // When
        Category category = getCategory(place);

        // Then
        assertThat(category).isEqualTo(ROCK);
    }

}