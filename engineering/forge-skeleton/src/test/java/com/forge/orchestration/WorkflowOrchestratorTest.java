package com.forge.orchestration;

import com.forge.capabilities.evidence.LocalEvidenceConsolidation;
import com.forge.domain.evidence.Evidence;
import com.forge.domain.execution.Execution;
import com.forge.domain.execution.ExecutionContext;
import com.forge.domain.execution.ExecutionStage;
import org.junit.jupiter.api.Test;
import com.forge.capabilities.requirements.LocalRequirementsDiscovery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class WorkflowOrchestratorTest {

    @Test
    void shouldProcessEvidenceAndAdvanceToRequirements() {

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
                        new LocalRequirementsDiscovery());

        Execution result = orchestrator.start(execution);

        assertEquals(
                ExecutionStage.REQUIREMENTS,
                result.currentStage());

        assertFalse(
                result.context().evidenceTopics().isEmpty());
    }
}