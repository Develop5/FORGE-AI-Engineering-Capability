package com.forge.domain.traceability;

import java.util.Objects;

public final class RequirementTestCaseRelation {

    private final String requirementId;
    private final String testCaseId;
    private final RelationType relationType;

    public RequirementTestCaseRelation(
            String requirementId,
            String testCaseId,
            RelationType relationType) {

        this.requirementId = requireNonBlank(
                requirementId,
                "requirementId");

        this.testCaseId = requireNonBlank(
                testCaseId,
                "testCaseId");

        this.relationType = Objects.requireNonNull(
                relationType,
                "relationType must not be null");
    }

    public String requirementId() {
        return requirementId;
    }

    public String testCaseId() {
        return testCaseId;
    }

    public RelationType relationType() {
        return relationType;
    }

    // Método privado de esta propia clase; no requiere import.
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