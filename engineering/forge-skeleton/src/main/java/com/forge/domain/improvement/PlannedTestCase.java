package com.forge.domain.improvement;

import java.util.List;
import java.util.Objects;

public record PlannedTestCase(
        String id,
        String title,
        List<String> targetRequirementIds) {

    public PlannedTestCase {
        id = requireNonBlank(id, "id");
        title = requireNonBlank(title, "title");

        Objects.requireNonNull(
                targetRequirementIds,
                "targetRequirementIds must not be null");

        targetRequirementIds = List.copyOf(targetRequirementIds);

        if (targetRequirementIds.isEmpty()) {
            throw new IllegalArgumentException(
                    "targetRequirementIds must not be empty");
        }
    }

    private static String requireNonBlank(
            String value,
            String fieldName) {

        Objects.requireNonNull(
                value,
                fieldName + " must not be null");

        if (value.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName + " must not be blank");
        }

        return value;
    }
}