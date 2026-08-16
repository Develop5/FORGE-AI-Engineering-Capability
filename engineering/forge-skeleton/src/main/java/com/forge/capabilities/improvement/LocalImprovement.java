package com.forge.capabilities.improvement;

import com.forge.domain.coverage.ProjectedCoverage;
import com.forge.domain.improvement.NonGeneratedTestCase;
import com.forge.domain.improvement.PlannedTestCase;
import com.forge.domain.requirement.BusinessRequirement;
import com.forge.domain.testcase.GeneratedTestCase;

import java.util.ArrayList;
import java.util.List;

public final class LocalImprovement implements ImprovementCapability {

    private final LocalProjectedCoverage projectedCoverageCalculator;

    public LocalImprovement() {
        this(new LocalProjectedCoverage());
    }

    public LocalImprovement(
            LocalProjectedCoverage projectedCoverageCalculator) {

        if (projectedCoverageCalculator == null) {
            throw new NullPointerException(
                    "projectedCoverageCalculator must not be null");
        }

        this.projectedCoverageCalculator =
                projectedCoverageCalculator;
    }

    @Override
    public ImprovementOutput execute(ImprovementInput input) {

        List<PlannedTestCase> plannedTestCases =
                plan(input);

        List<GeneratedTestCase> generatedTestCases =
                new ArrayList<>();

        List<NonGeneratedTestCase> nonGeneratedTestCases =
                new ArrayList<>();

        for (PlannedTestCase plannedTestCase : plannedTestCases) {

            try {
                generatedTestCases.add(
                        generateTestCase(
                                input,
                                plannedTestCase));

            } catch (IllegalArgumentException exception) {

                nonGeneratedTestCases.add(
                        new NonGeneratedTestCase(
                                plannedTestCase,
                                exception.getMessage()));
            }
        }

        ProjectedCoverage projectedCoverage =
                projectedCoverageCalculator.calculate(
                        input.coverageResult(),
                        generatedTestCases);

        boolean targetAchieved =
                projectedCoverage.coveragePercentage()
                        >= input.requestedCoverageTarget();

        List<String> targetFailureReasons =
                targetAchieved
                        ? List.of()
                        : buildTargetFailureReasons(
                        input,
                        nonGeneratedTestCases,
                        projectedCoverage);

        return new ImprovementOutput(
                plannedTestCases,
                generatedTestCases,
                nonGeneratedTestCases,
                projectedCoverage,
                targetAchieved,
                targetFailureReasons);
    }

    private List<PlannedTestCase> plan(
            ImprovementInput input) {

        if (input.coverageResult().currentCoverage()
                >= input.requestedCoverageTarget()) {

            return List.of();
        }

        List<PlannedTestCase> plannedTestCases =
                new ArrayList<>();

        for (String requirementId :
                input.coverageResult()
                        .uncoveredRequirementIds()) {

            plannedTestCases.add(
                    new PlannedTestCase(
                            "PLAN-" + requirementId,
                            "Improve coverage for " + requirementId,
                            List.of(requirementId)));
        }

        return plannedTestCases;
    }

    private GeneratedTestCase generateTestCase(
            ImprovementInput input,
            PlannedTestCase plannedTestCase) {

        if (plannedTestCase.targetRequirementIds().isEmpty()) {
            throw new IllegalArgumentException(
                    "No target Business Requirement is available");
        }

        BusinessRequirement requirement =
                findRequirement(
                        input.businessRequirements(),
                        plannedTestCase.targetRequirementIds().get(0));

        if (requirement.description().isBlank()) {
            throw new IllegalArgumentException(
                    "Insufficient Business Requirement information");
        }

        return new GeneratedTestCase(
                "GEN-" + requirement.id(),
                "Generated test for " + requirement.id(),
                "Deterministic generated test case for business requirement "
                        + requirement.id(),
                List.of(),
                List.of(),
                List.of(
                        "Execute the business requirement scenario"),
                "The business requirement is satisfied",
                List.of(requirement.id()));
    }

    private BusinessRequirement findRequirement(
            List<BusinessRequirement> requirements,
            String requirementId) {

        return requirements.stream()
                .filter(
                        requirement ->
                                requirement.id()
                                        .equals(requirementId))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "Business Requirement not found: "
                                        + requirementId));
    }

    private List<String> buildTargetFailureReasons(
            ImprovementInput input,
            List<NonGeneratedTestCase> nonGeneratedTestCases,
            ProjectedCoverage projectedCoverage) {

        List<String> reasons =
                new ArrayList<>();

        reasons.add(
                "Requested coverage target of "
                        + input.requestedCoverageTarget()
                        + "% was not achieved. Projected coverage is "
                        + projectedCoverage.coveragePercentage()
                        + "%.");

        for (NonGeneratedTestCase nonGeneratedTestCase :
                nonGeneratedTestCases) {

            reasons.add(
                    nonGeneratedTestCase.plannedTestCase().id()
                            + ": "
                            + nonGeneratedTestCase.reason());
        }

        if (reasons.size() == 1
                && !projectedCoverage.uncoveredRequirementIds().isEmpty()) {

            reasons.add(
                    "Remaining uncovered Business Requirements: "
                            + String.join(
                            ", ",
                            projectedCoverage
                                    .uncoveredRequirementIds()));
        }

        return reasons;
    }
}