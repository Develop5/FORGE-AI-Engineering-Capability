package com.forge.capabilities.improvement;

import com.forge.domain.testcase.GeneratedTestCase;

import java.util.List;
import java.util.Objects;

public record ImprovementOutput(
        List<GeneratedTestCase> generatedTestCases) {

    public ImprovementOutput {
        Objects.requireNonNull(
                generatedTestCases,
                "generatedTestCases must not be null");

        generatedTestCases = List.copyOf(generatedTestCases);
    }
}