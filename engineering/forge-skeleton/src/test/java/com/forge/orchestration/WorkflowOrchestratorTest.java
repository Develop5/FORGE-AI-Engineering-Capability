package com.forge.orchestration;

import com.forge.capabilities.clarification.LocalClarification;
import com.forge.capabilities.coverage.LocalCoverageAnalysis;
import com.forge.capabilities.evidence.LocalEvidenceConsolidation;
import com.forge.capabilities.improvement.LocalImprovement;
import com.forge.capabilities.requirements.LocalRequirementsDiscovery;
import com.forge.capabilities.traceability.LocalTraceabilityAnalysis;
import com.forge.domain.evidence.Evidence;
import com.forge.domain.execution.Execution;
import com.forge.domain.execution.ExecutionContext;
import com.forge.domain.execution.ExecutionStage;
import com.forge.domain.testcase.TestCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkflowOrchestratorTest {

    @Test
    void shouldProcessCompleteWorkflowAndImproveCoverage() {

        ExecutionContext context =
                new ExecutionContext();

        context.addEvidence(
                new Evidence(
                        "evidence-1",
                        "The system must allow users to authenticate.",
                        "local-test"));

        context.addEvidence(
                new Evidence(
                        "evidence-2",
                        "The system must allow users to reset their password.",
                        "local-test"));

        context.addExistingTestCase(
                new TestCase(
                        "test-case-1",
                        "User authentication",
                        "Verify that a user can authenticate successfully.",
                        List.of(
                                "User has access to the system"),
                        List.of(
                                "Valid user credentials"),
                        List.of(
                                "Open the system",
                                "Enter valid credentials",
                                "Submit credentials"),
                        "The user is authenticated and can access the system.",
                        "BR-evidence-1"));

        Execution execution =
                new Execution(
                        "execution-1",
                        context);

        WorkflowOrchestrator orchestrator =
                new WorkflowOrchestrator(
                        new LocalEvidenceConsolidation(),
                        new LocalRequirementsDiscovery(),
                        new LocalClarification(),
                        new LocalTraceabilityAnalysis(),
                        new LocalCoverageAnalysis(),
                        new LocalImprovement());

        Execution result =
                orchestrator.start(execution);

        assertEquals(
                ExecutionStage.PROJECTED_COVERAGE,
                result.currentStage());

        assertEquals(
                "COMPLETED",
                result.status().name());

        assertFalse(
                result.context()
                        .evidenceTopics()
                        .isEmpty());

        assertEquals(
                2,
                result.context()
                        .businessRequirements()
                        .size());

        assertEquals(
                1,
                result.context()
                        .existingTestCases()
                        .size());

        assertTrue(
                result.context()
                        .findings()
                        .isEmpty());

        assertTrue(
                result.context()
                        .pendingQuestion() == null);

        assertEquals(
                50.0,
                result.context()
                        .coverageResult()
                        .currentCoverage());

        assertEquals(
                1,
                result.context()
                        .coverageResult()
                        .uncoveredRequirementIds()
                        .size());

        assertEquals(
                1,
                result.context()
                        .generatedTestCases()
                        .size());

        assertEquals(
                100.0,
                result.context()
                        .projectedCoverage()
                        .coveragePercentage());

        assertTrue(
                result.context()
                        .projectedCoverage()
                        .uncoveredRequirementIds()
                        .isEmpty());
    }
}