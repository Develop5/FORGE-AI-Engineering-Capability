package com.forge.capabilities.clarification;

import com.forge.domain.clarification.Question;
import com.forge.domain.finding.Finding;
import com.forge.domain.finding.Risk;
import com.forge.domain.requirement.BusinessRequirement;
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
                                List.of(),
                                List.of(finding),
                                null,
                                null));

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

        assertTrue(
                output.businessRequirements().isEmpty());

        assertTrue(
                output.risks().isEmpty());
    }

    @Test
    void shouldReturnNoQuestionsWhenThereAreNoFindings() {

        LocalClarification clarification =
                new LocalClarification();

        ClarificationOutput output =
                clarification.execute(
                        new ClarificationInput(
                                List.of(),
                                List.of(),
                                null,
                                null));

        assertTrue(
                output.questions().isEmpty());

        assertTrue(
                output.businessRequirements().isEmpty());

        assertTrue(
                output.findings().isEmpty());

        assertTrue(
                output.risks().isEmpty());
    }

    @Test
    void shouldResolveFindingAndUpdateRelatedRequirement() {

        BusinessRequirement requirement =
                new BusinessRequirement(
                        "requirement-1",
                        "Authentication",
                        "The authentication behavior is unclear.",
                        "HIGH",
                        List.of());

        Finding finding =
                new Finding(
                        "finding-1",
                        "AMBIGUITY",
                        "The authentication requirement is unclear.",
                        List.of("requirement-1"));

        Question question =
                new Question(
                        "question-1",
                        "Please clarify: The authentication requirement is unclear.",
                        "finding-1");

        LocalClarification clarification =
                new LocalClarification();

        ClarificationOutput output =
                clarification.execute(
                        new ClarificationInput(
                                List.of(requirement),
                                List.of(finding),
                                question,
                                "Users must authenticate with valid credentials."));

        assertTrue(
                output.questions().isEmpty());

        assertTrue(
                output.risks().isEmpty());

        assertTrue(
                output.findings().isEmpty());

        assertEquals(
                1,
                output.businessRequirements().size());

        assertEquals(
                List.of(
                        "Users must authenticate with valid credentials."),
                output.businessRequirements()
                        .get(0)
                        .acceptanceCriteria());
    }

    @Test
    void shouldCreateRiskWhenFindingCannotBeResolved() {

        BusinessRequirement requirement =
                new BusinessRequirement(
                        "requirement-1",
                        "Authentication",
                        "The authentication behavior is unclear.",
                        "HIGH",
                        List.of());

        Finding finding =
                new Finding(
                        "finding-1",
                        "AMBIGUITY",
                        "The authentication requirement is unclear.",
                        List.of("requirement-1"));

        Question question =
                new Question(
                        "question-1",
                        "Please clarify: The authentication requirement is unclear.",
                        "finding-1");

        LocalClarification clarification =
                new LocalClarification();

        ClarificationOutput output =
                clarification.execute(
                        new ClarificationInput(
                                List.of(requirement),
                                List.of(finding),
                                question,
                                null));

        assertTrue(
                output.questions().isEmpty());

        assertTrue(
                output.findings().isEmpty());

        assertEquals(
                1,
                output.risks().size());

        Risk risk =
                output.risks().get(0);

        assertEquals(
                "finding-1",
                risk.sourceFindingId().orElseThrow());

        assertEquals(
                List.of("requirement-1"),
                risk.relatedRequirementIds());

        assertEquals(
                "Clarification",
                risk.identifyingCapability());
    }
}