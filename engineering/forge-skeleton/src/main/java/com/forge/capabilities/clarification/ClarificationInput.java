package com.forge.capabilities.clarification;

import com.forge.domain.finding.Finding;

import java.util.List;
import java.util.Objects;

public record ClarificationInput(
        List<Finding> findings) {

    public ClarificationInput {
        Objects.requireNonNull(
                findings,
                "findings must not be null");

        findings = List.copyOf(findings);
    }
}