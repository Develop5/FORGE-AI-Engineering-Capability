# Product Definition

## Target User

### Primary User

**Product Manager**

The Product Manager is the primary consumer of the capability because they need visibility of functional risk and Business Requirement Coverage before a release.

### Secondary Stakeholders

* QA Analysts
* QA Managers
* Business Analysts
* Technical Leads

FORGE supports collaboration between these roles without changing their existing responsibilities.

---

## Product Goal

FORGE provides an AI-assisted capability to determine and justify **Business Requirement Coverage against existing Test Cases**, even when the available evidence is incomplete, dispersed, heterogeneous, or inconsistently documented.

The MVP is explicitly focused on calculating Business Requirement Coverage using existing Test Cases.

---

## Evidence Sources

FORGE is designed to operate on heterogeneous Evidence Sources. The available evidence may vary between executions and may be incomplete, inconsistent, or distributed across multiple sources.

Potential Evidence Sources include:

* Existing Test Cases from Jira/XRay
* Business Requirement documentation
* User Stories
* Bugs
* Test Case steps
* Test Case titles or summaries
* Use Cases
* Technical documentation
* Notes and other relevant product documentation

FORGE is intended to support different combinations of these Evidence Sources.

The MVP deliberately starts with **existing Test Cases from Jira/XRay as the primary Evidence Source**.

Jira Test Cases may contain detailed steps or may provide only a title, summary, or other limited information.

When the available Test Case information is insufficient, the MVP allows the user to provide additional contextual notes through a CSV input.

Additional Evidence Sources and combinations of sources are future extensions of the FORGE capability and are not required for the MVP.

---

## Requirements Discovery

Requirements Discovery is the process through which FORGE consolidates and analyses the available evidence in order to establish a reliable understanding of the Business Requirements relevant to the coverage analysis.

Requirements Discovery may produce the following intermediate results:

1. **Business Requirements identified**

   * Business Requirements identified and consolidated from the available evidence.

2. **Conflicts**

   * Contradictions detected between requirements or other evidence sources.

3. **Unresolved Dependencies**

   * Dependencies or relationships that prevent a sufficiently reliable understanding of a requirement.

4. **Clarification Questions**

   * Questions requiring input from relevant stakeholders.

Requirements Discovery does not create or approve Business Requirements. Business Requirements remain the responsibility of the appropriate business stakeholders.

---

## Specification Synthesis

FORGE structures the discovered Business Requirements and their supporting evidence into traceable specifications.

Specifications provide a structured representation of the relationship between Business Requirements and test evidence. They support human validation and traceability but do not by themselves determine Business Requirement Coverage.

---

## Traceability and Coverage

FORGE establishes traceability between the Business Requirements identified through Requirements Discovery and the available existing Test Cases.

This traceability enables the calculation of **Business Requirement Coverage**.

The coverage definition and formula are maintained in `Glossary.md`.

The MVP does not require FORGE to generate new Test Cases in order to calculate coverage.

The primary MVP outcome is the resulting **Business Requirement Coverage and the functional risk identified from the available evidence**.

---

## Intermediate Artefacts

FORGE may generate or synthesize Functional Specifications when they are required to establish or clarify traceability between Business Requirements and Test Cases.

Specifications are intermediate artefacts within the Requirements Discovery and traceability process.

They are **not the final product outcome of the MVP**.

---

## Product Principles

* FORGE does not replace Business Analysts.
* FORGE does not invent Business Requirements.
* FORGE consolidates available evidence.
* FORGE performs Requirements Discovery when information is incomplete or ambiguous.
* FORGE does not assume that all Evidence Sources are available.
* FORGE does not infer coverage without sufficient supporting evidence.
* FORGE generates traceable intermediate artefacts when required for the analysis.
* FORGE calculates Business Requirement Coverage against existing Test Cases.
* FORGE exposes inconsistencies, evidence gaps, and functional risk.
* FORGE does not change the responsibilities of the roles involved in the product lifecycle.
