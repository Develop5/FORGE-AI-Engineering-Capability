package com.forge.capabilities.traceability;

import com.forge.domain.requirement.BusinessRequirement;
import com.forge.domain.testcase.TestCase;

import java.util.List;
import java.util.Objects;

public record TraceabilityAnalysisInput(
        List<BusinessRequirement> businessRequirements,
        List<TestCase> testCases) {

    public TraceabilityAnalysisInput {
        Objects.requireNonNull(
                businessRequirements,
                "businessRequirements must not be null");

        Objects.requireNonNull(
                testCases,
                "testCases must not be null");

        businessRequirements = List.copyOf(businessRequirements);
        testCases = List.copyOf(testCases);
    }
}