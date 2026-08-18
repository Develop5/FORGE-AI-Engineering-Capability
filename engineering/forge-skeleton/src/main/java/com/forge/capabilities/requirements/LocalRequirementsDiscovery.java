package com.forge.capabilities.requirements;

import com.forge.domain.evidence.EvidenceTopic;
import com.forge.domain.finding.Finding;
import com.forge.domain.requirement.BusinessRequirement;

import java.util.ArrayList;
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

        List<Finding> findings =
                new ArrayList<>();

        for (BusinessRequirement requirement : requirements) {
            if (isAmbiguous(requirement)) {
                findings.add(
                        new Finding(
                                "FINDING-" + requirement.id(),
                                "AMBIGUITY",
                                "The requirement is ambiguous and requires clarification.",
                                List.of(requirement.id())));
            }
        }

        return new RequirementsDiscoveryOutput(
                requirements,
                findings);
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

    private boolean isAmbiguous(
            BusinessRequirement requirement) {

        String description =
                requirement.description().toLowerCase();

        return description.contains("some")
                || description.contains("appropriate")
                || description.contains("suitable")
                || description.contains("as needed")
                || description.contains("etc.");
    }
}