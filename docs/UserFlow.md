# E2E User Flow

## End-to-End Flow

```text
Available Evidence
        ↓
Evidence Consolidation
        ↓
Requirements Discovery
        ↓
[Business Requirements + Conflicts
 + Unresolved Dependencies
 + Clarification Questions]
        ↓
Clarification (when required)
        ↓
Traceability Analysis
        ↓
Coverage Analysis
        ↓
Coverage Result
        ↓
Functional Risk
```

The flow is evidence-driven. Different executions may provide different combinations of Evidence Sources, and the available evidence may be incomplete or insufficient.

FORGE must adapt its analysis to the evidence provided and explicitly identify when the available evidence is insufficient to establish reliable coverage.

---

## Evidence Sources

FORGE is designed to operate on heterogeneous Evidence Sources.

Potential Evidence Sources include:

* Business Requirement documentation
* Existing Test Cases
* Test Case steps
* Test Case titles or summaries
* User Stories
* Bugs
* Use Cases
* Technical documentation
* Notes and other relevant product documentation

Different executions may provide different combinations of these sources.

The absence of an Evidence Source must not be interpreted as evidence that the corresponding information does not exist.

---

## Requirements Discovery

FORGE analyses and consolidates the available evidence.

### Output

* Business Requirements identified from the available evidence
* Conflicts between requirements or evidence
* Unresolved Dependencies
* Clarification Questions

FORGE does not create or approve Business Requirements.

---

## Clarification

Clarification is performed when the available evidence is insufficient, ambiguous, or contradictory.

### FORGE

* Presents the Clarification Questions.

### Human

* Obtains the required answers from the relevant stakeholders.
* Provides the answers to FORGE.

### FORGE

* Incorporates the answers into the analysis.
* Updates the affected Business Requirements or their understanding.
* Re-evaluates the affected traceability and coverage.
* Keeps unresolved questions visible when answers are not available.

If sufficient clarification cannot be obtained, FORGE must distinguish between conclusions supported by evidence and cases where the available evidence is insufficient.

---

## Traceability Analysis

FORGE establishes relationships between:

* Business Requirements
* Existing Test Cases
* Relevant supporting evidence

The objective is to determine whether each Business Requirement is covered by the available Test Cases.

Functional Specifications may be synthesized as an intermediate artefact when they are required to establish or clarify traceability.

Specification generation is not a mandatory step of the MVP.

---

## Coverage Analysis

FORGE calculates Business Requirement Coverage using the existing Test Cases and the Business Requirements identified from the available evidence.

The coverage definition and formula are maintained in `Glossary.md`.

FORGE must distinguish between:

* Business Requirements with identified Test Case coverage
* Business Requirements without identified Test Case coverage in the available evidence
* Business Requirements for which the available evidence is insufficient to determine coverage

The absence of an Evidence Source must not be interpreted as proof that the corresponding requirement or Test Case does not exist.

---

## Coverage Result

The primary outcome of the MVP is a Business Requirement Coverage result.

The result should provide:

* Business Requirements identified
* Matching Test Cases
* Coverage percentage, when it can be reliably calculated
* Business Requirements without identified Test Case coverage
* Insufficient or missing evidence
* Relevant inconsistencies
* Remaining functional risk

---

## Functional Risk

FORGE uses the coverage result and identified evidence gaps or inconsistencies to expose remaining functional risk.

The MVP does not attempt to provide a generic project risk assessment. The risk presented by FORGE is specifically related to the functional coverage evidenced by the available Business Requirements and Test Cases.

---

# MVP User Flow


The MVP deliberately starts with **existing Test Cases from Jira/XRay** as its primary Evidence Source.

Jira Bugs and user-provided CSV notes may optionally supplement the Test Cases when additional evidence is available or required.

The MVP does not require other Evidence Sources such as Confluence Business Requirement pages, User Stories, Use Cases, or Technical documentation.

## 1. Jira/XRay Test Cases

### Input

The user provides existing Test Cases from Jira/XRay.

Test Cases may contain:

* Test Case title
* Test Case description or summary
* Test Case steps
* Other available Test Case information

The MVP must support Test Cases both with and without detailed steps.

FORGE must use the information that is actually available and must not assume that Test Case steps exist.

---

## 2. Optional Jira Bugs

The user may provide Jira Bugs as supplementary evidence.

Bugs may provide additional information about:

* Implemented or observed behaviour
* Functional areas
* Known defects
* Relationships between functionality and existing tests

Jira Bugs are optional and are not required when the Test Cases provide sufficient evidence.

---

## 3. Optional CSV Notes

When the available Jira evidence is insufficient, the user may provide additional contextual notes through a CSV file.

The CSV is supplementary evidence used to provide additional context for Requirements Discovery and traceability analysis.

The CSV is optional and is not required when the available Jira evidence provides sufficient information.

---

## 4. Evidence Consolidation

### FORGE

* Ingests the available Jira/XRay Test Cases.
* Ingests Jira Bugs when provided.
* Ingests the optional CSV notes when provided.
* Identifies the type and relevance of the available evidence.
* Consolidates the available information.
* Identifies missing or potentially insufficient evidence.

---

## 5. Requirements Discovery

### FORGE

* Analyses the available evidence.
* Identifies and consolidates Business Requirements.
* Detects conflicts.
* Identifies unresolved dependencies.
* Produces Clarification Questions when required.

### Output

* Business Requirements
* Conflicts
* Unresolved Dependencies
* Clarification Questions

---

## 6. Clarification

### FORGE

* Presents the Clarification Questions.

### Human

* Obtains answers from relevant stakeholders.
* Provides the answers to FORGE.

### FORGE

* Incorporates the answers.
* Updates the affected analysis.
* Re-evaluates traceability and coverage.

Pending questions remain visible when they cannot be resolved.

---

## 7. Traceability Analysis

### FORGE

* Analyses the relationship between Business Requirements and existing Test Cases.
* Uses available Test Case information, including titles, summaries and steps when available.
* Uses supplementary CSV notes when provided.
* Identifies potential coverage gaps and inconsistencies.

### Intermediate Artefact

A Functional Specification may be synthesized when required to establish or clarify traceability.

It is not a required output of the MVP.

---

## 8. Coverage Analysis

### FORGE

* Determines which Business Requirements have identified coverage from existing Test Cases.
* Identifies Business Requirements without identified Test Case coverage.
* Identifies cases where the available evidence is insufficient to determine coverage.
* Calculates Business Requirement Coverage when the available evidence supports a reliable calculation.

---

## 9. Coverage Result

The MVP ends with a Coverage Result that provides the information defined in [First Valuable Outcome](#first-valuable-outcome).

---

## First Valuable Outcome

The first valuable outcome delivered by the MVP is a **Business Requirement Coverage result** showing:

* Business Requirements
* Matching Test Cases
* Coverage percentage, when reliably calculable
* Business Requirements without identified Test Case coverage
* Insufficient evidence
* Functional inconsistencies
* Remaining functional risk
* No more content is needed
