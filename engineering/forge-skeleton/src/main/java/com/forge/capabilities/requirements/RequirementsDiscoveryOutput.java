package com.forge.capabilities.requirements;

import com.forge.domain.finding.Finding;
import com.forge.domain.requirement.BusinessRequirement;

import java.util.List;
import java.util.Objects;

public record RequirementsDiscoveryOutput(
        List<BusinessRequirement> businessRequirements,
        List<Finding> findings) {

    public RequirementsDiscoveryOutput {
        Objects.requireNonNull(
                businessRequirements,
                "businessRequirements must not be null");

        Objects.requireNonNull(
                findings,
                "findings must not be null");

        businessRequirements = List.copyOf(businessRequirements);
        findings = List.copyOf(findings);
    }
}