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

        String requirementId =
                "BR-" + topic.evidenceReferences().get(0);

        return new BusinessRequirement(
                requirementId,
                topic.name(),
                topic.information(),
                "LOCAL",
                List.of());
    }
}