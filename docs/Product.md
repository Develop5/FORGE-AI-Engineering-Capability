# Product

## Product Purpose

FORGE is an AI Engineering capability designed to help teams understand, trace, assess, and improve Business Requirement Coverage across heterogeneous project evidence.

FORGE analyses available project evidence to identify Business Requirements, establish traceability with existing Test Cases, calculate current Business Requirement Coverage, identify functional risk, and, when requested, generate additional Test Cases intended to improve coverage towards a user-defined target.

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
* Where functional risk remains
* What additional Test Cases could improve the coverage

---

## Product Goal

FORGE aims to provide an evidence-driven way to:

1. Discover and consolidate Business Requirements from heterogeneous project evidence.
2. Identify conflicts, unresolved dependencies, and ambiguities.
3. Support clarification of identified issues with the user.
4. Establish traceability between Business Requirements and existing Test Cases.
5. Calculate current Business Requirement Coverage.
6. Identify remaining functional risk.
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

---

### 2. Requirements Discovery

FORGE analyses the available evidence to identify and consolidate Business Requirements.

Business Requirements may be expressed explicitly or implicitly, including within:

* Business Requirements documentation
* Technical Requirements
* User Stories
* Acceptance Criteria
* Bug Expected Results
* Epics
* Other relevant project evidence

Requirements Discovery also identifies:

* Conflicts
* Unresolved dependencies
* Ambiguities
* Clarification Questions

FORGE does not create or approve Business Requirements on behalf of stakeholders.

---

### 3. Clarification

FORGE presents questions resulting from conflicts, ambiguities, unresolved dependencies, or insufficient evidence.

The user provides the available clarification.

FORGE incorporates the clarification into the analysis and continues processing even when some questions remain unresolved.

Unresolved questions remain visible and may contribute to the identified functional risk.

---

### 4. Traceability Analysis

FORGE establishes and clarifies relationships between:

* Business Requirements
* Existing Test Cases
* Relevant supporting evidence

The purpose of traceability is to structure and make explicit the relationship between Business Requirements and Test Cases so that Business Requirement Coverage can subsequently be assessed.

Functional Specifications may be synthesized as an intermediate artefact when required to establish or clarify traceability.

Specifications are not themselves evidence of coverage.

Specification generation is not a mandatory MVP output.

---

### 5. Coverage Analysis

FORGE calculates Business Requirement Coverage based on the available Business Requirements, traceability information, and existing Test Cases.

The coverage definition and calculation are maintained in `Glossary.md`.

Coverage Analysis produces the Coverage Result as part of the analysis.

Coverage is based on existing Test Cases and must distinguish between:

* Business Requirements with identified coverage
* Business Requirements without identified coverage
* Cases where the available evidence is insufficient to determine coverage

The Coverage Result is a core FORGE capability and is not restricted to the MVP.

---

### 6. Functional Risk

FORGE identifies functional risk associated with:

* Uncovered Business Requirements
* Insufficient evidence
* Functional inconsistencies
* Unresolved clarification questions

The product does not attempt to replace a general project risk-management process.

---

### 7. Improvement of Requirement Coverage

FORGE can improve Business Requirement Coverage by generating additional Test Cases for Business Requirements that are insufficiently covered.

The user may provide a target coverage.

The target represents the minimum desired coverage.

The default target is **95%**.

FORGE attempts to achieve **at least** the requested target.

The generated Test Cases are linked to the Business Requirements they are intended to cover.

FORGE also provides a projected coverage based on the generated Test Cases.

Projected coverage must be distinguished from current coverage because generated Test Cases have not necessarily been executed.

If the requested target cannot be achieved, FORGE must explicitly report that the target was not achieved.

---

## Human Responsibility

FORGE supports human decision-making and does not replace stakeholder ownership.

Humans remain responsible for:

* Validating Business Requirements
* Resolving questions with stakeholders
* Deciding how conflicts should be resolved
* Accepting or rejecting the resulting understanding of requirements
* Reviewing generated Test Cases
* Deciding whether generated Test Cases should be adopted
* Making final decisions regarding product behaviour

FORGE provides analysis, traceability, coverage assessment, risk identification, and proposed improvements.

---

## MVP Scope

The MVP demonstrates the complete core flow from project evidence to current coverage and coverage improvement.

The MVP includes:

* Evidence Consolidation
* Requirements Discovery
* Conflict and dependency identification
* Ambiguity identification
* Clarification Questions
* Clarification handling
* Traceability Analysis
* Intermediate Functional Specification synthesis when required for traceability
* Current Business Requirement Coverage calculation
* Coverage Result
* Functional Risk identification
* Improvement of Requirement Coverage
* Generation of additional Test Cases intended to improve coverage
* Projected Coverage

The MVP does not require:

* Integration that writes generated Test Cases back into Jira
* Execution of generated Test Cases
* Verification of the generated Test Cases through their execution
* A generic project risk-management system

Generated Test Cases are proposals intended to improve coverage. Their generation does not constitute evidence that they have been executed or validated.

---

## First Valuable Outcome

The first valuable outcome is a **Business Requirement Coverage result** that allows the user to understand:

* Which Business Requirements have been identified
* Which existing Test Cases are associated with them
* Current Business Requirement Coverage
* Which Business Requirements lack identified coverage
* Where evidence is insufficient
* What functional inconsistencies remain
* What functional risk remains

Coverage improvement is subsequently available through the Improvement of Requirement Coverage capability.

---

## Product Boundary

FORGE focuses on the relationship between Business Requirements and Test Cases.

It does not aim to:

* Replace Jira or Confluence
* Replace stakeholder decision-making
* Replace Test Case execution platforms
* Automatically declare requirements approved
* Treat generated Test Cases as executed evidence
* Provide generic project management or risk management

-----
