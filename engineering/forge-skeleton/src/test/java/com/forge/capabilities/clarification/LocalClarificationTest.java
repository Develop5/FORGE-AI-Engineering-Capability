package com.forge.capabilities.clarification;

import com.forge.domain.clarification.Question;
import com.forge.domain.finding.Finding;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalClarificationTest {

    @Test
    void shouldGenerateQuestionForEachFinding() {

        Finding finding =
                new Finding(
                        "finding-1",
                        "AMBIGUITY",
                        "The authentication requirement is unclear.",
                        List.of("requirement-1"));

        LocalClarification clarification =
                new LocalClarification();

        ClarificationOutput output =
                clarification.execute(
                        new ClarificationInput(
                                List.of(finding)));

        assertEquals(
                1,
                output.questions().size());

        Question question =
                output.questions().get(0);

        assertEquals(
                "question-1",
                question.id());

        assertTrue(
                question.question()
                        .contains(
                                "The authentication requirement is unclear."));

        assertEquals(
                "finding-1",
                question.findingId());
    }

    @Test
    void shouldReturnNoQuestionsWhenThereAreNoFindings() {

        LocalClarification clarification =
                new LocalClarification();

        ClarificationOutput output =
                clarification.execute(
                        new ClarificationInput(
                                List.of()));

        assertTrue(
                output.questions().isEmpty());
    }
}