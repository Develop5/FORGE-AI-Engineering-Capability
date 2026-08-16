package com.forge.capabilities.coverage;

import com.forge.domain.coverage.CoverageResult;
import com.forge.domain.requirement.BusinessRequirement;
import com.forge.domain.testcase.TestCase;
import com.forge.domain.traceability.RelationType;
import com.forge.domain.traceability.RequirementTestCaseRelation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class LocalCoverageAnalysis
        implements CoverageAnalysisCapability {

    @Override
    public CoverageAnalysisOutput execute(
            CoverageAnalysisInput input) {

        Set<String> coveredRequirementIds =
                findCoveredRequirementIds(
                        input.traceabilityRelations());

        List<String> uncoveredRequirementIds =
                input.businessRequirements().stream()
                        .map(BusinessRequirement::id)
                        .filter(requirementId ->
                                !coveredRequirementIds.contains(requirementId))
                        .toList();

        long coveredRequirements =
                input.businessRequirements().stream()
                        .map(BusinessRequirement::id)
                        .filter(coveredRequirementIds::contains)
                        .count();

        double currentCoverage =
                calculateCoverage(
                        coveredRequirements,
                        input.businessRequirements().size());

        List<TestCase> relatedTestCases =
                findRelatedTestCases(
                        input.testCases(),
                        input.traceabilityRelations());

        CoverageResult result =
                new CoverageResult(
                        input.businessRequirements(),
                        relatedTestCases,
                        currentCoverage,
                        uncoveredRequirementIds,
                        input.risks());

        return new CoverageAnalysisOutput(result);
    }

    private static Set<String> findCoveredRequirementIds(
            List<RequirementTestCaseRelation> relations) {

        Set<String> coveredRequirementIds = new HashSet<>();

        for (RequirementTestCaseRelation relation : relations) {
            if (relation.relationType() == RelationType.COVERS) {
                coveredRequirementIds.add(
                        relation.requirementId());
            }
        }

        return coveredRequirementIds;
    }

    private static double calculateCoverage(
            long coveredRequirements,
            int totalRequirements) {

        if (totalRequirements == 0) {
            return 0.0;
        }

        return (double) coveredRequirements
                / totalRequirements
                * 100.0;
    }

    private static List<TestCase> findRelatedTestCases(
            List<TestCase> testCases,
            List<RequirementTestCaseRelation> relations) {

        Set<String> relatedTestCaseIds =
                relations.stream()
                        .filter(relation ->
                                relation.relationType() == RelationType.COVERS)
                        .map(RequirementTestCaseRelation::testCaseId)
                        .collect(java.util.stream.Collectors.toSet());

        return testCases.stream()
                .filter(testCase ->
                        relatedTestCaseIds.contains(testCase.id()))
                .toList();
    }
}