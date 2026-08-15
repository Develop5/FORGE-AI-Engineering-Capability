package com.forge.capabilities.coverage;

import com.forge.domain.coverage.CoverageResult;

import java.util.Objects;

public record CoverageAnalysisOutput(
        CoverageResult coverageResult) {

    public CoverageAnalysisOutput {
        Objects.requireNonNull(
                coverageResult,
                "coverageResult must not be null");
    }
}