package com.adaptionsoft.games.uglytrivia;

import java.util.List;

import static java.util.Arrays.asList;

public enum Category {

    POP("Pop", asList(0, 4, 8)),
    SCIENCE("Science", asList(1, 5, 9)),
    SPORTS("Sports", asList(2, 6, 10)),
    ROCK("Rock", asList());

    private final String libelle;
    private final List<Integer> places;

    Category(String libelle, List<Integer> places) {
        this.libelle = libelle;
        this.places = places;
    }

    public String getLibelle() {
        return libelle;
    }

    public static Category getCategory(int place) {
        for (Category category : Category.values()) {
            if (category.places.contains(place)) {
                return category;
            }
        }
        return ROCK;
    }
}
