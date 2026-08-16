package com.forge.capabilities.improvement;

import com.forge.domain.coverage.CoverageResult;
import com.forge.domain.coverage.ProjectedCoverage;
import com.forge.domain.requirement.BusinessRequirement;
import com.forge.domain.testcase.GeneratedTestCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalProjectedCoverageTest {

    @Test
    void shouldIncludeGeneratedTestCasesInProjectedCoverage() {

        BusinessRequirement covered =
                requirement("BR-001");

        BusinessRequirement uncovered =
                requirement("BR-002");

        CoverageResult currentCoverage =
                new CoverageResult(
                        List.of(covered, uncovered),
                        List.of(),
                        50.0,
                        List.of("BR-002"),
                        List.of());

        GeneratedTestCase generatedTestCase =
                new GeneratedTestCase(
                        "GEN-BR-002",
                        "Generated test for BR-002",
                        "Generated test",
                        List.of(),
                        List.of(),
                        List.of("Execute scenario"),
                        "Requirement is satisfied",
                        List.of("BR-002"));

        ProjectedCoverage result =
                new LocalProjectedCoverage().calculate(
                        currentCoverage,
                        List.of(generatedTestCase));

        assertEquals(
                100.0,
                result.coveragePercentage());

        assertEquals(
                List.of("BR-001", "BR-002"),
                result.coveredRequirementIds());

        assertTrue(
                result.uncoveredRequirementIds()
                        .isEmpty());
    }

    @Test
    void shouldPreserveUncoveredRequirementsWithoutGeneratedTests() {

        BusinessRequirement requirement1 =
                requirement("BR-001");

        BusinessRequirement requirement2 =
                requirement("BR-002");

        CoverageResult currentCoverage =
                new CoverageResult(
                        List.of(requirement1, requirement2),
                        List.of(),
                        0.0,
                        List.of("BR-001", "BR-002"),
                        List.of());

        ProjectedCoverage result =
                new LocalProjectedCoverage().calculate(
                        currentCoverage,
                        List.of());

        assertEquals(
                0.0,
                result.coveragePercentage());

        assertTrue(
                result.coveredRequirementIds()
                        .isEmpty());

        assertEquals(
                List.of("BR-001", "BR-002"),
                result.uncoveredRequirementIds());
    }

    private static BusinessRequirement requirement(
            String id) {

        return new BusinessRequirement(
                id,
                "Requirement " + id,
                "Description " + id,
                "HIGH",
                List.of("Requirement is satisfied"));
    }
}