package com.forge.capabilities.improvement;

import com.forge.domain.requirement.BusinessRequirement;
import com.forge.domain.testcase.GeneratedTestCase;

import java.util.ArrayList;
import java.util.List;

public final class LocalImprovement implements ImprovementCapability {

    @Override
    public ImprovementOutput execute(ImprovementInput input) {
        List<GeneratedTestCase> generatedTestCases = new ArrayList<>();

        for (String requirementId : input.coverageResult().uncoveredRequirementIds()) {
            BusinessRequirement requirement = findRequirement(
                    input.businessRequirements(),
                    requirementId);

            generatedTestCases.add(generateTestCase(requirement));
        }

        return new ImprovementOutput(generatedTestCases);
    }

    private BusinessRequirement findRequirement(
            List<BusinessRequirement> requirements,
            String requirementId) {

        return requirements.stream()
                .filter(requirement -> requirement.id().equals(requirementId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Uncovered requirement not found: " + requirementId));
    }

    private GeneratedTestCase generateTestCase(
            BusinessRequirement requirement) {

        String testCaseId = "GEN-" + requirement.id();

        return new GeneratedTestCase(
                testCaseId,
                "Generated test for " + requirement.id(),
                "Deterministic generated test case for business requirement "
                        + requirement.id(),
                List.of(),
                List.of(),
                List.of(
                        "Execute the business requirement scenario"),
                "The business requirement is satisfied",
                List.of(requirement.id()));
    }
}