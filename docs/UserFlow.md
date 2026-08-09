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

The flow is evidence-driven. Not all Evidence Sources are expected to be available in every execution.

FORGE must adapt its analysis to the evidence provided and explicitly identify when the available evidence is insufficient to establish reliable coverage.

---

## Evidence Sources

The flow may start with any relevant combination of available evidence.

Examples include:

* Business Requirement documentation
* Existing Test Cases
* Test Case steps
* Test Case titles or summaries
* Bugs
* Use Cases
* Technical documentation
* Notes and other relevant product documentation

For the MVP, the primary Evidence Sources are:

* Existing Test Cases
* Business Requirement evidence

Neither source is required to be complete.

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

* Business Requirements covered by available tests
* Business Requirements not covered by available tests
* Business Requirements for which the available evidence is insufficient to determine coverage

The absence of evidence must not be interpreted as proof that the corresponding requirement or test does not exist.

---

## Coverage Result

The primary outcome of the MVP is a Business Requirement Coverage result.

The result should provide:

* Business Requirements identified
* Matching Test Cases
* Coverage percentage, when it can be reliably calculated
* Business Requirements without identified test coverage
* Insufficient or missing evidence
* Relevant inconsistencies
* Remaining functional risk

---

## Functional Risk

FORGE uses the coverage result and identified evidence gaps or inconsistencies to expose remaining functional risk.

The MVP does not attempt to provide a generic project risk assessment. The risk presented by FORGE is specifically related to the functional coverage evidenced by the available Business Requirements and Test Cases.

---

# MVP User Flow

## 1. Available Evidence

### Input

A variable combination of available evidence, including:

* Existing Test Cases from Jira/XRay
* Business Requirement documentation from Confluence
* Test Case titles or summaries
* Test Case steps
* Bugs or other supporting evidence

The MVP must not assume that all of these sources are available.

---

## 2. Evidence Consolidation

### FORGE

* Ingests the available evidence.
* Identifies the type and relevance of each source.
* Consolidates the available information.
* Identifies missing or potentially insufficient evidence.

---

## 3. Requirements Discovery

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

---

## 5. Traceability Analysis

### FORGE

* Analyses the relationship between Business Requirements and existing Test Cases.
* Uses available Test Case information, including titles, summaries and steps when available.
* Uses other available evidence when relevant.
* Identifies potential coverage gaps and inconsistencies.

### Intermediate Artefact

A Functional Specification may be synthesized when required to establish or clarify traceability.

It is not a required output of the MVP.

---

## 6. Coverage Analysis

### FORGE

* Determines which Business Requirements are covered by existing Test Cases.
* Identifies Business Requirements without identified test coverage.
* Identifies cases where the available evidence is insufficient to determine coverage.
* Calculates Business Requirement Coverage when the available evidence supports a reliable calculation.

---

## 7. Coverage Result

The MVP ends with a Coverage Result that provides the information defined in [First Valuable Outcome](#first-valuable-outcome).

---

## First Valuable Outcome

The first valuable outcome delivered by the MVP is a **Business Requirement Coverage result** showing:

* Business Requirements
* Matching Test Cases
* Coverage percentage, when reliably calculable
* Missing or unidentified validation
* Insufficient evidence
* Functional inconsistencies
* Remaining functional risk
