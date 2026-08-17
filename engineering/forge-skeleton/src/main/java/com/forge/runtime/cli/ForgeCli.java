package com.forge.runtime.cli;

import com.forge.capabilities.clarification.LocalClarification;
import com.forge.capabilities.coverage.LocalCoverageAnalysis;
import com.forge.capabilities.evidence.LocalEvidenceConsolidation;
import com.forge.capabilities.improvement.LocalImprovement;
import com.forge.capabilities.requirements.LocalRequirementsDiscovery;
import com.forge.capabilities.traceability.LocalTraceabilityAnalysis;
import com.forge.domain.clarification.Question;
import com.forge.domain.evidence.Evidence;
import com.forge.domain.execution.Execution;
import com.forge.domain.execution.ExecutionContext;
import com.forge.domain.execution.ExecutionStatus;
import com.forge.orchestration.ForgeEngine;
import com.forge.orchestration.WorkflowOrchestrator;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public final class ForgeCli {

    private final ForgeEngine engine;

    public ForgeCli(ForgeEngine engine) {
        this.engine = Objects.requireNonNull(
                engine,
                "engine must not be null");
    }

    public static void main(String[] args) {

        ForgeCli cli = new ForgeCli(createEngine());

        cli.run(
                System.in,
                System.out);
    }

    public void run(
            InputStream input,
            PrintStream output) {

        Objects.requireNonNull(
                input,
                "input must not be null");

        Objects.requireNonNull(
                output,
                "output must not be null");

        Scanner scanner = new Scanner(input);

        Execution execution =
                createExecution(scanner, output);

        executeWorkflow(
                execution,
                scanner,
                output);
    }

    void executeWorkflow(
            Execution execution,
            Scanner scanner,
            PrintStream output) {

        Execution current =
                engine.start(execution);

        while (current.status()
                == ExecutionStatus.WAITING_FOR_CLARIFICATION) {

            Question question =
                    current.context().pendingQuestion();

            if (question == null) {
                throw new IllegalStateException(
                        "Execution is waiting for clarification "
                                + "but no question is pending");
            }

            output.println();
            output.println("Question:");
            output.println(question.question());
            output.print("> ");

            if (!scanner.hasNextLine()) {
                throw new IllegalStateException(
                        "Input ended while waiting for clarification");
            }

            String response =
                    scanner.nextLine();

            current =
                    engine.resume(
                            current,
                            response);
        }

        renderResult(
                current,
                output);
    }

    private Execution createExecution(
            Scanner scanner,
            PrintStream output) {

        ExecutionContext context =
                new ExecutionContext();

        output.println("FORGE");
        output.println("Enter evidence, one item per line.");
        output.println("Submit an empty line to start the workflow.");
        output.println();

        int evidenceNumber = 1;

        while (true) {

            output.print("Evidence " + evidenceNumber + ": ");

            if (!scanner.hasNextLine()) {
                throw new IllegalStateException(
                        "Input ended before the workflow was started");
            }

            String content =
                    scanner.nextLine();

            if (content.isBlank()) {
                break;
            }

            context.addEvidence(
                    new Evidence(
                            "cli-evidence-" + evidenceNumber,
                            content,
                            "cli"));

            evidenceNumber++;
        }

        if (context.evidence().isEmpty()) {
            throw new IllegalArgumentException(
                    "At least one evidence item is required");
        }

        return new Execution(
                UUID.randomUUID().toString(),
                context);
    }

    private void renderResult(
            Execution execution,
            PrintStream output) {

        output.println();
        output.println("FORGE execution finished.");
        output.println("Status: " + execution.status());
        output.println("Stage: " + execution.currentStage());

        if (execution.context().coverageResult() != null) {
            output.println(
                    "Current Coverage: "
                            + execution.context()
                            .coverageResult()
                            .currentCoverage()
                            + "%");
        }

        if (execution.context().projectedCoverage() != null) {
            output.println(
                    "Projected Coverage: "
                            + execution.context()
                            .projectedCoverage()
                            .coveragePercentage()
                            + "%");
        }
    }

    private static ForgeEngine createEngine() {

        return new WorkflowOrchestrator(
                new LocalEvidenceConsolidation(),
                new LocalRequirementsDiscovery(),
                new LocalClarification(),
                new LocalTraceabilityAnalysis(),
                new LocalCoverageAnalysis(),
                new LocalImprovement());
    }
}