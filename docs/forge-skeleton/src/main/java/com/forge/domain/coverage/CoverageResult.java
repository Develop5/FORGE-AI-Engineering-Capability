package com.forge.domain.coverage;

import java.util.List;
import java.util.Objects;

public final class CoverageResult {

    private final List<String> coveredRequirementIds;
    private final List<String> uncoveredRequirementIds;
    private final double coveragePercentage;

    public CoverageResult(
            List<String> coveredRequirementIds,
            List<String> uncoveredRequirementIds,
            double coveragePercentage) {

        this.coveredRequirementIds = copyList(
                coveredRequirementIds,
                "coveredRequirementIds");

        this.uncoveredRequirementIds = copyList(
                uncoveredRequirementIds,
                "uncoveredRequirementIds");

        if (coveragePercentage < 0.0 || coveragePercentage > 100.0) {
            throw new IllegalArgumentException(
                    "coveragePercentage must be between 0 and 100");
        }

        this.coveragePercentage = coveragePercentage;
    }

    public List<String> coveredRequirementIds() {
        return coveredRequirementIds;
    }

    public List<String> uncoveredRequirementIds() {
        return uncoveredRequirementIds;
    }

    public double coveragePercentage() {
        return coveragePercentage;
    }

    private static List<String> copyList(
            List<String> value,
            String fieldName) {

        Objects.requireNonNull(
                value,
                fieldName + " must not be null");

        return List.copyOf(value);
    }
}