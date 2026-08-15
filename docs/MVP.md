# MVP Scope

## MVP Objective

Demonstrate that FORGE can:

1. identify and consolidate Business Requirements from heterogeneous project Evidence;
2. identify and clarify Findings affecting those Business Requirements;
3. establish traceability between Business Requirements and existing Test Cases;
4. calculate Business Requirement Coverage;
5. identify weaknesses and Risks affecting the analysis;
6. plan and generate additional Test Cases intended to improve Business Requirement Coverage.

The MVP therefore demonstrates both **assessment of Current Coverage** and **Improvement of Requirement Coverage**.

The requested coverage target represents the minimum desired coverage for the improvement process and defaults to **95%**.

FORGE attempts to reach at least the requested target, subject to the information available for Test Case Generation.

---

## MVP Evidence Sources

The MVP supports heterogeneous Evidence Sources.

The initial MVP Evidence Sources are:

* Confluence pages
* Jira/XRay Test Cases
* Jira Bugs
* Jira User Stories
* Jira Epics

Evidence may be explicit or implicit.

Business Requirements may be identified from information distributed across these sources.

Available Evidence may be incomplete, inconsistent, ambiguous, or distributed across multiple sources.

FORGE must preserve references to the Evidence used to identify and analyse Business Requirements.

---

## MVP Flow

The MVP follows the following logical flow:

```text
Evidence Sources
      ↓
Evidence Consolidation
      ↓
Requirements Discovery
      ↓
Findings
      ↓
Clarification
      ↓
Business Requirements + Risks
      ↓
Traceability Analysis
      ↓
Coverage Analysis
      ↓
Current Coverage
      ↓
Improvement of Requirement Coverage
      ├── Test Case Planning
      └── Test Case Generation
      ↓
Generated Test Cases
      ↓
Projected Coverage
````

The Workflow Orchestrator controls the execution flow and determines which capability executes next.

The capabilities do not invoke one another directly.

The MVP does not require every execution to follow every step. The Workflow Orchestrator determines the applicable path according to the current execution state and capability results.

---

## Requirements Discovery

FORGE analyses the available Evidence to identify and consolidate Business Requirements relevant to the coverage analysis.

Requirements Discovery may identify:

* Business Requirements;
* conflicts;
* ambiguities;
* unresolved dependencies;
* insufficient information;
* other Findings affecting Business Requirements.

Requirements Discovery creates Findings for identified problems requiring clarification.

Requirements Discovery does not create Questions.

FORGE does not create or approve Business Requirements on behalf of the business. It identifies and structures Business Requirements from available Evidence.

---

## Clarification

Clarification processes Findings that require user input.

A Finding may be presented to the user as a Question.

The user may:

* provide an answer;
* decide not to answer;
* decide to continue or stop clarification.

Clarification incorporates the available responses into the subsequent analysis.

When Clarification ends with an unresolved Finding, the Finding becomes a Risk.

If a response reveals a new problem, FORGE creates a new Finding that may result in a new Question.

---

## Traceability Analysis

FORGE establishes traceability between Business Requirements and existing Test Cases.

The MVP must support Test Cases with:

* detailed steps;
* descriptions or summaries;
* titles or other available information;
* incomplete information.

Traceability Analysis identifies:

* Business Requirements with related Test Cases;
* Business Requirements without related Test Cases;
* Test Cases that cannot be related to a Business Requirement.

When required, Traceability Analysis may produce Specifications for Test Cases that cannot be related to a Business Requirement.

A Specification is an intermediate analysis artefact and does not become a Business Requirement.

---

## Coverage Analysis

The MVP calculates **Business Requirement Coverage** using the established Business Requirement ↔ Test Case relationships.

The coverage definition and formula are maintained in `Glossary.md`.

A Business Requirement is covered when it has at least one related Test Case.

The MVP must distinguish between:

* Business Requirements with Test Case coverage;
* Business Requirements without Test Case coverage.

An uncovered Business Requirement is not automatically a Risk.

Risks and other explanatory information do not participate in the Coverage calculation.

---

## Current Coverage

Current Coverage represents the Business Requirement Coverage supported by the existing Test Cases available to FORGE.

Coverage Analysis produces the Coverage Result containing the Current Coverage analysis and the information required to understand the result.

The Coverage Result may include:

* Business Requirements;
* related Test Cases;
* Current Coverage;
* uncovered Business Requirements;
* Risks;
* other relevant explanatory information.

---

## Improvement of Requirement Coverage

Improvement of Requirement Coverage is part of the MVP.

It is a single capability containing:

* Test Case Planning;
* Test Case Generation.

### Test Case Planning

Planning receives the requested coverage target and estimates the additional Test Cases required to reach it.

Planning identifies:

* Business Requirements requiring additional coverage;
* estimated Test Cases required;
* the generation plan.

Planning does not determine whether the planned Test Cases can technically be generated.

### Test Case Generation

Generation attempts to produce the Test Cases identified by Planning.

Generation uses:

* the Business Requirement;
* available Evidence and references;
* approved Specifications, when available.

Generation determines whether sufficient information exists to generate each planned Test Case.

When sufficient information exists, FORGE generates the Test Case.

When information is insufficient, FORGE reports the planned Test Case and the reason it was not generated.

If a Specification conflicts with the Business Requirement or available information, FORGE reports the conflict and does not generate the Test Case based on that Specification.

A generation result may therefore contain both:

* generated Test Cases;
* planned Test Cases that were not generated, together with their reasons.

---

## Projected Coverage

Projected Coverage represents the Business Requirement Coverage that would result from adding the Test Cases generated by FORGE to the existing Test Cases.

Projected Coverage uses the same Coverage rule as Current Coverage.

Generated Test Cases are not evidence of executed or validated Test Coverage.

The MVP does not execute generated Test Cases.

---

## MVP Outcome

The primary domain outcome of the MVP is the **Coverage Result**, including the information required to understand both Current Coverage and the potential improvement of coverage.

The MVP may expose:

* identified Business Requirements;
* supporting Evidence references;
* Findings;
* clarification outcomes;
* Risks;
* Business Requirement ↔ Test Case traceability;
* Current Coverage;
* uncovered Business Requirements;
* Test Case Generation plans;
* generated Test Cases;
* non-generated planned Test Cases and reasons;
* Projected Coverage.

The Interface or reporting layer determines how this information is presented to users.

---

## Explicitly Out of MVP

The MVP does not require:

* writing generated Test Cases back to Jira;
* authentication and identity management as a FORGE domain capability;
* user management;
* real-time collaboration;
* comments;
* approval workflows;
* project management;
* advanced editing;
* multiple export formats.

The following are outside the scope of FORGE itself, not merely the MVP:

* execution of generated Test Cases;
* validation of generated Test Cases through their execution.

A future integration may allow generated Test Cases to be written back to Jira.

FORGE does not become responsible for executing or validating those Test Cases through execution.

---

## MVP Boundaries

The MVP focuses on Business Requirement Coverage and its improvement.

It does not:

* replace Business Analysts or Product Managers;
* approve Business Requirements;
* make release decisions;
* act as a generic project risk-management system;
* treat uncovered Business Requirements as Risks automatically;
* treat insufficient Evidence as a separate Coverage category;
* consider generated Test Cases to be executed or validated;
* execute Test Cases.

The MVP demonstrates the ability of FORGE to identify weaknesses in available Evidence and Requirements and to propose or generate additional Test Cases when sufficient information is available.

```
