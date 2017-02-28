package com.adaptionsoft.games.uglytrivia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static com.adaptionsoft.games.uglytrivia.Category.values;

public class QuestionSet {

    Map<Category, LinkedList<String>> questions = new HashMap<>();

    public QuestionSet() {
        for (Category category : values()) {
            questions.put(category, new LinkedList<>());
            for (int i = 0; i < 50; i++) {
                questions.get(category).addLast(category.getLibelle() + " Question " + i);
            }
        }
    }

    public LinkedList<String> get(Category category) {
        return questions.get(category);
    }

    public String nextQuestion(Category category) {
        return questions.get(category).removeFirst();
    }
}
