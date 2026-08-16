package com.forge.capabilities.clarification;

import com.forge.domain.clarification.Question;
import com.forge.domain.finding.Finding;
import com.forge.domain.requirement.BusinessRequirement;

import java.util.List;
import java.util.Objects;

public record ClarificationInput(
        List<BusinessRequirement> businessRequirements,
        List<Finding> findings,
        Question question,
        String response) {

    public ClarificationInput {
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