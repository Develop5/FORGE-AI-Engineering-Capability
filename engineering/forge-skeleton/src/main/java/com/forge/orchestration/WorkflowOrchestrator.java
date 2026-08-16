package com.forge.orchestration;

import com.forge.capabilities.clarification.ClarificationCapability;
import com.forge.capabilities.clarification.ClarificationInput;
import com.forge.capabilities.clarification.ClarificationOutput;
import com.forge.capabilities.coverage.CoverageAnalysisCapability;
import com.forge.capabilities.coverage.CoverageAnalysisInput;
import com.forge.capabilities.coverage.CoverageAnalysisOutput;
import com.forge.capabilities.evidence.EvidenceConsolidationCapability;
import com.forge.capabilities.evidence.EvidenceConsolidationInput;
import com.forge.capabilities.evidence.EvidenceConsolidationOutput;
import com.forge.capabilities.requirements.RequirementsDiscoveryCapability;
import com.forge.capabilities.requirements.RequirementsDiscoveryInput;
import com.forge.capabilities.requirements.RequirementsDiscoveryOutput;
import com.forge.capabilities.traceability.TraceabilityAnalysisCapability;
import com.forge.capabilities.traceability.TraceabilityAnalysisInput;
import com.forge.capabilities.traceability.TraceabilityAnalysisOutput;
import com.forge.domain.execution.Execution;
import com.forge.domain.execution.ExecutionStage;

import java.util.Objects;

public final class WorkflowOrchestrator implements ForgeEngine {

    private final EvidenceConsolidationCapability
            evidenceConsolidationCapability;

    private final RequirementsDiscoveryCapability
            requirementsDiscoveryCapability;

    private final ClarificationCapability
            clarificationCapability;

    private final TraceabilityAnalysisCapability
            traceabilityAnalysisCapability;

    private final CoverageAnalysisCapability
            coverageAnalysisCapability;

    public WorkflowOrchestrator(
            EvidenceConsolidationCapability
                    evidenceConsolidationCapability,
            RequirementsDiscoveryCapability
                    requirementsDiscoveryCapability,
            ClarificationCapability
                    clarificationCapability,
            TraceabilityAnalysisCapability
                    traceabilityAnalysisCapability,
            CoverageAnalysisCapability
                    coverageAnalysisCapability) {

        this.evidenceConsolidationCapability =
                Objects.requireNonNull(
                        evidenceConsolidationCapability,
                        "evidenceConsolidationCapability must not be null");

        this.requirementsDiscoveryCapability =
                Objects.requireNonNull(
                        requirementsDiscoveryCapability,
                        "requirementsDiscoveryCapability must not be null");

        this.clarificationCapability =
                Objects.requireNonNull(
                        clarificationCapability,
                        "clarificationCapability must not be null");

        this.traceabilityAnalysisCapability =
                Objects.requireNonNull(
                        traceabilityAnalysisCapability,
                        "traceabilityAnalysisCapability must not be null");

        this.coverageAnalysisCapability =
                Objects.requireNonNull(
                        coverageAnalysisCapability,
                        "coverageAnalysisCapability must not be null");
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
                evidenceConsolidationCapability.execute(
                        evidenceInput);

        evidenceOutput.evidenceTopics()
                .forEach(
                        execution.context()::addEvidenceTopic);

        RequirementsDiscoveryInput requirementsInput =
                new RequirementsDiscoveryInput(
                        execution.context().evidenceTopics());

        RequirementsDiscoveryOutput requirementsOutput =
                requirementsDiscoveryCapability.execute(
                        requirementsInput);

        requirementsOutput.businessRequirements()
                .forEach(
                        execution.context()::addBusinessRequirement);

        requirementsOutput.findings()
                .forEach(
                        execution.context()::addFinding);

        execution.moveTo(
                ExecutionStage.REQUIREMENTS);

        ClarificationInput clarificationInput =
                new ClarificationInput(
                        execution.context().findings());

        ClarificationOutput clarificationOutput =
                clarificationCapability.execute(
                        clarificationInput);

        if (!clarificationOutput.questions().isEmpty()) {
            execution.context().setPendingQuestion(
                    clarificationOutput.questions().get(0));

            execution.moveTo(
                    ExecutionStage.CLARIFICATION);

            execution.waitForClarification();

            return execution;
        }

        TraceabilityAnalysisInput traceabilityInput =
                new TraceabilityAnalysisInput(
                        execution.context().businessRequirements(),
                        execution.context().existingTestCases());

        TraceabilityAnalysisOutput traceabilityOutput =
                traceabilityAnalysisCapability.execute(
                        traceabilityInput);

        traceabilityOutput.relations()
                .forEach(
                        execution.context()::addTraceabilityRelation);

        execution.moveTo(
                ExecutionStage.TRACEABILITY);

        CoverageAnalysisInput coverageInput =
                new CoverageAnalysisInput(
                        execution.context().businessRequirements(),
                        execution.context().existingTestCases(),
                        execution.context().traceabilityRelations(),
                        execution.context().risks());

        CoverageAnalysisOutput coverageOutput =
                coverageAnalysisCapability.execute(
                        coverageInput);

        execution.context().setCoverageResult(
                coverageOutput.coverageResult());

        execution.moveTo(
                ExecutionStage.COVERAGE);

        return execution;
    }
}