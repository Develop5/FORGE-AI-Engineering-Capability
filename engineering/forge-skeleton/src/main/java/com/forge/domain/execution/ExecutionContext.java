package com.forge.domain.execution;

import com.forge.domain.clarification.Question;
import com.forge.domain.coverage.CoverageResult;
import com.forge.domain.coverage.ProjectedCoverage;
import com.forge.domain.evidence.Evidence;
import com.forge.domain.evidence.EvidenceTopic;
import com.forge.domain.finding.Finding;
import com.forge.domain.finding.Risk;
import com.forge.domain.requirement.BusinessRequirement;
import com.forge.domain.testcase.GeneratedTestCase;
import com.forge.domain.testcase.Specification;
import com.forge.domain.testcase.TestCase;
import com.forge.domain.traceability.RequirementTestCaseRelation;

import java.util.ArrayList;
import java.util.List;

public final class ExecutionContext {

    private final List<Evidence> evidence = new ArrayList<>();
    private final List<EvidenceTopic> evidenceTopics = new ArrayList<>();

    private final List<BusinessRequirement> businessRequirements =
            new ArrayList<>();

    private final List<Finding> findings = new ArrayList<>();
    private final List<Risk> risks = new ArrayList<>();

    private Question pendingQuestion;
    private String pendingResponse;

    private final List<TestCase> existingTestCases = new ArrayList<>();
    private final List<Specification> specifications = new ArrayList<>();
    private final List<RequirementTestCaseRelation> traceabilityRelations =
            new ArrayList<>();
    private final List<GeneratedTestCase> generatedTestCases =
            new ArrayList<>();

    private CoverageResult coverageResult;
    private ProjectedCoverage projectedCoverage;

    public List<Evidence> evidence() {
        return List.copyOf(evidence);
    }

    public void addEvidence(Evidence value) {
        evidence.add(value);
    }

    public List<EvidenceTopic> evidenceTopics() {
        return List.copyOf(evidenceTopics);
    }

    public void addEvidenceTopic(EvidenceTopic value) {
        evidenceTopics.add(value);
    }

    public List<BusinessRequirement> businessRequirements() {
        return List.copyOf(businessRequirements);
    }

    public void addBusinessRequirement(BusinessRequirement value) {
        businessRequirements.add(value);
    }

    public void replaceBusinessRequirements(
            List<BusinessRequirement> values) {

        businessRequirements.clear();
        businessRequirements.addAll(values);
    }

    public List<Finding> findings() {
        return List.copyOf(findings);
    }

    public void addFinding(Finding value) {
        findings.add(value);
    }

    public void replaceFindings(List<Finding> values) {
        findings.clear();
        findings.addAll(values);
    }

    public List<Risk> risks() {
        return List.copyOf(risks);
    }

    public void addRisk(Risk value) {
        risks.add(value);
    }

    public Question pendingQuestion() {
        return pendingQuestion;
    }

    public void setPendingQuestion(Question pendingQuestion) {
        this.pendingQuestion = pendingQuestion;
    }

    public void clearPendingQuestion() {
        this.pendingQuestion = null;
    }

    public String pendingResponse() {
        return pendingResponse;
    }

    public void setPendingResponse(String pendingResponse) {
        this.pendingResponse = pendingResponse;
    }

    public void clearPendingResponse() {
        this.pendingResponse = null;
    }

    public List<TestCase> existingTestCases() {
        return List.copyOf(existingTestCases);
    }

    public void addExistingTestCase(TestCase value) {
        existingTestCases.add(value);
    }

    public List<Specification> specifications() {
        return List.copyOf(specifications);
    }

    public void addSpecification(Specification value) {
        specifications.add(value);
    }

    public List<RequirementTestCaseRelation> traceabilityRelations() {
        return List.copyOf(traceabilityRelations);
    }

    public void addTraceabilityRelation(
            RequirementTestCaseRelation value) {

        traceabilityRelations.add(value);
    }

    public List<GeneratedTestCase> generatedTestCases() {
        return List.copyOf(generatedTestCases);
    }

    public void addGeneratedTestCase(GeneratedTestCase value) {
        generatedTestCases.add(value);
    }

    public CoverageResult coverageResult() {
        return coverageResult;
    }

    public void setCoverageResult(CoverageResult coverageResult) {
        this.coverageResult = coverageResult;
    }

    public ProjectedCoverage projectedCoverage() {
        return projectedCoverage;
    }

    public void setProjectedCoverage(
            ProjectedCoverage projectedCoverage) {

        this.projectedCoverage = projectedCoverage;
    }
}