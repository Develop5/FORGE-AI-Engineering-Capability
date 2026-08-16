package com.forge.orchestration;

import com.forge.capabilities.evidence.EvidenceConsolidationCapability;
import com.forge.capabilities.evidence.EvidenceConsolidationInput;
import com.forge.capabilities.evidence.EvidenceConsolidationOutput;
import com.forge.capabilities.requirements.RequirementsDiscoveryCapability;
import com.forge.capabilities.requirements.RequirementsDiscoveryInput;
import com.forge.capabilities.requirements.RequirementsDiscoveryOutput;
import com.forge.domain.execution.Execution;

import java.util.Objects;

public final class WorkflowOrchestrator implements ForgeEngine {

    private final EvidenceConsolidationCapability
            evidenceConsolidationCapability;

    private final RequirementsDiscoveryCapability
            requirementsDiscoveryCapability;

    public WorkflowOrchestrator(
            EvidenceConsolidationCapability
                    evidenceConsolidationCapability,
            RequirementsDiscoveryCapability
                    requirementsDiscoveryCapability) {

        this.evidenceConsolidationCapability =
                Objects.requireNonNull(
                        evidenceConsolidationCapability,
                        "evidenceConsolidationCapability must not be null");

        this.requirementsDiscoveryCapability =
                Objects.requireNonNull(
                        requirementsDiscoveryCapability,
                        "requirementsDiscoveryCapability must not be null");
    }

    @Override
    public Execution start(Execution execution) {
        Objects.requireNonNull(
                execution,
                "execution must not be null");

        execution.start();

        EvidenceConsolidationInput evidenceInput =
                new EvidenceConsolidationInput(
                        execution.context().evidence());

        EvidenceConsolidationOutput evidenceOutput =
                evidenceConsolidationCapability.execute(evidenceInput);

        evidenceOutput.evidenceTopics()
                .forEach(execution.context()::addEvidenceTopic);

        RequirementsDiscoveryInput requirementsInput =
                new RequirementsDiscoveryInput(
                        execution.context().evidenceTopics());

        RequirementsDiscoveryOutput requirementsOutput =
                requirementsDiscoveryCapability.execute(requirementsInput);

        requirementsOutput.businessRequirements()
                .forEach(execution.context()::addBusinessRequirement);

        requirementsOutput.findings()
                .forEach(execution.context()::addFinding);

        execution.moveTo(
                com.forge.domain.execution.ExecutionStage.REQUIREMENTS);

        return execution;
    }
}