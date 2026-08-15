package com.forge.domain.evidence;

import java.util.List;
import java.util.Objects;

public final class EvidenceTopic {

    private final String name;
    private final String information;
    private final List<String> evidenceReferences;

    public EvidenceTopic(
            String name,
            String information,
            List<String> evidenceReferences) {

        this.name = requireNonBlank(name, "name");
        this.information = requireNonBlank(information, "information");
        this.evidenceReferences = List.copyOf(
                Objects.requireNonNull(
                        evidenceReferences,
                        "evidenceReferences must not be null"));
    }

    public String name() {
        return name;
    }

    public String information() {
        return information;
    }

    public List<String> evidenceReferences() {
        return evidenceReferences;
    }

    private static String requireNonBlank(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " must not be null");

        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }

        return value;
    }
}