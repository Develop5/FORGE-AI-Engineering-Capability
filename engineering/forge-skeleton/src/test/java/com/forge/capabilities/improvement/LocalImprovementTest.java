package com.forge.capabilities.improvement;

import com.forge.domain.coverage.CoverageResult;
import com.forge.domain.finding.Risk;
import com.forge.domain.requirement.BusinessRequirement;
import com.forge.domain.testcase.GeneratedTestCase;
import com.forge.domain.testcase.TestCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalImprovementTest {

    @Test
    void generatesOneTestCaseForEachUncoveredRequirement() {
        BusinessRequirement requirement1 = requirement("BR-001");
        BusinessRequirement requirement2 = requirement("BR-002");

        CoverageResult coverageResult = new CoverageResult(
                List.of(requirement1, requirement2),
                List.of(),
                0.0,
                List.of("BR-001", "BR-002"),
                List.of());

        ImprovementInput input = new ImprovementInput(
                List.of(requirement1, requirement2),
                List.<TestCase>of(),
                coverageResult,
                100.0);

        ImprovementOutput output = new LocalImprovement().execute(input);

        assertEquals(2, output.generatedTestCases().size());

        assertEquals(
                List.of("BR-001"),
                output.generatedTestCases()
                        .get(0)
                        .targetRequirementIds());

        assertEquals(
                List.of("BR-002"),
                output.generatedTestCases()
                        .get(1)
                        .targetRequirementIds());
    }

    @Test
    void returnsEmptyOutputWhenThereAreNoUncoveredRequirements() {
        BusinessRequirement requirement = requirement("BR-001");

        CoverageResult coverageResult = new CoverageResult(
                List.of(requirement),
                List.of(),
                100.0,
                List.of(),
                List.of());

        ImprovementInput input = new ImprovementInput(
                List.of(requirement),
                List.<TestCase>of(),
                coverageResult,
                100.0);

        ImprovementOutput output = new LocalImprovement().execute(input);

        assertTrue(output.generatedTestCases().isEmpty());
    }

    @Test
    void generationIsDeterministic() {
        BusinessRequirement requirement = requirement("BR-001");

        CoverageResult coverageResult = new CoverageResult(
                List.of(requirement),
                List.of(),
                0.0,
                List.of("BR-001"),
                List.of());

        ImprovementInput input = new ImprovementInput(
                List.of(requirement),
                List.<TestCase>of(),
                coverageResult,
                100.0);

        LocalImprovement improvement = new LocalImprovement();

        GeneratedTestCase first =
                improvement.execute(input).generatedTestCases().get(0);

        GeneratedTestCase second =
                improvement.execute(input).generatedTestCases().get(0);

        assertEquals(first.id(), second.id());
        assertEquals(first.title(), second.title());
        assertEquals(first.description(), second.description());
        assertEquals(first.steps(), second.steps());
        assertEquals(first.expectedResult(), second.expectedResult());
        assertEquals(
                first.targetRequirementIds(),
                second.targetRequirementIds());
    }

    private static BusinessRequirement requirement(String id) {
        return new BusinessRequirement(
                id,
                "Requirement " + id,
                "Description for " + id,
                "HIGH",
                List.of("The requirement is satisfied"));
    }
}