package com.forge.capabilities.improvement;

import com.forge.domain.coverage.CoverageResult;
import com.forge.domain.requirement.BusinessRequirement;
import com.forge.domain.testcase.GeneratedTestCase;
import com.forge.domain.testcase.TestCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalImprovementTest {

    @Test
    void shouldPlanAndGenerateTestCasesForUncoveredRequirements() {

        BusinessRequirement requirement =
                requirement("BR-001");

        ImprovementInput input =
                input(
                        List.of(requirement),
                        List.of("BR-001"),
                        95.0);

        ImprovementOutput output =
                new LocalImprovement().execute(input);

        assertEquals(
                1,
                output.plannedTestCases().size());

        assertEquals(
                "PLAN-BR-001",
                output.plannedTestCases()
                        .get(0)
                        .id());

        assertEquals(
                1,
                output.generatedTestCases().size());

        assertEquals(
                "GEN-BR-001",
                output.generatedTestCases()
                        .get(0)
                        .id());

        assertTrue(
                output.nonGeneratedTestCases()
                        .isEmpty());

        assertEquals(
                100.0,
                output.projectedCoverage()
                        .coveragePercentage());

        assertTrue(
                output.targetAchieved());

        assertTrue(
                output.targetFailureReasons()
                        .isEmpty());
    }

    @Test
    void shouldNotGenerateTestsWhenTargetAlreadyAchieved() {

        BusinessRequirement requirement =
                requirement("BR-001");

        CoverageResult coverageResult =
                new CoverageResult(
                        List.of(requirement),
                        List.of(),
                        100.0,
                        List.of(),
                        List.of());

        ImprovementInput targetAlreadyAchieved =
                new ImprovementInput(
                        List.of(requirement),
                        List.of(),
                        coverageResult,
                        95.0);

        ImprovementOutput output =
                new LocalImprovement()
                        .execute(targetAlreadyAchieved);

        assertTrue(
                output.plannedTestCases()
                        .isEmpty());

        assertTrue(
                output.generatedTestCases()
                        .isEmpty());

        assertTrue(
                output.nonGeneratedTestCases()
                        .isEmpty());

        assertEquals(
                100.0,
                output.projectedCoverage()
                        .coveragePercentage());

        assertTrue(
                output.targetAchieved());

        assertTrue(
                output.targetFailureReasons()
                        .isEmpty());
    }


    @Test
    void shouldReportFailureWhenTargetCannotBeReached() {

        BusinessRequirement requirement =
                requirement("BR-001");

        CoverageResult coverageResult =
                new CoverageResult(
                        List.of(requirement),
                        List.of(),
                        0.0,
                        List.of("BR-001"),
                        List.of());

        ImprovementInput input =
                new ImprovementInput(
                        List.of(),
                        List.of(),
                        coverageResult,
                        95.0);

        ImprovementOutput output =
                new LocalImprovement().execute(input);

        assertFalse(
                output.targetAchieved());

        assertFalse(
                output.targetFailureReasons()
                        .isEmpty());

        assertEquals(
                0.0,
                output.projectedCoverage()
                        .coveragePercentage());

        assertEquals(
                List.of("BR-001"),
                output.projectedCoverage()
                        .uncoveredRequirementIds());
    }

    private static ImprovementInput input(
            List<BusinessRequirement> requirements,
            List<String> uncoveredRequirementIds,
            double target) {

        CoverageResult coverageResult =
                new CoverageResult(
                        requirements,
                        List.of(),
                        uncoveredRequirementIds.isEmpty()
                                ? 100.0
                                : 0.0,
                        uncoveredRequirementIds,
                        List.of());

        List<TestCase> existingTestCases =
                List.of();

        return new ImprovementInput(
                requirements,
                existingTestCases,
                coverageResult,
                target);
    }

    private static BusinessRequirement requirement(
            String id) {

        return new BusinessRequirement(
                id,
                "Requirement " + id,
                "Description " + id,
                "HIGH",
                List.of(
                        "Requirement is satisfied"));
    }
}