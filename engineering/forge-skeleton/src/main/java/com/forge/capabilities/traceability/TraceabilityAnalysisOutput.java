package com.forge.capabilities.traceability;

import com.forge.domain.traceability.RequirementTestCaseRelation;

import java.util.List;
import java.util.Objects;

public record TraceabilityAnalysisOutput(
        List<RequirementTestCaseRelation> relations) {

    public TraceabilityAnalysisOutput {
        Objects.requireNonNull(
                relations,
                "relations must not be null");

        relations = List.copyOf(relations);
    }
}