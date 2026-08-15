package com.forge.capabilities.requirements;

import com.forge.domain.evidence.EvidenceTopic;

import java.util.List;
import java.util.Objects;

public record RequirementsDiscoveryInput(
        List<EvidenceTopic> evidenceTopics) {

    public RequirementsDiscoveryInput {
        Objects.requireNonNull(
                evidenceTopics,
                "evidenceTopics must not be null");

        evidenceTopics = List.copyOf(evidenceTopics);
    }
}