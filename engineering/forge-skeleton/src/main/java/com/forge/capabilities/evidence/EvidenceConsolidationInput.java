package com.forge.capabilities.evidence;

import com.forge.domain.evidence.Evidence;

import java.util.List;
import java.util.Objects;

public record EvidenceConsolidationInput(
        List<Evidence> evidence) {

    public EvidenceConsolidationInput {
        Objects.requireNonNull(
                evidence,
                "evidence must not be null");

        evidence = List.copyOf(evidence);
    }
}