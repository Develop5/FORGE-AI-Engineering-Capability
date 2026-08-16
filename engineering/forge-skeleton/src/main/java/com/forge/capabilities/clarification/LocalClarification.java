package com.forge.capabilities.clarification;

import com.forge.domain.clarification.Question;
import com.forge.domain.finding.Finding;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public final class LocalClarification
        implements ClarificationCapability {

    @Override
    public ClarificationOutput execute(
            ClarificationInput input) {

        Objects.requireNonNull(
                input,
                "input must not be null");

        List<Question> questions =
                IntStream.range(0, input.findings().size())
                        .mapToObj(index -> {
                            Finding finding =
                                    input.findings().get(index);

                            return new Question(
                                    "question-" + (index + 1),
                                    "Please clarify: "
                                            + finding.description(),
                                    finding.id());
                        })
                        .toList();

        return new ClarificationOutput(questions);
    }
}