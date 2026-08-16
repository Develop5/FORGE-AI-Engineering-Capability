package com.forge.capabilities.improvement;

import com.forge.domain.coverage.CoverageResult;
import com.forge.domain.coverage.ProjectedCoverage;
import com.forge.domain.testcase.GeneratedTestCase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class LocalProjectedCoverage {

    public ProjectedCoverage calculate(
            CoverageResult currentCoverage,
            List<GeneratedTestCase> generatedTestCases) {

        Set<String> coveredRequirementIds =
                new HashSet<>(
                        currentCoverage.businessRequirements().stream()
                                .map(requirement -> requirement.id())
                                .filter(requirementId ->
                                        !currentCoverage.uncoveredRequirementIds()
                                                .contains(requirementId))
                                .toList());

        generatedTestCases.forEach(
                testCase -> coveredRequirementIds.addAll(
                        testCase.targetRequirementIds()));

        List<String> allRequirementIds =
                currentCoverage.businessRequirements().stream()
                        .map(requirement -> requirement.id())
                        .toList();

        List<String> projectedCoveredRequirementIds =
                allRequirementIds.stream()
                        .filter(coveredRequirementIds::contains)
                        .toList();

        List<String> projectedUncoveredRequirementIds =
                allRequirementIds.stream()
                        .filter(requirementId ->
                                !coveredRequirementIds.contains(requirementId))
                        .toList();

        double projectedCoverage =
                calculateCoverage(
                        projectedCoveredRequirementIds.size(),
                        allRequirementIds.size());

        return new ProjectedCoverage(
                projectedCoveredRequirementIds,
                projectedUncoveredRequirementIds,
                projectedCoverage);
    }

    private static double calculateCoverage(
            int coveredRequirements,
            int totalRequirements) {

        if (totalRequirements == 0) {
            return 0.0;
        }

        return (double) coveredRequirements
                / totalRequirements
                * 100.0;
    }
}