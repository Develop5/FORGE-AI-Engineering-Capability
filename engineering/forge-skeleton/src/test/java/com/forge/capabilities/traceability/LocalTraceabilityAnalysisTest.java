package com.forge.capabilities.traceability;

import com.forge.domain.requirement.BusinessRequirement;
import com.forge.domain.testcase.TestCase;
import com.forge.domain.traceability.RelationType;
import com.forge.domain.traceability.RequirementTestCaseRelation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalTraceabilityAnalysisTest {

    @Test
    void shouldCreateCoverageRelationBetweenRequirementAndTestCase() {

        BusinessRequirement requirement =
                new BusinessRequirement(
                        "requirement-1",
                        "User authentication",
                        "The user must authenticate.",
                        "HIGH",
                        List.of("Authentication is required."));

        TestCase testCase =
                new TestCase(
                        "testcase-1",
                        "Verify user authentication",
                        "Verify that the user can authenticate.",
                        List.of("User exists."),
                        List.of("Valid credentials."),
                        List.of("Enter credentials.", "Submit login."),
                        "User is authenticated.",
                        "local-test");

        LocalTraceabilityAnalysis analysis =
                new LocalTraceabilityAnalysis();

        TraceabilityAnalysisOutput output =
                analysis.execute(
                        new TraceabilityAnalysisInput(
                                List.of(requirement),
                                List.of(testCase)));

        assertEquals(
                1,
                output.relations().size());

        RequirementTestCaseRelation relation =
                output.relations().get(0);

        assertEquals(
                "requirement-1",
                relation.requirementId());

        assertEquals(
                "testcase-1",
                relation.testCaseId());

        assertEquals(
                RelationType.COVERS,
                relation.relationType());
    }

    @Test
    void shouldReturnNoRelationsWhenThereAreNoTestCases() {

        BusinessRequirement requirement =
                new BusinessRequirement(
                        "requirement-1",
                        "User authentication",
                        "The user must authenticate.",
                        "HIGH",
                        List.of("Authentication is required."));

        LocalTraceabilityAnalysis analysis =
                new LocalTraceabilityAnalysis();

        TraceabilityAnalysisOutput output =
                analysis.execute(
                        new TraceabilityAnalysisInput(
                                List.of(requirement),
                                List.of()));

        assertTrue(
                output.relations().isEmpty());
    }
}