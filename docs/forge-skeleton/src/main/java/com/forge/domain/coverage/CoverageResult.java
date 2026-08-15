package com.forge.domain.coverage;

import com.forge.domain.finding.Risk;
import com.forge.domain.requirement.BusinessRequirement;
import com.forge.domain.testcase.TestCase;

import java.util.List;
import java.util.Objects;

public final class CoverageResult {

    private final List<BusinessRequirement> businessRequirements;
    private final List<TestCase> relatedTestCases;
    private final double currentCoverage;
    private final List<String> uncoveredRequirementIds;
    private final List<Risk> risks;

    public CoverageResult(
            List<BusinessRequirement> businessRequirements,
            List<TestCase> relatedTestCases,
            double currentCoverage,
            List<String> uncoveredRequirementIds,
            List<Risk> risks) {

        this.businessRequirements = copyList(
                businessRequirements,
                "businessRequirements");

        this.relatedTestCases = copyList(
                relatedTestCases,
                "relatedTestCases");

        if (currentCoverage < 0.0 || currentCoverage > 100.0) {
            throw new IllegalArgumentException(
                    "currentCoverage must be between 0 and 100");
        }

        this.currentCoverage = currentCoverage;

        this.uncoveredRequirementIds = copyList(
                uncoveredRequirementIds,
                "uncoveredRequirementIds");

        this.risks = copyList(
                risks,
                "risks");
    }

    public List<BusinessRequirement> businessRequirements() {
        return businessRequirements;
    }

    public List<TestCase> relatedTestCases() {
        return relatedTestCases;
    }

    public double currentCoverage() {
        return currentCoverage;
    }

    public List<String> uncoveredRequirementIds() {
        return uncoveredRequirementIds;
    }

    public List<Risk> risks() {
        return risks;
    }

    private static <T> List<T> copyList(
            List<T> value,
            String fieldName) {

        Objects.requireNonNull(
                value,
                fieldName + " must not be null");

        return List.copyOf(value);
    }
}