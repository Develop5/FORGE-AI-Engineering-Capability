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
Functional Risk
        ↓
Improvement of Requirement Coverage (when required)
        ↓
Generated Test Cases
        ↓
Projected Coverage
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
* Epics
* Bugs
* Acceptance Criteria
* Use Cases
* Technical documentation
* Notes and other relevant product documentation

Different executions may provide different combinations of these sources.

The absence of an Evidence Source must not be interpreted as evidence that the corresponding information does not exist.

---

## Requirements Discovery

FORGE analyses and consolidates the available evidence.

Business Requirements may be explicitly identified or may be distributed across different Evidence Sources and expressed in different forms.

### Output

* Business Requirements
* Conflicts
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

Unresolved questions do not prevent FORGE from continuing the analysis. They remain visible as unresolved information and may contribute to functional risk in the final result.

---

## Traceability Analysis

FORGE establishes relationships between:

* Business Requirements
* Existing Test Cases
* Relevant supporting evidence

The objective is to determine whether each Business Requirement is covered by the available Test Cases.

Functional Specifications may be synthesized as an intermediate artefact when they are required to establish or clarify traceability.

Specification generation is not a mandatory step of the MVP.

A Functional Specification does not by itself demonstrate Business Requirement Coverage.

---

## Coverage Analysis

FORGE calculates Business Requirement Coverage using the existing Test Cases and the Business Requirements identified from the available evidence.

The coverage definition and formula are maintained in `Glossary.md`.

FORGE must distinguish between:

* Business Requirements with identified Test Case coverage
* Business Requirements without identified Test Case coverage in the available evidence
* Business Requirements for which the available evidence is insufficient to determine coverage

The absence of an Evidence Source must not be interpreted as proof that the corresponding requirement or Test Case does not exist.

### Coverage Result

Coverage Analysis produces the Business Requirement Coverage result.

The result should provide:

* Business Requirements identified
* Matching Test Cases
* Current coverage percentage, when it can be reliably calculated
* Business Requirements without identified Test Case coverage
* Insufficient or missing evidence
* Relevant inconsistencies
* Remaining functional risk

---

## Functional Risk

FORGE uses the coverage result and identified evidence gaps or inconsistencies to expose remaining functional risk.

The risk presented by FORGE is specifically related to the functional coverage evidenced by the available Business Requirements and Test Cases.

---

## Improvement of Requirement Coverage

This capability allows FORGE to improve the current Business Requirement Coverage by generating additional Test Cases.

The user may request a target coverage level.

The target represents the **minimum desired coverage**.

The default target is **95%**.

FORGE attempts to achieve **at least** the requested target.

### Input

* Current Business Requirement Coverage
* Business Requirements without identified Test Case coverage
* Relevant traceability information
* User-defined target coverage

### FORGE

* Identifies the Business Requirements that need additional Test Case coverage to reach the target.
* Generates new Test Cases intended to cover the identified Business Requirements.
* Establishes traceability between the generated Test Cases and the relevant Business Requirements.
* Calculates the resulting projected coverage.

### Output

* Generated Test Cases
* Traceability between generated Test Cases and Business Requirements
* Projected coverage
* Business Requirements that remain without sufficient coverage
* Whether the requested target coverage was achieved

Projected coverage must be clearly distinguished from current coverage because generated Test Cases have not necessarily been executed.

If the requested target cannot be achieved, FORGE must explicitly report that the target was not achieved.

---

# MVP User Flow

The following capabilities are **part of the MVP**:

* Evidence Consolidation
* Requirements Discovery
* Clarification
* Traceability Analysis
* Coverage Analysis
* Functional Risk identification
* Improvement of Requirement Coverage
* Test Case generation for improving requirement coverage
* Projected Coverage calculation

The MVP therefore does not stop after calculating the existing coverage. It also allows the user to request improvement of that coverage by generating additional Test Cases.

---

## 1. Evidence Sources

### Input

The user provides available evidence through the platform.

Evidence may include:

* Confluence pages
* Jira Test Cases
* Jira Bugs
* Jira User Stories
* Jira Epics

The available content may contain explicit or implicit Business Requirements.

FORGE must use the information that is actually available and must not assume that a particular structure, title, field, or format exists.

---

## 2. Evidence Consolidation

### FORGE

* Ingests the available Confluence pages.
* Ingests the available Jira Test Cases.
* Ingests Jira Bugs when provided.
* Ingests Jira User Stories when provided.
* Ingests Jira Epics when provided.
* Identifies the type and relevance of the available evidence.
* Consolidates related information across the available sources.
* Identifies missing or potentially insufficient evidence.

---

## 3. Requirements Discovery

### FORGE

* Analyses the available evidence.
* Identifies and consolidates Business Requirements.
* Detects conflicts.
* Identifies unresolved dependencies.
* Identifies ambiguities.
* Produces Clarification Questions when required.

### Output

* Business Requirements
* Conflicts
* Unresolved Dependencies
* Clarification Questions

---

## 4. Clarification

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

Unresolved questions do not prevent FORGE from continuing the analysis. They remain visible as unresolved information and may contribute to functional risk in the final result.

---

## 5. Traceability Analysis

### FORGE

* Analyses the relationship between Business Requirements and existing Test Cases.
* Uses available Test Case information, including titles, summaries and steps when available.
* Uses relevant supporting evidence from the available sources.
* Identifies potential coverage gaps and inconsistencies.
* Determines when an intermediate Functional Specification is useful to establish or clarify traceability.

### Intermediate Artefact

A Functional Specification may be synthesized when required to establish or clarify traceability between Business Requirements and Test Cases.

It is not a required output of the MVP.

A Functional Specification does not by itself demonstrate Business Requirement Coverage.

---

## 6. Coverage Analysis

### FORGE

* Determines which Business Requirements have identified coverage from existing Test Cases.
* Identifies Business Requirements without identified Test Case coverage.
* Identifies cases where the available evidence is insufficient to determine coverage.
* Calculates current Business Requirement Coverage when the available evidence supports a reliable calculation.
* Produces the Coverage Result.

The calculated coverage represents the **current coverage based on existing Test Cases**.

---

## 7. Functional Risk

### FORGE

* Identifies functional risk resulting from uncovered Business Requirements.
* Identifies risk resulting from insufficient evidence.
* Identifies relevant functional inconsistencies.
* Keeps unresolved questions visible as remaining functional risk when applicable.

The MVP does not attempt to provide a generic project risk assessment.

---

## 8. Improvement of Requirement Coverage

**This capability is part of the MVP.**

The user may request improvement of the current coverage by providing a target coverage level.

The default target is **95%**.

The target represents the **minimum desired coverage**.

### Input

* Current Business Requirement Coverage
* Business Requirements without sufficient Test Case coverage
* Relevant traceability information
* User-defined target coverage

### FORGE

* Identifies the Business Requirements that require additional Test Case coverage.
* Generates new Test Cases intended to cover those Business Requirements.
* Establishes traceability between the generated Test Cases and the relevant Business Requirements.
* Determines the projected coverage resulting from the generated Test Cases.

FORGE attempts to achieve **at least** the requested target.

### Output

* Generated Test Cases
* Traceability between generated Test Cases and Business Requirements
* Projected coverage
* Business Requirements that remain without sufficient coverage
* Whether the requested target coverage was achieved

Projected coverage is distinct from current coverage. Generated Test Cases have not necessarily been executed and therefore do not constitute evidence of executed test coverage.

If the requested target cannot be achieved, FORGE explicitly reports that the target was not achieved.

---

## First Valuable Outcome

The first valuable outcome delivered by the MVP is a **Business Requirement Coverage result** showing:

* Business Requirements
* Matching Test Cases
* Current coverage percentage, when reliably calculable
* Business Requirements without identified Test Case coverage
* Insufficient evidence
* Functional inconsistencies
* Remaining functional risk

---


