# Product

## Product Purpose

FORGE is an AI Engineering capability designed to help teams understand, trace, assess, and improve Business Requirement Coverage across heterogeneous project evidence.

FORGE analyses available project evidence to identify Business Requirements, establish traceability with existing Test Cases, calculate current Business Requirement Coverage, identify Risks affecting the analysis or its results, and, when requested, generate additional Test Cases intended to improve coverage towards a user-defined target.

---

## Problem

Business Requirements are frequently distributed across different project artefacts and are not always explicitly identified or consistently structured.

Relevant information may be found in:

* Confluence pages
* Jira Epics
* Jira User Stories
* Jira Acceptance Criteria
* Jira Bugs and Expected Results
* Jira Test Cases
* Technical Requirements
* Other relevant project documentation

Teams therefore spend significant time manually reading, comparing, interpreting, and connecting information across these sources.

This makes it difficult to determine:

* Which Business Requirements actually exist
* Whether different sources contain conflicting information
* Whether requirements depend on each other
* Which Test Cases provide evidence of coverage
* Which Business Requirements remain uncovered
* Where Risks remain
* What additional Test Cases could improve the coverage

---

## Product Goal

FORGE aims to provide an evidence-driven way to:

1. Discover and consolidate Business Requirements from heterogeneous project evidence.
2. Identify conflicts, unresolved dependencies, and ambiguities.
3. Support clarification of identified issues with the user.
4. Establish traceability between Business Requirements and existing Test Cases.
5. Calculate current Business Requirement Coverage.
6. Identify and preserve Risks affecting the analysis or its results.
7. Generate additional Test Cases when requested to improve Business Requirement Coverage towards a target.

---

## Core Product Capabilities

### 1. Evidence Consolidation

FORGE accepts heterogeneous project evidence and consolidates relevant information across the available sources.

The MVP supports:

* Confluence pages
* Jira Test Cases
* Jira Bugs
* Jira User Stories
* Jira Epics

Business Requirements do not need to be explicitly labelled as such in the source material.

Evidence Consolidation produces structured topics containing the information found and references to the originating evidence.

---

### 2. Requirements Discovery

FORGE analyses the consolidated evidence to identify and structure Business Requirements.

Business Requirements may be expressed explicitly or implicitly, including within:

* Business Requirements documentation
* Technical Requirements
* User Stories
* Acceptance Criteria
* Bug Expected Results
* Epics
* Other relevant project evidence

Requirements Discovery also identifies problems affecting Business Requirements, including:

* Conflicts
* Unresolved dependencies
* Ambiguities
* Other issues requiring clarification

These problems are represented as Findings.

FORGE does not create or approve Business Requirements on behalf of stakeholders.

---

### 3. Clarification

FORGE processes Findings that require clarification and presents corresponding Questions to the user.

The user decides whether to provide answers to the Questions and whether clarification should continue.

FORGE incorporates available answers into the analysis.

A resolved Finding no longer produces an unresolved Question.

When clarification ends with an unresolved Finding, the Finding becomes a Risk.

FORGE may continue processing when some Findings remain unresolved.

---

### 4. Traceability Analysis

FORGE establishes relationships between:

* Business Requirements
* Existing Test Cases

The purpose of traceability is to make explicit the relationship between Business Requirements and Test Cases so that Business Requirement Coverage can subsequently be assessed.

A Business Requirement is not considered covered unless an established relationship with at least one Test Case exists.

Traceability Analysis may produce a Functional Specification for a Test Case that cannot be related to any Business Requirement.

A Functional Specification is an intermediate analysis artefact. It does not become a Business Requirement and does not participate directly in Business Requirement Coverage.

---

### 5. Coverage Analysis

FORGE calculates Business Requirement Coverage using:

* all Business Requirements;
* all established Business Requirement ↔ Test Case relationships;
* the available Test Cases.

A Business Requirement is covered when it has at least one established relationship with a Test Case.

Coverage Analysis produces the Coverage Result as part of the analysis.

The Coverage Result includes, as applicable:

* Business Requirement Coverage percentage;
* covered Business Requirements;
* uncovered Business Requirements;
* the target, when applicable;
* relevant Risks and other information needed to explain weaknesses or limitations.

Insufficient evidence may be reported as a Risk or explanation, but it does not create a separate coverage category and does not change the Coverage calculation.

The Coverage Result is a core FORGE capability and is not restricted to the MVP.

---

### 6. Improvement of Requirement Coverage

FORGE can improve Business Requirement Coverage by planning and, when possible, generating additional Test Cases for Business Requirements that currently have no Test Case coverage.

Improvement of Requirement Coverage is a single capability containing two internal responsibilities:

* Test Case Planning
* Test Case Generation

The user may provide a target coverage.

The target represents the minimum desired coverage.

The default target is **95%**.

FORGE attempts to achieve **at least** the requested target.

#### Test Case Planning

Planning identifies the Business Requirements requiring additional Test Case coverage and estimates the Test Cases required to reach the target.

Planning does not determine whether the planned Test Cases can be generated from the available information.

#### Test Case Generation

Test Case Generation attempts to generate the planned Test Cases.

Generation uses the available Business Requirement information as its primary context.

Approved Specifications may be used as additional context.

Unapproved Specifications are ignored.

If a Specification conflicts with the Business Requirement or available information, FORGE reports the conflict and does not generate a Test Case based on that Specification.

When sufficient information exists, FORGE generates a Test Case and links it to the corresponding Business Requirement.

When sufficient information does not exist, FORGE reports the planned Test Case and the reason why it could not be generated.

Projected Coverage is calculated using:

* all Business Requirements;
* existing Test Cases;
* generated Test Cases.

The same coverage rule applies to Projected Coverage as to current Coverage: a Business Requirement is covered when it has at least one related Test Case.

Generated Test Cases are linked to the Business Requirements they are intended to cover.

Projected Coverage must be distinguished from current Coverage because generated Test Cases have not necessarily been executed.

If the requested target cannot be achieved, FORGE explicitly reports that the target was not achieved and provides the relevant causes.

---

## Human Responsibility

FORGE supports human decision-making and does not replace stakeholder ownership.

Humans remain responsible for:

* Validating Business Requirements
* Resolving questions with stakeholders
* Deciding how conflicts should be resolved
* Accepting or rejecting the resulting understanding of requirements
* Deciding whether clarification should continue
* Reviewing generated Test Cases
* Deciding whether generated Test Cases should be adopted
* Making final decisions regarding product behaviour

FORGE provides analysis, traceability, coverage assessment, Risk identification, and proposed improvements.

---

## MVP Scope

The MVP demonstrates the complete core flow from project evidence to current coverage and coverage improvement.

The MVP includes:

* Evidence Consolidation
* Requirements Discovery
* Finding identification
* Clarification Questions
* Clarification handling
* Traceability Analysis
* Functional Specification generation when required by the analysis
* Current Business Requirement Coverage calculation
* Coverage Result
* Risk identification and preservation
* Improvement of Requirement Coverage
* Test Case Planning
* Test Case Generation
* Projected Coverage

The MVP does not require:

* Integration that writes generated Test Cases back into Jira
* A generic project risk-management system

Generated Test Cases are proposals intended to improve coverage. Their generation does not constitute evidence that they have been executed or validated.

---

## Product Boundaries

FORGE does not execute Test Cases.

FORGE may generate Test Cases and assess their intended contribution to Business Requirement Coverage, but Test Case execution and execution-based validation are outside the responsibility of the platform.

FORGE also does not:

* Replace Jira or Confluence
* Replace stakeholder decision-making
* Automatically declare requirements approved
* Treat generated Test Cases as executed evidence
* Provide generic project management or risk management