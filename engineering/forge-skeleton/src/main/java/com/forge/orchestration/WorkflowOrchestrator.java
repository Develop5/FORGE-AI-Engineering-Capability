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
import com.forge.capabilities.improvement.ImprovementCapability;
import com.forge.capabilities.improvement.ImprovementInput;
import com.forge.capabilities.improvement.ImprovementOutput;
import com.forge.capabilities.requirements.RequirementsDiscoveryCapability;
import com.forge.capabilities.requirements.RequirementsDiscoveryInput;
import com.forge.capabilities.requirements.RequirementsDiscoveryOutput;
import com.forge.capabilities.traceability.TraceabilityAnalysisCapability;
import com.forge.capabilities.traceability.TraceabilityAnalysisInput;
import com.forge.capabilities.traceability.TraceabilityAnalysisOutput;
import com.forge.domain.clarification.Question;
import com.forge.domain.execution.Execution;
import com.forge.domain.execution.ExecutionStage;

import java.util.List;
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

    private final ImprovementCapability
            improvementCapability;

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
                    coverageAnalysisCapability,
            ImprovementCapability
                    improvementCapability) {

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

        this.improvementCapability =
                Objects.requireNonNull(
                        improvementCapability,
                        "improvementCapability must not be null");
    }

    @Override
    public Execution start(Execution execution) {

        Objects.requireNonNull(
                execution,
                "execution must not be null");

        execution.start();

        EvidenceConsolidationOutput evidenceOutput =
                evidenceConsolidationCapability.execute(
                        new EvidenceConsolidationInput(
                                execution.context().evidence()));

        evidenceOutput.evidenceTopics()
                .forEach(
                        execution.context()::addEvidenceTopic);

        RequirementsDiscoveryOutput requirementsOutput =
                requirementsDiscoveryCapability.execute(
                        new RequirementsDiscoveryInput(
                                execution.context().evidenceTopics()));

        requirementsOutput.businessRequirements()
                .forEach(
                        execution.context()::addBusinessRequirement);

        requirementsOutput.findings()
                .forEach(
                        execution.context()::addFinding);

        execution.moveTo(
                ExecutionStage.REQUIREMENTS);

        ClarificationOutput clarificationOutput =
                clarificationCapability.execute(
                        new ClarificationInput(
                                execution.context()
                                        .businessRequirements(),
                                execution.context()
                                        .findings(),
                                null,
                                null));

        applyInitialClarificationResult(
                execution,
                clarificationOutput);

        if (!execution.context()
                .pendingQuestions()
                .isEmpty()) {

            execution.moveTo(
                    ExecutionStage.CLARIFICATION);

            execution.waitForClarification();

            return execution;
        }

        return continueAfterClarification(execution);
    }

    @Override
    public Execution resume(
            Execution execution,
            String response) {

        Objects.requireNonNull(
                execution,
                "execution must not be null");

        Question question =
                execution.context().pendingQuestion();

        if (question == null) {
            throw new IllegalStateException(
                    "No clarification question is pending");
        }

        execution.context().setPendingResponse(response);

        ClarificationOutput clarificationOutput =
                clarificationCapability.execute(
                        new ClarificationInput(
                                execution.context()
                                        .businessRequirements(),
                                execution.context()
                                        .findings(),
                                question,
                                execution.context()
                                        .pendingResponse()));

        execution.context().clearPendingResponse();
        execution.context().removePendingQuestion();

        applyClarificationResult(
                execution,
                clarificationOutput);

        if (!execution.context()
                .pendingQuestions()
                .isEmpty()) {

            execution.moveTo(
                    ExecutionStage.CLARIFICATION);

            execution.waitForClarification();

            return execution;
        }

        return continueAfterClarification(execution);
    }

    private void applyInitialClarificationResult(
            Execution execution,
            ClarificationOutput output) {

        execution.context().replaceBusinessRequirements(
                output.businessRequirements());

        execution.context().replaceFindings(
                output.findings());

        output.risks()
                .forEach(
                        execution.context()::addRisk);

        execution.context().setPendingQuestions(
                output.questions());
    }

    private void applyClarificationResult(
            Execution execution,
            ClarificationOutput output) {

        execution.context().replaceBusinessRequirements(
                output.businessRequirements());

        execution.context().replaceFindings(
                output.findings());

        output.risks()
                .forEach(
                        execution.context()::addRisk);

        List<Question> newQuestions =
                output.questions();

        newQuestions.forEach(
                execution.context()::addPendingQuestion);
    }

    private Execution continueAfterClarification(
            Execution execution) {

        TraceabilityAnalysisOutput traceabilityOutput =
                traceabilityAnalysisCapability.execute(
                        new TraceabilityAnalysisInput(
                                execution.context()
                                        .businessRequirements(),
                                execution.context()
                                        .existingTestCases()));

        traceabilityOutput.relations()
                .forEach(
                        execution.context()
                                ::addTraceabilityRelation);

        execution.moveTo(
                ExecutionStage.TRACEABILITY);

        CoverageAnalysisOutput coverageOutput =
                coverageAnalysisCapability.execute(
                        new CoverageAnalysisInput(
                                execution.context()
                                        .businessRequirements(),
                                execution.context()
                                        .existingTestCases(),
                                execution.context()
                                        .traceabilityRelations(),
                                execution.context()
                                        .risks()));

        execution.context().setCoverageResult(
                coverageOutput.coverageResult());

        execution.moveTo(
                ExecutionStage.COVERAGE);

        ImprovementOutput improvementOutput =
                improvementCapability.execute(
                        new ImprovementInput(
                                execution.context()
                                        .businessRequirements(),
                                execution.context()
                                        .existingTestCases(),
                                execution.context()
                                        .coverageResult(),
                                95.0));

        execution.moveTo(
                ExecutionStage.IMPROVEMENT);

        improvementOutput.generatedTestCases()
                .forEach(
                        execution.context()
                                ::addGeneratedTestCase);

        execution.context().setProjectedCoverage(
                improvementOutput.projectedCoverage());

        execution.moveTo(
                ExecutionStage.GENERATION);

        execution.moveTo(
                ExecutionStage.PROJECTED_COVERAGE);

        execution.complete();

        return execution;
    }
}