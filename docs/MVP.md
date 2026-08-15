# MVP

## MVP Objective

The MVP demonstrates that FORGE can analyse heterogeneous project evidence, identify Business Requirements, establish traceability with existing Test Cases, calculate current Business Requirement Coverage, identify and preserve Risks affecting the analysis or its results, and generate additional Test Cases intended to improve coverage towards a user-defined target.

The MVP is a functional end-to-end demonstration of the core FORGE capability.

---

## MVP Input

The user provides project evidence through the platform.

The MVP supports:

* Confluence pages
* Jira Test Cases
* Jira Bugs
* Jira User Stories
* Jira Epics

Business Requirements do not need to be explicitly labelled as such in the source material.

They may be distributed across the available evidence, including:

* Business Requirements documentation
* Technical Requirements
* User Stories
* Acceptance Criteria
* Bug Expected Results
* Epics
* Other relevant project evidence

---

## MVP Flow

The MVP follows this flow:

```text id="k3x9qf"
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

The Workflow Orchestrator controls the execution flow, maintains execution state, handles user interaction, and decides which capability to invoke next.

The capabilities themselves produce domain results and do not control workflow progression.

---

## 1. Evidence Consolidation

FORGE analyses the available project evidence and consolidates relevant information across the supplied sources.

The MVP must be able to work with heterogeneous evidence rather than requiring a predefined document structure.

Evidence Consolidation produces structured topics containing:

* Topic name
* Information found about the topic
* References to the originating evidence

---

## 2. Requirements Discovery

FORGE identifies and consolidates Business Requirements from the available evidence.

The MVP identifies:

* Business Requirements
* Findings affecting those Business Requirements

Findings may represent:

* Conflicts
* Unresolved Dependencies
* Ambiguities
* Other problems requiring clarification

FORGE must be able to identify Business Requirements even when they are not explicitly contained in a document or section named "Business Requirements".

A Finding is associated with the Business Requirements affected by the identified problem.

Requirements Discovery does not create Questions.

---

## 3. Clarification

FORGE receives Business Requirements and Findings from Requirements Discovery.

Each Finding results in one Question presented to the user when clarification is required.

The human may:

* Provide answers to the Questions
* Decide not to answer the Questions
* Allow clarification to continue or stop

FORGE incorporates available answers into the subsequent analysis.

When an answer resolves a Finding, the Finding no longer remains unresolved.

If an answer reveals a new problem, the new problem is treated as a new Finding and may result in a new Question.

The Question representing a resolved Finding no longer exists.

When clarification ends with an unresolved Finding, the Finding becomes a Risk.

FORGE does not remain blocked waiting for user answers.

---

## 4. Traceability Analysis

FORGE establishes relationships between Business Requirements and existing Test Cases.

A Business Requirement is not considered covered unless an established relationship with at least one Test Case exists.

Traceability Analysis may produce a Functional Specification for a Test Case that cannot be related to any Business Requirement.

A Functional Specification is an intermediate analysis artefact.

It does not become a Business Requirement and does not participate directly in Business Requirement Coverage.

Traceability Analysis preserves the relevant Business Requirement references associated with the analysed Test Cases and Specifications.

---

## 5. Coverage Analysis

FORGE calculates the current Business Requirement Coverage using:

* All Business Requirements
* Existing Test Cases
* Established Business Requirement ↔ Test Case relationships

The coverage definition and calculation are maintained in `Glossary.md`.

A Business Requirement is covered when it has at least one established relationship with a Test Case.

Coverage Analysis produces the Coverage Result.

Insufficient evidence does not create a separate coverage category and does not change the Coverage calculation.

Insufficient evidence may instead contribute to a Risk or provide an explanation for limitations in the analysis.

Coverage Analysis preserves received Risks for subsequent reporting but does not use Risks to calculate Coverage.

---

## 6. Risks

FORGE identifies and preserves Risks affecting the completeness, reliability, or coverage of the analysis or its results.

A Risk may originate from an unresolved Finding or from another FORGE capability.

Every Risk retains, where applicable:

* The affected Business Requirement
* The identified problem or issue
* The originating FORGE capability
* The unresolved question that led to it, when applicable

Risks are not used in the Coverage calculation.

They are preserved for subsequent reporting so that the Coverage Result can explain weaknesses, unresolved issues, or limitations affecting the analysis.

The MVP does not attempt to provide generic project risk management.

---

## 7. Improvement of Requirement Coverage

**This capability is part of the MVP.**

The user may request improvement of the current Business Requirement Coverage.

The user may specify a target coverage.

The target represents the **minimum desired coverage**.

The default target is **95%**.

FORGE attempts to achieve **at least** the requested target.

Improvement of Requirement Coverage is a single capability containing two internal responsibilities:

* Test Case Planning
* Test Case Generation

### Test Case Planning

Planning:

* Identifies Business Requirements requiring additional Test Case coverage.
* Estimates the Test Cases required to reach the target.
* Produces the plan for the requested improvement.

Planning does not determine whether the planned Test Cases are technically generable from the available information.

Planning receives the target but does not decide where the target originated.

### Test Case Generation

Generation:

* Receives the planned Test Case targets and the relevant Business Requirements.
* Uses the available Business Requirement information as its primary context.
* May use approved Specifications as additional context.
* Ignores unapproved Specifications.
* Reports conflicts involving Specifications without generating a Test Case based on the conflicting Specification.
* Generates a Test Case when sufficient information exists.
* Reports a planned Test Case as not generated when sufficient information does not exist, together with the reason.

Generation may produce both generated and non-generated Test Cases in the same result.

Generated Test Cases are associated with the Business Requirements they are intended to cover.

Projected Coverage is calculated using:

* All Business Requirements
* Existing Test Cases
* Generated Test Cases

The same coverage rule applies to Projected Coverage as to Current Coverage: a Business Requirement is covered when it has at least one related Test Case.

If the requested target cannot be achieved, FORGE explicitly reports that the target was not achieved and provides the relevant causes.

---

## Current Coverage vs Projected Coverage

The MVP must clearly distinguish between:

**Current Coverage**

Coverage calculated from the existing Test Cases.

**Projected Coverage**

Coverage that would result from adding the Test Cases generated by FORGE.

Generated Test Cases have not necessarily been executed.

Therefore, Projected Coverage must not be presented as evidence of executed Test Coverage.

---

## Human Validation

The human remains responsible for:

* Reviewing generated Test Cases
* Deciding whether generated Test Cases should be accepted
* Making final decisions regarding requirements and product behaviour

FORGE does not consider a generated Test Case to be executed or validated merely because it generated it.

---

## MVP Output

The MVP provides:

* Business Requirements
* Findings and unresolved issues, where applicable
* Clarification Questions, where applicable
* Traceability between Business Requirements and Test Cases
* Functional Specifications, where applicable
* Current Business Requirement Coverage
* Risks
* Generated Test Cases intended to improve coverage
* Non-generated planned Test Cases and their reasons, where applicable
* Traceability between generated Test Cases and Business Requirements
* Projected Coverage
* Remaining uncovered Business Requirements
* Whether the requested coverage target was achieved
* Relevant reasons when the requested target was not achieved

---

## MVP Boundaries

The MVP does not require:

* Writing generated Test Cases back to Jira
* Automatically approving generated Test Cases
* Replacing stakeholder decision-making
* Providing generic project management
* Providing generic project risk management

Writing generated Test Cases back to Jira is intentionally outside the MVP. A future integration may allow generated Test Cases to be materialised in Jira without changing the responsibility of the Test Case Generation capability.

Generated Test Cases are proposals for improving requirement coverage. Their generation does not constitute evidence that they have been executed or validated.
