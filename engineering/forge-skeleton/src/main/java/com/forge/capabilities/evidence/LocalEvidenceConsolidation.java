package com.forge.capabilities.evidence;

import com.forge.domain.evidence.Evidence;
import com.forge.domain.evidence.EvidenceTopic;

import java.util.List;

public final class LocalEvidenceConsolidation
        implements EvidenceConsolidationCapability {

    @Override
    public EvidenceConsolidationOutput execute(
            EvidenceConsolidationInput input) {

        List<EvidenceTopic> topics = input.evidence()
                .stream()
                .map(this::toTopic)
                .toList();

        return new EvidenceConsolidationOutput(topics);
    }

    private EvidenceTopic toTopic(Evidence evidence) {
        return new EvidenceTopic(
                evidence.id(),
                evidence.title(),
                evidence.content());
    }
}