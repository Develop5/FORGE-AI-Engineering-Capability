package com.forge.domain.requirement;

import java.util.List;
import java.util.Objects;

public final class BusinessRequirement {

    private final String id;
    private final String title;
    private final String description;
    private final String priority;
    private final List<String> acceptanceCriteria;

    public BusinessRequirement(
            String id,
            String title,
            String description,
            String priority,
            List<String> acceptanceCriteria) {

        this.id = requireNonBlank(id, "id");
        this.title = requireNonBlank(title, "title");
        this.description = requireNonBlank(description, "description");
        this.priority = requireNonBlank(priority, "priority");
        this.acceptanceCriteria = List.copyOf(
                Objects.requireNonNull(
                        acceptanceCriteria,
                        "acceptanceCriteria must not be null"));
    }

    public String id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public String priority() {
        return priority;
    }

    public List<String> acceptanceCriteria() {
        return acceptanceCriteria;
    }

    private static String requireNonBlank(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " must not be null");

        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }

        return value;
    }
}