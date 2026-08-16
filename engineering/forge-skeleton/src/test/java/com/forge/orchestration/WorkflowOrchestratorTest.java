package com.forge.orchestration;

import com.forge.capabilities.clarification.LocalClarification;
import com.forge.capabilities.evidence.LocalEvidenceConsolidation;
import com.forge.capabilities.requirements.LocalRequirementsDiscovery;
import com.forge.capabilities.traceability.LocalTraceabilityAnalysis;
import com.forge.domain.evidence.Evidence;
import com.forge.domain.execution.Execution;
import com.forge.domain.execution.ExecutionContext;
import com.forge.domain.execution.ExecutionStage;
import com.forge.domain.testcase.TestCase;
import com.forge.domain.traceability.RelationType;
import com.forge.domain.traceability.RequirementTestCaseRelation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkflowOrchestratorTest {

    @Test
    void shouldProcessEvidenceRequirementsClarificationAndTraceability() {

        ExecutionContext context = new ExecutionContext();

        context.addEvidence(
                new Evidence(
                        "evidence-1",
                        "User must authenticate before accessing the system.",
                        "local-test"));

        context.addExistingTestCase(
                new TestCase(
                        "test-case-1",
                        "User authentication",
                        "Verify that a user must authenticate before accessing the system.",
                        List.of("User has access to the system"),
                        List.of("Valid user credentials"),
                        List.of("Open the system",
                                "Enter valid credentials",
                                "Submit credentials"),
                        "The user is authenticated and can access the system.",
                        "local-test"));

        Execution execution = new Execution(
                "execution-1",
                context);

        WorkflowOrchestrator orchestrator =
                new WorkflowOrchestrator(
                        new LocalEvidenceConsolidation(),
                        new LocalRequirementsDiscovery(),
                        new LocalClarification(),
                        new LocalTraceabilityAnalysis());

        Execution result = orchestrator.start(execution);

        assertEquals(
                ExecutionStage.TRACEABILITY,
                result.currentStage());

        assertFalse(
                result.context().evidenceTopics().isEmpty());

        assertFalse(
                result.context().businessRequirements().isEmpty());

        assertTrue(
                result.context().findings().isEmpty());

        assertTrue(
                result.context().pendingQuestion() == null);

        assertEquals(
                1,
                result.context().traceabilityRelations().size());

        RequirementTestCaseRelation relation =
                result.context().traceabilityRelations().get(0);

        assertEquals(
                result.context().businessRequirements().get(0).id(),
                relation.requirementId());

        assertEquals(
                "test-case-1",
                relation.testCaseId());

        assertEquals(
                RelationType.COVERS,
                relation.relationType());
    }
}