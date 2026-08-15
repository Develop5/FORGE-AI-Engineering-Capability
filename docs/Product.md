# Product Definition

## Target User

### Primary User

**Product Manager**

The Product Manager is the primary consumer of FORGE because they need visibility of Business Requirement Coverage, identified Risks, and the opportunities to improve coverage before a release.

### Secondary Stakeholders

* QA Analysts
* QA Managers
* Business Analysts
* Technical Leads

FORGE supports collaboration between these roles without changing their existing responsibilities.

---

## Product Goal

FORGE provides an AI-assisted capability to determine and justify **Business Requirement Coverage against Test Cases**, even when the available Evidence is incomplete, dispersed, heterogeneous, or inconsistently documented.

FORGE also identifies weaknesses affecting the analysis and can propose and generate additional Test Cases to improve insufficient Business Requirement Coverage.

---

## Evidence Sources

FORGE is designed to operate on heterogeneous Evidence Sources. The available Evidence may vary between executions and may be incomplete, inconsistent, or distributed across multiple sources.

Potential Evidence Sources include:

* Confluence pages
* Jira Test Cases
* Jira Bugs
* Jira User Stories
* Jira Epics
* Business Requirement documentation
* Acceptance Criteria
* Technical Requirements
* Test Case steps
* Test Case titles or summaries
* Use Cases
* Technical documentation
* Notes and other relevant product documentation

FORGE is intended to support different combinations of these Evidence Sources.

The MVP supports:

* Confluence pages
* Jira Test Cases
* Jira Bugs
* Jira User Stories
* Jira Epics

Business Requirements may be identified from explicit or implicit Evidence across these sources.

Additional Evidence Sources and combinations of sources may be introduced in future extensions of the FORGE capability.

---

## Requirements Discovery

Requirements Discovery is the process through which FORGE consolidates and analyses available Evidence in order to establish a reliable understanding of the Business Requirements relevant to the coverage analysis.

Requirements Discovery may produce:

1. **Business Requirements**

   * Business Requirements identified and consolidated from the available Evidence.

2. **Findings**

   * Problems affecting one or more Business Requirements, including ambiguities, conflicts, unresolved dependencies, insufficient information, or other relevant inconsistencies.

3. **Business Requirement relationships**

   * Relevant relationships between Business Requirements identified from the available Evidence.

Requirements Discovery does not create Questions.

Requirements Discovery does not approve Business Requirements. Business Requirements remain subject to the appropriate business ownership and validation.

---

## Clarification

Clarification processes Findings that require user input.

A Finding may be presented to the user as a Question.

Clarification allows the user to provide information that may resolve a Finding or reveal a new problem.

When Clarification ends with an unresolved Finding, the Finding becomes a Risk.

Clarification therefore provides FORGE with a way to expose and resolve weaknesses in the available information rather than silently making unsupported assumptions.

---

## Specifications

FORGE may produce Specifications when additional structure or precision is required to establish or clarify traceability between Business Requirements and Test Cases.

Specifications are intermediate analysis artefacts.

A Specification does not become a Business Requirement and does not by itself demonstrate Business Requirement Coverage.

Approved Specifications may subsequently be used as contextual information during Test Case Generation.

---

## Traceability and Coverage

FORGE establishes traceability between Business Requirements and available Test Cases.

This traceability enables the calculation of **Business Requirement Coverage**.

The coverage definition and formula are maintained in `Glossary.md`.

A Business Requirement is covered when it has at least one related Test Case.

Business Requirements without Test Case coverage are identified explicitly as uncovered Business Requirements.

An uncovered Business Requirement is not automatically a Risk.

Risks and other explanatory information may be associated with the Coverage Result, but they do not participate in the Coverage calculation.

---

## Improvement of Requirement Coverage

Improvement of Requirement Coverage is a FORGE capability that improves Business Requirement Coverage by planning and generating additional Test Cases for Business Requirements that currently have no Test Case coverage.

The capability includes:

* **Test Case Planning** — estimates the Test Cases required to reach a requested coverage target.
* **Test Case Generation** — attempts to generate the planned Test Cases using the available Business Requirement information and approved Specifications as context.

Test Case Generation may determine that sufficient information is not available to generate a particular Test Case.

When this occurs, FORGE reports the planned Test Case and the reason it could not be generated.

Conflicting Specifications are reported and are not used as the basis for generating a Test Case.

Generated Test Cases are proposals for improving coverage. Their generation does not constitute evidence that they have been executed or validated.

---

## Coverage Result

Coverage Analysis produces the structured **Coverage Result**.

The Coverage Result may include:

* Business Requirements
* Related Test Cases
* Current Coverage
* Uncovered Business Requirements
* Risks
* Generated Test Cases and information relevant to Projected Coverage
* Other information required to explain the analysis

Risks and explanatory information do not participate in the Coverage calculation.

The Interface or other reporting mechanism is responsible for deciding how the Coverage Result is presented to users.

---

## Product Boundaries

FORGE is responsible for analysing Evidence, identifying and clarifying Business Requirements, establishing Test Case traceability, calculating Business Requirement Coverage, identifying Risks, and proposing or generating additional Test Cases to improve coverage.

FORGE does not:

* replace the business ownership or approval of Business Requirements;
* execute generated Test Cases;
* validate generated Test Cases through their execution;
* make release decisions on behalf of stakeholders;
* act as a generic project risk-management system;
* require generated Test Cases to be written back to Jira as part of the MVP.

Generated Test Cases are analysis outputs and proposals for improving Business Requirement Coverage.

A future integration may allow generated Test Cases to be written back to Jira without changing the responsibility of the Test Case Generation capability.

---

## Product Principles

* FORGE does not replace Business Analysts.
* FORGE does not invent Business Requirements.
* FORGE consolidates available Evidence.
* FORGE performs Requirements Discovery when information is incomplete or ambiguous.
* FORGE does not assume that all Evidence Sources are available.
* FORGE preserves references to supporting Evidence.
* FORGE exposes Findings and allows unresolved Findings to become Risks.
* FORGE does not treat uncovered Business Requirements as Risks automatically.
* FORGE calculates Business Requirement Coverage against Test Cases.
* FORGE distinguishes Current Coverage from Projected Coverage.
* FORGE generates traceable intermediate artefacts when required for the analysis.
* FORGE can propose and generate additional Test Cases to improve Business Requirement Coverage.
* FORGE exposes inconsistencies, Evidence gaps, and other Risks affecting the analysis.
* FORGE does not change the responsibilities of the roles involved in the product lifecycle.