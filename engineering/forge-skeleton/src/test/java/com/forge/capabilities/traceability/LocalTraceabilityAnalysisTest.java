package com.forge.capabilities.traceability;

import com.forge.domain.requirement.BusinessRequirement;
import com.forge.domain.testcase.TestCase;
import com.forge.domain.traceability.RelationType;
import com.forge.domain.traceability.RequirementTestCaseRelation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalTraceabilityAnalysisTest {

    private final LocalTraceabilityAnalysis analysis =
            new LocalTraceabilityAnalysis();

    @Test
    void shouldCreateCoversRelationWhenTestCaseExplicitlyReferencesRequirement() {

        BusinessRequirement requirement =
                new BusinessRequirement(
                        "BR-001",
                        "User authentication",
                        "The user must authenticate before accessing the system.",
                        "HIGH",
                        List.of(
                                "The user must provide valid credentials."));

        TestCase testCase =
                new TestCase(
                        "TC-001",
                        "Verify user authentication",
                        "Verify that a user must authenticate before accessing the system.",
                        List.of(
                                "User has access to the system."),
                        List.of(
                                "Valid user credentials."),
                        List.of(
                                "Open the system.",
                                "Enter valid credentials.",
                                "Submit credentials."),
                        "The user is authenticated and can access the system.",
                        "BR-001");

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
                "BR-001",
                relation.requirementId());

        assertEquals(
                "TC-001",
                relation.testCaseId());

        assertEquals(
                RelationType.COVERS,
                relation.relationType());
    }

    @Test
    void shouldCreateCoversRelationWhenTestCaseContentSupportsRequirement() {

        BusinessRequirement requirement =
                new BusinessRequirement(
                        "BR-001",
                        "User authentication",
                        "The user must authenticate before accessing the system.",
                        "HIGH",
                        List.of(
                                "The user must provide valid credentials."));

        TestCase testCase =
                new TestCase(
                        "TC-001",
                        "Verify authentication",
                        "Verify authentication before accessing the system.",
                        List.of(
                                "User has valid credentials."),
                        List.of(
                                "Valid user credentials."),
                        List.of(
                                "Open the system.",
                                "Authenticate the user.",
                                "Enter valid credentials.",
                                "Submit credentials."),
                        "The user is authenticated and can access the system.",
                        "OTHER-REQUIREMENT");

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
                "BR-001",
                relation.requirementId());

        assertEquals(
                "TC-001",
                relation.testCaseId());

        assertEquals(
                RelationType.COVERS,
                relation.relationType());
    }

    @Test
    void shouldNotCreateRelationWhenTestCaseDoesNotReferenceOrSupportRequirement() {

        BusinessRequirement requirement =
                new BusinessRequirement(
                        "BR-001",
                        "User authentication",
                        "The user must authenticate before accessing the system.",
                        "HIGH",
                        List.of(
                                "The user must provide valid credentials."));

        TestCase testCase =
                new TestCase(
                        "TC-001",
                        "Verify user logout",
                        "Verify that a user can leave the system.",
                        List.of(
                                "User is authenticated."),
                        List.of(
                                "Logout request."),
                        List.of(
                                "Open the system.",
                                "Select logout."),
                        "The user is logged out.",
                        "OTHER-REQUIREMENT");

        TraceabilityAnalysisOutput output =
                analysis.execute(
                        new TraceabilityAnalysisInput(
                                List.of(requirement),
                                List.of(testCase)));

        assertEquals(
                0,
                output.relations().size());
    }

    @Test
    void shouldNotCreateRelationsWhenThereAreNoTestCases() {

        BusinessRequirement requirement =
                new BusinessRequirement(
                        "BR-001",
                        "User authentication",
                        "The user must authenticate before accessing the system.",
                        "HIGH",
                        List.of(
                                "The user must provide valid credentials."));

        TraceabilityAnalysisOutput output =
                analysis.execute(
                        new TraceabilityAnalysisInput(
                                List.of(requirement),
                                List.of()));

        assertEquals(
                0,
                output.relations().size());
    }
}