package com.forge.capabilities.evidence;

import com.forge.domain.evidence.EvidenceTopic;

import java.util.List;
import java.util.Objects;

public record EvidenceConsolidationOutput(
        List<EvidenceTopic> evidenceTopics) {

    public EvidenceConsolidationOutput {
        Objects.requireNonNull(
                evidenceTopics,
                "evidenceTopics must not be null");

        evidenceTopics = List.copyOf(evidenceTopics);
    }
}