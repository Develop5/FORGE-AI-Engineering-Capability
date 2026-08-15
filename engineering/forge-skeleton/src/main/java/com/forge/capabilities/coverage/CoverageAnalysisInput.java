package com.forge.capabilities.coverage;

import com.forge.domain.requirement.BusinessRequirement;
import com.forge.domain.testcase.TestCase;
import com.forge.domain.traceability.RequirementTestCaseRelation;
import com.forge.domain.finding.Risk;

import java.util.List;
import java.util.Objects;

public record CoverageAnalysisInput(
        List<BusinessRequirement> businessRequirements,
        List<TestCase> testCases,
        List<RequirementTestCaseRelation> traceabilityRelations,
        List<Risk> risks) {

    public CoverageAnalysisInput {
        Objects.requireNonNull(
                businessRequirements,
                "businessRequirements must not be null");

        Objects.requireNonNull(
                testCases,
                "testCases must not be null");

        Objects.requireNonNull(
                traceabilityRelations,
                "traceabilityRelations must not be null");

        Objects.requireNonNull(
                risks,
                "risks must not be null");

        businessRequirements = List.copyOf(businessRequirements);
        testCases = List.copyOf(testCases);
        traceabilityRelations = List.copyOf(traceabilityRelations);
        risks = List.copyOf(risks);
    }
}