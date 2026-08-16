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
import com.forge.domain.finding.Finding;
import com.forge.domain.testcase.TestCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        assertTrue(
                result.context()
                        .evidenceTopics()
                        .size() > 0);

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
                        .pendingQuestions()
                        .isEmpty());

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

    @Test
    void shouldPauseForClarificationAndResumeWithUserResponse() {

        ExecutionContext context =
                new ExecutionContext();

        context.addEvidence(
                new Evidence(
                        "evidence-1",
                        "The system must allow users to authenticate.",
                        "local-test"));

        context.addFinding(
                new Finding(
                        "finding-1",
                        "AMBIGUITY",
                        "The authentication requirement is unclear.",
                        List.of("BR-evidence-1")));

        Execution execution =
                new Execution(
                        "execution-clarification",
                        context);

        WorkflowOrchestrator orchestrator =
                new WorkflowOrchestrator(
                        new LocalEvidenceConsolidation(),
                        new LocalRequirementsDiscovery(),
                        new LocalClarification(),
                        new LocalTraceabilityAnalysis(),
                        new LocalCoverageAnalysis(),
                        new LocalImprovement());

        Execution waiting =
                orchestrator.start(execution);

        assertEquals(
                ExecutionStage.CLARIFICATION,
                waiting.currentStage());

        assertEquals(
                "WAITING_FOR_CLARIFICATION",
                waiting.status().name());

        assertEquals(
                1,
                waiting.context()
                        .pendingQuestions()
                        .size());

        assertEquals(
                "question-1",
                waiting.context()
                        .pendingQuestion()
                        .id());

        Execution resumed =
                orchestrator.resume(
                        waiting,
                        "Users must authenticate with valid credentials.");

        assertEquals(
                "COMPLETED",
                resumed.status().name());

        assertTrue(
                resumed.context()
                        .pendingQuestions()
                        .isEmpty());

        assertTrue(
                resumed.context()
                        .pendingResponse() == null);

        assertTrue(
                resumed.context()
                        .findings()
                        .stream()
                        .noneMatch(
                                finding ->
                                        finding.id()
                                                .equals("finding-1")));

        assertTrue(
                resumed.context()
                        .businessRequirements()
                        .stream()
                        .anyMatch(
                                requirement ->
                                        requirement.id()
                                                .equals("BR-evidence-1")
                                                && requirement
                                                .acceptanceCriteria()
                                                .contains(
                                                        "Users must authenticate with valid credentials.")));
    }

    @Test
    void shouldPreserveRemainingQuestionsWhileClarificationIsWaiting() {

        ExecutionContext context =
                new ExecutionContext();

        context.addEvidence(
                new Evidence(
                        "evidence-1",
                        "The system must allow users to authenticate.",
                        "local-test"));

        context.addFinding(
                new Finding(
                        "finding-1",
                        "AMBIGUITY",
                        "The authentication requirement is unclear.",
                        List.of("BR-evidence-1")));

        context.addFinding(
                new Finding(
                        "finding-2",
                        "AMBIGUITY",
                        "The authentication credential rules are unclear.",
                        List.of("BR-evidence-1")));

        Execution execution =
                new Execution(
                        "execution-multiple-questions",
                        context);

        WorkflowOrchestrator orchestrator =
                new WorkflowOrchestrator(
                        new LocalEvidenceConsolidation(),
                        new LocalRequirementsDiscovery(),
                        new LocalClarification(),
                        new LocalTraceabilityAnalysis(),
                        new LocalCoverageAnalysis(),
                        new LocalImprovement());

        Execution waiting =
                orchestrator.start(execution);

        assertEquals(
                ExecutionStage.CLARIFICATION,
                waiting.currentStage());

        assertEquals(
                "WAITING_FOR_CLARIFICATION",
                waiting.status().name());

        assertEquals(
                2,
                waiting.context()
                        .pendingQuestions()
                        .size());

        assertEquals(
                "finding-1",
                waiting.context()
                        .pendingQuestion()
                        .findingId());

        Execution stillWaiting =
                orchestrator.resume(
                        waiting,
                        "Users must authenticate with valid credentials.");

        assertEquals(
                ExecutionStage.CLARIFICATION,
                stillWaiting.currentStage());

        assertEquals(
                "WAITING_FOR_CLARIFICATION",
                stillWaiting.status().name());

        assertEquals(
                1,
                stillWaiting.context()
                        .pendingQuestions()
                        .size());

        assertEquals(
                "finding-2",
                stillWaiting.context()
                        .pendingQuestion()
                        .findingId());

        Execution completed =
                orchestrator.resume(
                        stillWaiting,
                        "Authentication requires valid credential rules.");

        assertEquals(
                "COMPLETED",
                completed.status().name());

        assertTrue(
                completed.context()
                        .pendingQuestions()
                        .isEmpty());

        assertTrue(
                completed.context()
                        .findings()
                        .isEmpty());
    }
}