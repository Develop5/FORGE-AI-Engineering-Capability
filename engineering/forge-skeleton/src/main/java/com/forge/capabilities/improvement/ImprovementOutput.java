package com.forge.capabilities.improvement;

import com.forge.domain.coverage.ProjectedCoverage;
import com.forge.domain.improvement.NonGeneratedTestCase;
import com.forge.domain.improvement.PlannedTestCase;
import com.forge.domain.testcase.GeneratedTestCase;

import java.util.List;
import java.util.Objects;

public record ImprovementOutput(
        List<PlannedTestCase> plannedTestCases,
        List<GeneratedTestCase> generatedTestCases,
        List<NonGeneratedTestCase> nonGeneratedTestCases,
        ProjectedCoverage projectedCoverage,
        boolean targetAchieved,
        List<String> targetFailureReasons) {

    public ImprovementOutput {
        Objects.requireNonNull(
                plannedTestCases,
                "plannedTestCases must not be null");

        Objects.requireNonNull(
                generatedTestCases,
                "generatedTestCases must not be null");

        Objects.requireNonNull(
                nonGeneratedTestCases,
                "nonGeneratedTestCases must not be null");

        Objects.requireNonNull(
                projectedCoverage,
                "projectedCoverage must not be null");

        Objects.requireNonNull(
                targetFailureReasons,
                "targetFailureReasons must not be null");

        plannedTestCases = List.copyOf(plannedTestCases);
        generatedTestCases = List.copyOf(generatedTestCases);
        nonGeneratedTestCases = List.copyOf(nonGeneratedTestCases);
        targetFailureReasons = List.copyOf(targetFailureReasons);

        if (targetAchieved && !targetFailureReasons.isEmpty()) {
            throw new IllegalArgumentException(
                    "targetFailureReasons must be empty when target is achieved");
        }

        if (!targetAchieved && targetFailureReasons.isEmpty()) {
            throw new IllegalArgumentException(
                    "targetFailureReasons must not be empty when target is not achieved");
        }
    }
}