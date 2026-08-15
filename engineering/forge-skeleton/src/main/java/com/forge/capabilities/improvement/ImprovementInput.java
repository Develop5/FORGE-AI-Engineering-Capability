package com.forge.capabilities.improvement;

import com.forge.domain.coverage.CoverageResult;
import com.forge.domain.requirement.BusinessRequirement;
import com.forge.domain.testcase.TestCase;

import java.util.List;
import java.util.Objects;

public record ImprovementInput(
        List<BusinessRequirement> businessRequirements,
        List<TestCase> existingTestCases,
        CoverageResult coverageResult) {

    public ImprovementInput {
        Objects.requireNonNull(
                businessRequirements,
                "businessRequirements must not be null");

        Objects.requireNonNull(
                existingTestCases,
                "existingTestCases must not be null");

        Objects.requireNonNull(
                coverageResult,
                "coverageResult must not be null");

        businessRequirements = List.copyOf(businessRequirements);
        existingTestCases = List.copyOf(existingTestCases);
    }
}