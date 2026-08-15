package com.forge.domain.testcase;

import java.util.List;
import java.util.Objects;

public final class TestCase {

    private final String id;
    private final String title;
    private final String description;
    private final List<String> conditions;
    private final List<String> inputs;
    private final List<String> steps;
    private final String expectedResult;
    private final String sourceReference;

    public TestCase(
            String id,
            String title,
            String description,
            List<String> conditions,
            List<String> inputs,
            List<String> steps,
            String expectedResult,
            String sourceReference) {

        this.id = requireNonBlank(id, "id");
        this.title = requireNonBlank(title, "title");
        this.description = requireNonBlank(description, "description");
        this.conditions = copyList(conditions, "conditions");
        this.inputs = copyList(inputs, "inputs");
        this.steps = copyList(steps, "steps");
        this.expectedResult = requireNonBlank(expectedResult, "expectedResult");
        this.sourceReference = requireNonBlank(sourceReference, "sourceReference");
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

    public List<String> conditions() {
        return conditions;
    }

    public List<String> inputs() {
        return inputs;
    }

    public List<String> steps() {
        return steps;
    }

    public String expectedResult() {
        return expectedResult;
    }

    public String sourceReference() {
        return sourceReference;
    }

    private static List<String> copyList(
            List<String> value,
            String fieldName) {

        Objects.requireNonNull(value, fieldName + " must not be null");
        return List.copyOf(value);
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