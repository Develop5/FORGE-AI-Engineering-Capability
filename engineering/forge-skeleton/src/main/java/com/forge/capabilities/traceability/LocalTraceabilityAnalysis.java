package com.forge.capabilities.traceability;

import com.forge.domain.requirement.BusinessRequirement;
import com.forge.domain.testcase.TestCase;
import com.forge.domain.traceability.RelationType;
import com.forge.domain.traceability.RequirementTestCaseRelation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class LocalTraceabilityAnalysis
        implements TraceabilityAnalysisCapability {

    @Override
    public TraceabilityAnalysisOutput execute(
            TraceabilityAnalysisInput input) {

        Objects.requireNonNull(
                input,
                "input must not be null");

        List<RequirementTestCaseRelation> relations =
                new ArrayList<>();

        for (BusinessRequirement requirement
                : input.businessRequirements()) {

            for (TestCase testCase
                    : input.testCases()) {

                if (covers(requirement, testCase)) {
                    relations.add(
                            new RequirementTestCaseRelation(
                                    requirement.id(),
                                    testCase.id(),
                                    RelationType.COVERS));
                }
            }
        }

        return new TraceabilityAnalysisOutput(relations);
    }

    private boolean covers(
            BusinessRequirement requirement,
            TestCase testCase) {

        if (testCase.sourceReference() == null) {
            return false;
        }

        return testCase.sourceReference()
                .equals(requirement.id());
    }
}