package com.forge.capabilities.clarification;

import com.forge.domain.clarification.Question;
import com.forge.domain.finding.Finding;
import com.forge.domain.finding.Risk;
import com.forge.domain.requirement.BusinessRequirement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class LocalClarification
        implements ClarificationCapability {

    @Override
    public ClarificationOutput execute(
            ClarificationInput input) {

        Objects.requireNonNull(
                input,
                "input must not be null");

        if (input.question() == null) {
            return createQuestions(input);
        }

        return processResponse(input);
    }

    private ClarificationOutput createQuestions(
            ClarificationInput input) {

        List<Question> questions = new ArrayList<>();

        for (int index = 0;
             index < input.findings().size();
             index++) {

            Finding finding = input.findings().get(index);

            questions.add(
                    new Question(
                            "question-" + (index + 1),
                            "Please clarify: "
                                    + finding.description(),
                            finding.id()));
        }

        return new ClarificationOutput(
                input.businessRequirements(),
                input.findings(),
                questions,
                List.of());
    }

    private ClarificationOutput processResponse(
            ClarificationInput input) {

        Finding finding = input.findings().stream()
                .filter(candidate ->
                        candidate.id().equals(
                                input.question().findingId()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Finding not found for question: "
                                        + input.question().id()));

        if (input.response() == null
                || input.response().isBlank()) {

            Risk risk = new Risk(
                    "risk-" + finding.id(),
                    finding.description(),
                    finding.relatedRequirementIds(),
                    finding.id(),
                    "Clarification");

            return new ClarificationOutput(
                    input.businessRequirements(),
                    input.findings(),
                    List.of(),
                    List.of(risk));
        }

        List<BusinessRequirement> updatedRequirements =
                new ArrayList<>();

        for (BusinessRequirement requirement :
                input.businessRequirements()) {

            if (!finding.relatedRequirementIds()
                    .contains(requirement.id())) {

                updatedRequirements.add(requirement);
                continue;
            }

            List<String> acceptanceCriteria =
                    new ArrayList<>(
                            requirement.acceptanceCriteria());

            acceptanceCriteria.add(input.response());

            updatedRequirements.add(
                    new BusinessRequirement(
                            requirement.id(),
                            requirement.title(),
                            requirement.description(),
                            requirement.priority(),
                            acceptanceCriteria));
        }

        List<Finding> remainingFindings =
                input.findings().stream()
                        .filter(candidate ->
                                !candidate.id().equals(finding.id()))
                        .toList();

        return new ClarificationOutput(
                updatedRequirements,
                remainingFindings,
                List.of(),
                List.of());
    }
}