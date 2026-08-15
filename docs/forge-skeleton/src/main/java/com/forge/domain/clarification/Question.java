package com.forge.domain.clarification;

import java.util.Objects;

public final class Question {

    private final String id;
    private final String question;
    private final String findingId;

    public Question(
            String id,
            String question,
            String findingId) {

        this.id = requireNonBlank(id, "id");
        this.question = requireNonBlank(question, "question");
        this.findingId = findingId;
    }

    public String id() {
        return id;
    }

    public String question() {
        return question;
    }

    public String findingId() {
        return findingId;
    }

    // Método privado de esta propia clase; no requiere import.
    private static String requireNonBlank(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " must not be null");

        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }

        return value;
    }
}