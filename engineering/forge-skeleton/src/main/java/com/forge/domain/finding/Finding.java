package com.forge.domain.finding;

import java.util.List;
import java.util.Objects;

public final class Finding {

    private final String id;
    private final String type;
    private final String description;
    private final List<String> relatedRequirementIds;

    public Finding(
            String id,
            String type,
            String description,
            List<String> relatedRequirementIds) {

        this.id = requireNonBlank(id, "id");
        this.type = requireNonBlank(type, "type");
        this.description = requireNonBlank(description, "description");
        this.relatedRequirementIds = List.copyOf(
                Objects.requireNonNull(
                        relatedRequirementIds,
                        "relatedRequirementIds must not be null"));
    }

    public String id() {
        return id;
    }

    public String type() {
        return type;
    }

    public String description() {
        return description;
    }

    public List<String> relatedRequirementIds() {
        return relatedRequirementIds;
    }

    private static String requireNonBlank(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " must not be null");

        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }

        return value;
    }
}