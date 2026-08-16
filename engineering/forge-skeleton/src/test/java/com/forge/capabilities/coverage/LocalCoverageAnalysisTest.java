package com.forge.capabilities.coverage;

import com.forge.domain.finding.Risk;
import com.forge.domain.requirement.BusinessRequirement;
import com.forge.domain.testcase.TestCase;
import com.forge.domain.traceability.RelationType;
import com.forge.domain.traceability.RequirementTestCaseRelation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalCoverageAnalysisTest {

    private final LocalCoverageAnalysis analysis =
            new LocalCoverageAnalysis();

    @Test
    void shouldReturn100PercentWhenAllRequirementsAreCovered() {
        BusinessRequirement firstRequirement =
                requirement("BR-001");

        BusinessRequirement secondRequirement =
                requirement("BR-002");

        TestCase firstTestCase =
                testCase("TC-001", "BR-001");

        TestCase secondTestCase =
                testCase("TC-002", "BR-002");

        CoverageAnalysisOutput output =
                analysis.execute(
                        new CoverageAnalysisInput(
                                List.of(
                                        firstRequirement,
                                        secondRequirement),
                                List.of(
                                        firstTestCase,
                                        secondTestCase),
                                List.of(
                                        covers("BR-001", "TC-001"),
                                        covers("BR-002", "TC-002")),
                                List.of()));

        assertEquals(
                100.0,
                output.coverageResult().currentCoverage());

        assertEquals(
                List.of(),
                output.coverageResult().uncoveredRequirementIds());

        assertEquals(
                2,
                output.coverageResult().relatedTestCases().size());
    }

    @Test
    void shouldCalculatePartialCoverageAndReturnUncoveredRequirements() {
        BusinessRequirement firstRequirement =
                requirement("BR-001");

        BusinessRequirement secondRequirement =
                requirement("BR-002");

        TestCase testCase =
                testCase("TC-001", "BR-001");

        CoverageAnalysisOutput output =
                analysis.execute(
                        new CoverageAnalysisInput(
                                List.of(
                                        firstRequirement,
                                        secondRequirement),
                                List.of(testCase),
                                List.of(
                                        covers("BR-001", "TC-001")),
                                List.of()));

        assertEquals(
                50.0,
                output.coverageResult().currentCoverage());

        assertEquals(
                List.of("BR-002"),
                output.coverageResult().uncoveredRequirementIds());

        assertEquals(
                List.of(testCase),
                output.coverageResult().relatedTestCases());
    }

    @Test
    void shouldReturnZeroCoverageWhenThereAreNoCoveredRequirements() {
        BusinessRequirement requirement =
                requirement("BR-001");

        TestCase testCase =
                testCase("TC-001", "OTHER-REQUIREMENT");

        CoverageAnalysisOutput output =
                analysis.execute(
                        new CoverageAnalysisInput(
                                List.of(requirement),
                                List.of(testCase),
                                List.of(),
                                List.of()));

        assertEquals(
                0.0,
                output.coverageResult().currentCoverage());

        assertEquals(
                List.of("BR-001"),
                output.coverageResult().uncoveredRequirementIds());

        assertEquals(
                List.of(),
                output.coverageResult().relatedTestCases());
    }

    @Test
    void shouldReturnZeroCoverageWhenThereAreNoTestCases() {
        BusinessRequirement requirement =
                requirement("BR-001");

        CoverageAnalysisOutput output =
                analysis.execute(
                        new CoverageAnalysisInput(
                                List.of(requirement),
                                List.of(),
                                List.of(),
                                List.of()));

        assertEquals(
                0.0,
                output.coverageResult().currentCoverage());

        assertEquals(
                List.of("BR-001"),
                output.coverageResult().uncoveredRequirementIds());
    }

    @Test
    void shouldPreserveRisksWithoutAffectingCoverage() {
        BusinessRequirement requirement =
                requirement("BR-001");

        TestCase testCase =
                testCase("TC-001", "BR-001");

        Risk risk =
                new Risk(
                        "RISK-001",
                        "Authentication risk",
                        List.of("BR-001"),
                        null,
                        "Requirements Discovery");

        CoverageAnalysisOutput output =
                analysis.execute(
                        new CoverageAnalysisInput(
                                List.of(requirement),
                                List.of(testCase),
                                List.of(
                                        covers("BR-001", "TC-001")),
                                List.of(risk)));

        assertEquals(
                100.0,
                output.coverageResult().currentCoverage());

        assertEquals(
                List.of(risk),
                output.coverageResult().risks());
    }

    private static BusinessRequirement requirement(String id) {
        return new BusinessRequirement(
                id,
                "Requirement " + id,
                "Description for " + id,
                "HIGH",
                List.of("Acceptance criterion"));
    }

    private static TestCase testCase(
            String id,
            String sourceReference) {

        return new TestCase(
                id,
                "Test " + id,
                "Description for " + id,
                List.of("Condition"),
                List.of("Input"),
                List.of("Step"),
                "Expected result",
                sourceReference);
    }

    private static RequirementTestCaseRelation covers(
            String requirementId,
            String testCaseId) {

        return new RequirementTestCaseRelation(
                requirementId,
                testCaseId,
                RelationType.COVERS);
    }
}