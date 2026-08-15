package com.forge.domain.finding;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class Risk {

    private final String id;
    private final String description;
    private final List<String> relatedRequirementIds;
    private final String sourceFindingId;

    public Risk(
            String id,
            String description,
            List<String> relatedRequirementIds,
            String sourceFindingId) {

        this.id = requireNonBlank(id, "id");
        this.description = requireNonBlank(description, "description");
        this.relatedRequirementIds = List.copyOf(
                Objects.requireNonNull(
                        relatedRequirementIds,
                        "relatedRequirementIds must not be null"));
        this.sourceFindingId = sourceFindingId;
    }

    public String id() {
        return id;
    }

    public String description() {
        return description;
    }

    public List<String> relatedRequirementIds() {
        return relatedRequirementIds;
    }

    public Optional<String> sourceFindingId() {
        return Optional.ofNullable(sourceFindingId);
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