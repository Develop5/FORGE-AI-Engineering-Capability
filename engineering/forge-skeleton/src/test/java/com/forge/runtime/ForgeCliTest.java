package com.forge.runtime.cli;

import com.forge.domain.clarification.Question;
import com.forge.domain.execution.Execution;
import com.forge.domain.execution.ExecutionContext;
import com.forge.domain.execution.ExecutionStage;
import com.forge.domain.execution.ExecutionStatus;
import com.forge.orchestration.ForgeEngine;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ForgeCliTest {

    @Test
    void shouldPresentQuestionAndResumeWorkflowWithUserResponse() {

        ExecutionContext context =
                new ExecutionContext();

        Execution execution =
                new Execution(
                        "cli-test",
                        context);

        TestForgeEngine engine =
                new TestForgeEngine();

        ForgeCli cli =
                new ForgeCli(engine);

        ByteArrayOutputStream output =
                new ByteArrayOutputStream();

        cli.executeWorkflow(
                execution,
                new java.util.Scanner(
                        new java.io.ByteArrayInputStream(
                                "The requirement is clear now.\n"
                                        .getBytes(StandardCharsets.UTF_8)),
                        StandardCharsets.UTF_8),
                new PrintStream(
                        output,
                        true,
                        StandardCharsets.UTF_8));

        String renderedOutput =
                output.toString(StandardCharsets.UTF_8);

        assertEquals(
                1,
                engine.resumeCalls);

        assertEquals(
                "The requirement is clear now.",
                engine.lastResponse);

        assertTrue(
                renderedOutput.contains(
                        "Please clarify the requirement."));

        assertTrue(
                renderedOutput.contains(
                        "Status: COMPLETED"));

        assertEquals(
                ExecutionStatus.COMPLETED,
                execution.status());
    }

    private static final class TestForgeEngine
            implements ForgeEngine {

        private int resumeCalls;
        private String lastResponse;

        @Override
        public Execution start(
                Execution execution) {

            execution.context().setPendingQuestions(
                    java.util.List.of(
                            new Question(
                                    "question-1",
                                    "Please clarify the requirement.",
                                    "finding-1")));

            execution.moveTo(
                    ExecutionStage.CLARIFICATION);

            execution.waitForClarification();

            return execution;
        }

        @Override
        public Execution resume(
                Execution execution,
                String response) {

            resumeCalls++;
            lastResponse = response;

            execution.context()
                    .clearPendingQuestions();

            execution.complete();

            return execution;
        }
    }
}