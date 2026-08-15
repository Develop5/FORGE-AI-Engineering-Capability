# Product

## Product Vision

FORGE is a platform that analyses heterogeneous project evidence to discover Business Requirements, identify problems affecting those requirements, clarify unresolved issues with the user, establish traceability with Test Cases, calculate Business Requirement Coverage, identify Risks, and generate additional Test Cases intended to improve coverage.

FORGE is designed for users involved in understanding, validating, and improving Business Requirements and their coverage.

---

## Problem

Project information is distributed across multiple sources and is often incomplete, inconsistent, or ambiguous.

Business Requirements may not be explicitly documented.

Test Cases may exist without a clear relationship to the Business Requirements they are intended to verify.

As a result, teams may have difficulty determining:

* what the actual Business Requirements are;
* whether the requirements are sufficiently clear;
* which Test Cases provide evidence for each requirement;
* which requirements have no Test Case coverage;
* what risks or unresolved issues affect the analysis;
* how additional Test Cases could improve coverage.

Existing tools typically manage individual artefacts or provide reporting based on information that has already been structured.

FORGE addresses the problem by analysing heterogeneous evidence and establishing the relationships required to understand Business Requirement Coverage.

---

## Product Outcome

FORGE produces an analysis that allows the user to understand:

* the Business Requirements identified from the available Evidence;
* Findings affecting those Business Requirements;
* Questions requiring clarification;
* the answers and resulting changes to the analysis;
* the relationships between Business Requirements and Test Cases;
* Current Business Requirement Coverage;
* Risks affecting the analysis or its results;
* additional Test Cases that could improve coverage;
* Projected Coverage after adding generated Test Cases.

---

## Core Product Flow

The product flow is:

```text
Evidence
    ↓
Evidence Consolidation
    ↓
Requirements Discovery
    ↓
Findings
    ↓
Clarification
    ↓
Traceability Analysis
    ↓
Coverage Analysis
    ↓
Current Coverage + Risks
    ↓
Improvement of Requirement Coverage
    ↓
Generated Test Cases
    ↓
Projected Coverage
```

The user participates in the flow during Clarification and may review the results produced by FORGE.

The Workflow Orchestrator controls the execution flow and coordinates interaction with the user.

---

## Evidence

The user provides or makes available project evidence for analysis.

The MVP supports evidence from:

* Confluence
* Jira Test Cases
* Jira Bugs
* Jira User Stories
* Jira Epics

FORGE does not require Business Requirements to be explicitly labelled in the source evidence.

Business Requirements may be identified from:

* Business Requirements documentation;
* Technical Requirements;
* User Stories;
* Acceptance Criteria;
* Bug Expected Results;
* Epics;
* other relevant project evidence.

---

## Requirements Discovery

FORGE analyses the available Evidence to identify Business Requirements.

The analysis also identifies Findings that affect those Business Requirements.

Findings may represent:

* conflicts;
* ambiguities;
* unresolved dependencies;
* insufficient information;
* other problems requiring clarification.

Requirements Discovery does not create Questions.

---

## Clarification

FORGE presents a Question for each Finding that requires clarification.

The user may:

* provide an answer;
* decide not to answer;
* continue clarification;
* stop clarification.

FORGE incorporates available answers into the subsequent analysis.

When an answer resolves a Finding, the corresponding Question ceases to exist.

If an answer reveals a new problem, the new problem becomes a new Finding and may produce a new Question.

When clarification ends with an unresolved Finding, the Finding becomes a Risk.

FORGE does not remain blocked waiting for user answers.

---

## Traceability

FORGE establishes relationships between Business Requirements and available Test Cases.

A Business Requirement is considered covered when it has at least one related Test Case.

A Test Case may be related to multiple Business Requirements.

A Business Requirement may be related to multiple Test Cases.

FORGE does not establish a relationship when the correspondence is uncertain.

Test Cases that cannot be related to a Business Requirement may require a Specification.

---

## Coverage

FORGE calculates Current Business Requirement Coverage using the existing Test Cases and the established Business Requirement ↔ Test Case relationships.

Coverage is calculated according to the definition established in `Glossary.md`.

Risks do not participate in the Coverage calculation.

The user can therefore distinguish between:

* requirements that are covered;
* requirements that are not covered;
* issues or Risks affecting the interpretation of the analysis.

---

## Improvement of Requirement Coverage

The user may request improvement of the current Business Requirement Coverage.

The user specifies a target coverage.

The target represents the minimum desired coverage.

The default target is 95%.

FORGE attempts to achieve at least the requested target.

Improvement of Requirement Coverage contains two internal responsibilities:

* Test Case Planning;
* Test Case Generation.

Planning identifies the Business Requirements requiring additional coverage and estimates the Test Cases required to reach the target.

Generation attempts to generate those Test Cases using the available Business Requirement information and, when appropriate, approved Specifications.

Generated Test Cases are proposals for improving coverage.

Generation does not constitute execution or validation of those Test Cases.

---

## Projected Coverage

Projected Coverage represents the coverage that would result if the generated Test Cases were added to the existing Test Cases.

Projected Coverage uses the same coverage rule as Current Coverage.

Generated Test Cases have not necessarily been executed or validated.

Therefore, Projected Coverage must not be presented as evidence of executed Test Coverage.

---

## Human Responsibility

The user remains responsible for:

* reviewing generated Test Cases;
* deciding whether generated Test Cases should be accepted;
* providing clarification when required;
* making final decisions regarding requirements and product behaviour.

FORGE supports analysis and decision-making but does not replace stakeholder responsibility.

---

## Product Boundaries

FORGE does not attempt to:

* replace stakeholder decision-making;
* provide generic project management;
* provide generic project risk management;
* automatically approve generated Test Cases;
* treat generated Test Cases as executed or validated evidence.

Writing generated Test Cases back to Jira is outside the MVP.

Future integrations may allow generated Test Cases to be materialised in external systems without changing the responsibility of the Test Case Generation capability.
