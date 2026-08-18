package com.forge.capabilities.traceability;

import com.forge.domain.requirement.BusinessRequirement;
import com.forge.domain.testcase.TestCase;
import com.forge.domain.traceability.RelationType;
import com.forge.domain.traceability.RequirementTestCaseRelation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public final class LocalTraceabilityAnalysis
        implements TraceabilityAnalysisCapability {

    @Override
    public TraceabilityAnalysisOutput execute(
            TraceabilityAnalysisInput input) {

        Objects.requireNonNull(
                input,
                "input must not be null");

        List<RequirementTestCaseRelation> relations =
                new ArrayList<>();

        for (BusinessRequirement requirement
                : input.businessRequirements()) {

            for (TestCase testCase
                    : input.testCases()) {

                if (covers(requirement, testCase)) {
                    relations.add(
                            new RequirementTestCaseRelation(
                                    requirement.id(),
                                    testCase.id(),
                                    RelationType.COVERS));
                }
            }
        }

        return new TraceabilityAnalysisOutput(relations);
    }

    private boolean covers(
            BusinessRequirement requirement,
            TestCase testCase) {

        if (testCase.sourceReference() != null
                && testCase.sourceReference()
                .equals(requirement.id())) {
            return true;
        }

        return contentSupportsCoverage(
                requirement,
                testCase);
    }

    private boolean contentSupportsCoverage(
            BusinessRequirement requirement,
            TestCase testCase) {

        String requirementText =
                normalize(
                        requirement.title()
                                + " "
                                + requirement.description());

        String testCaseText =
                normalize(
                        testCase.title()
                                + " "
                                + testCase.description()
                                + " "
                                + String.join(
                                " ",
                                testCase.preconditions())
                                + " "
                                + String.join(
                                " ",
                                testCase.steps())
                                + " "
                                + testCase.expectedResult());

        List<String> requirementTerms =
                meaningfulTerms(requirementText);

        if (requirementTerms.isEmpty()) {
            return false;
        }

        long matchedTerms =
                requirementTerms.stream()
                        .filter(testCaseText::contains)
                        .count();

        return matchedTerms == requirementTerms.size();
    }

    private List<String> meaningfulTerms(
            String text) {

        return List.of(text.split(" "))
                .stream()
                .filter(term -> term.length() >= 4)
                .filter(term -> !isStopWord(term))
                .distinct()
                .toList();
    }

    private boolean isStopWord(
            String term) {

        return switch (term) {
            case "must",
                 "should",
                 "shall",
                 "with",
                 "from",
                 "that",
                 "this",
                 "user",
                 "system",
                 "before",
                 "after",
                 "when",
                 "then",
                 "into",
                 "onto",
                 "using",
                 "provide",
                 "support" -> true;
            default -> false;
        };
    }

    private String normalize(
            String text) {

        return text
                .toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9áéíóúüñ ]", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }
}