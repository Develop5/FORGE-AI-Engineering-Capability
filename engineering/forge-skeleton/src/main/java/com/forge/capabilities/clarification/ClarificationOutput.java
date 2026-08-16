package com.forge.capabilities.clarification;

import com.forge.domain.clarification.Question;
import com.forge.domain.finding.Finding;
import com.forge.domain.finding.Risk;
import com.forge.domain.requirement.BusinessRequirement;

import java.util.List;
import java.util.Objects;

public record ClarificationOutput(
        List<BusinessRequirement> businessRequirements,
        List<Finding> findings,
        List<Question> questions,
        List<Risk> risks) {

    public ClarificationOutput {
        Objects.requireNonNull(
                businessRequirements,
                "businessRequirements must not be null");

        Objects.requireNonNull(
                findings,
                "findings must not be null");

        Objects.requireNonNull(
                questions,
                "questions must not be null");

        Objects.requireNonNull(
                risks,
                "risks must not be null");

        businessRequirements = List.copyOf(businessRequirements);
        findings = List.copyOf(findings);
        questions = List.copyOf(questions);
        risks = List.copyOf(risks);
    }
}