package com.forge.domain.evidence;

import java.util.Objects;

public final class Evidence {

    private final String id;
    private final String content;
    private final String sourceReference;

    public Evidence(
            String id,
            String content,
            String sourceReference) {

        this.id = requireNonBlank(id, "id");
        this.content = requireNonBlank(content, "content");
        this.sourceReference = requireNonBlank(sourceReference, "sourceReference");
    }

    public String id() {
        return id;
    }

    public String content() {
        return content;
    }

    public String sourceReference() {
        return sourceReference;
    }

    private static String requireNonBlank(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " must not be null");

        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }

        return value;
    }
}