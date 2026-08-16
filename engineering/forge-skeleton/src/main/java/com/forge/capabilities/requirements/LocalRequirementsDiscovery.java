package com.forge.capabilities.requirements;

import com.forge.domain.evidence.EvidenceTopic;
import com.forge.domain.requirement.BusinessRequirement;

import java.util.List;

public final class LocalRequirementsDiscovery
        implements RequirementsDiscoveryCapability {

    @Override
    public RequirementsDiscoveryOutput execute(
            RequirementsDiscoveryInput input) {

        List<BusinessRequirement> requirements =
                input.evidenceTopics()
                        .stream()
                        .map(this::toRequirement)
                        .toList();

        return new RequirementsDiscoveryOutput(
                requirements,
                List.of());
    }

    private BusinessRequirement toRequirement(
            EvidenceTopic topic) {

        return new BusinessRequirement(
                topic.name(),
                topic.name(),
                topic.information(),
                "LOCAL",
                List.of());
    }
}