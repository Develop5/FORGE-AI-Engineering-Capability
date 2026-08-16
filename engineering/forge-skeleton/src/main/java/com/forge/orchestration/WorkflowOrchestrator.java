package com.forge.orchestration;

import com.forge.capabilities.evidence.EvidenceConsolidationCapability;
import com.forge.capabilities.evidence.EvidenceConsolidationInput;
import com.forge.capabilities.evidence.EvidenceConsolidationOutput;
import com.forge.domain.execution.Execution;

import java.util.Objects;

public final class WorkflowOrchestrator implements ForgeEngine {

    private final EvidenceConsolidationCapability
            evidenceConsolidationCapability;

    public WorkflowOrchestrator(
            EvidenceConsolidationCapability
                    evidenceConsolidationCapability) {

        this.evidenceConsolidationCapability =
                Objects.requireNonNull(
                        evidenceConsolidationCapability,
                        "evidenceConsolidationCapability must not be null");
    }

    @Override
    public Execution start(Execution execution) {
        Objects.requireNonNull(
                execution,
                "execution must not be null");

        execution.start();

        EvidenceConsolidationInput input =
                new EvidenceConsolidationInput(
                        execution.context().evidence());

        EvidenceConsolidationOutput output =
                evidenceConsolidationCapability.execute(input);

        output.evidenceTopics()
                .forEach(execution.context()::addEvidenceTopic);

        execution.moveTo(
                com.forge.domain.execution.ExecutionStage.REQUIREMENTS);

        return execution;
    }
}