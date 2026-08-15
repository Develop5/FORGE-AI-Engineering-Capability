package com.forge.capabilities.clarification;

import com.forge.domain.clarification.Question;

import java.util.List;
import java.util.Objects;

public record ClarificationOutput(
        List<Question> questions) {

    public ClarificationOutput {
        Objects.requireNonNull(
                questions,
                "questions must not be null");

        questions = List.copyOf(questions);
    }
}