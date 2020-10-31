package com.adaptionsoft.games.uglytrivia;

import org.junit.jupiter.api.Test;

import static com.adaptionsoft.games.uglytrivia.Category.*;
import static org.assertj.core.api.Assertions.assertThat;

public class QuestionSetTest {

    @Test
    public void initQuestionSet() {
        // When
        QuestionSet questionSet = new QuestionSet();
        // Then
        assertThat(questionSet.questions.size()).isEqualTo(Category.values().length);
        assertThat(questionSet.get(POP).size()).isEqualTo(50);
        assertThat(questionSet.get(ROCK).size()).isEqualTo(50);
        assertThat(questionSet.get(SPORTS).size()).isEqualTo(50);
        assertThat(questionSet.get(SCIENCE).size()).isEqualTo(50);
    }

    @Test
    public void nextQuestion_ShouldReturnQuestion0() {
        // Given
        QuestionSet questionSet = new QuestionSet();

        // When
        String question = questionSet.nextQuestion(POP);

        // Then
        assertThat(question).isEqualTo("Pop Question 0");
    }

}
