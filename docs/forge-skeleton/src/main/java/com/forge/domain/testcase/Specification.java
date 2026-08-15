package com.forge.domain.testcase;

import java.util.Objects;

public final class Specification {

    private final String id;
    private final String description;
    private final String relatedRequirementId;

    public Specification(
            String id,
            String description,
            String relatedRequirementId) {

        this.id = requireNonBlank(id, "id");
        this.description = requireNonBlank(description, "description");
        this.relatedRequirementId =
                requireNonBlank(relatedRequirementId, "relatedRequirementId");
    }

    public String id() {
        return id;
    }

    public String description() {
        return description;
    }

    public String relatedRequirementId() {
        return relatedRequirementId;
    }

    // Método privado de esta propia clase; no requiere import.
    private static String requireNonBlank(
            String value,
            String fieldName) {

        Objects.requireNonNull(value, fieldName + " must not be null");

        if (value.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName + " must not be blank");
        }

        return value;
    }
}