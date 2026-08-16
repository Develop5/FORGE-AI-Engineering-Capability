package com.forge.orchestration;

import com.forge.capabilities.clarification.LocalClarification;
import com.forge.capabilities.evidence.LocalEvidenceConsolidation;
import com.forge.capabilities.requirements.LocalRequirementsDiscovery;
import com.forge.domain.evidence.Evidence;
import com.forge.domain.execution.Execution;
import com.forge.domain.execution.ExecutionContext;
import com.forge.domain.execution.ExecutionStage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkflowOrchestratorTest {

    @Test
    void shouldProcessEvidenceRequirementsAndClarification() {

        ExecutionContext context = new ExecutionContext();

        context.addEvidence(
                new Evidence(
                        "evidence-1",
                        "User must authenticate before accessing the system.",
                        "local-test"));

        Execution execution = new Execution(
                "execution-1",
                context);

        WorkflowOrchestrator orchestrator =
                new WorkflowOrchestrator(
                        new LocalEvidenceConsolidation(),
                        new LocalRequirementsDiscovery(),
                        new LocalClarification());

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
    }
}