package com.forge.domain.improvement;

import java.util.Objects;

public record NonGeneratedTestCase(
        PlannedTestCase plannedTestCase,
        String reason) {

    public NonGeneratedTestCase {
        Objects.requireNonNull(
                plannedTestCase,
                "plannedTestCase must not be null");

        reason = requireNonBlank(
                reason,
                "reason");
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