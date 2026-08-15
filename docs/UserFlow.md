# E2E User Flow

## End-to-End Flow

```text
Evidence
    ↓
Evidence Consolidation
    ↓
Requirements Discovery
    ↓
Findings
    ↓
Clarification (when required)
    ↓
Business Requirements + Risks
    ↓
Traceability Analysis
    ↓
Coverage Analysis
    ↓
Current Coverage
    ↓
Improvement of Requirement Coverage (when requested)
    ├── Test Case Planning
    └── Test Case Generation
    ↓
Generated Test Cases
    ↓
Projected Coverage
```

The flow is evidence-driven. Different executions may provide different combinations of Evidence Sources, and the available evidence may be incomplete or insufficient.

The Workflow Orchestrator controls the execution flow, maintains execution state, coordinates user interaction, and decides which capability executes next.

Capabilities produce domain results and do not control workflow progression.

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
* Notes and other relevant project documentation

Different executions may provide different combinations of these sources.

The absence of an Evidence Source must not be interpreted as evidence that the corresponding information does not exist.

---

## Evidence Consolidation

FORGE consolidates heterogeneous Evidence into structured information that can be analysed by Requirements Discovery.

### Input

* References to Evidence Sources
* Access to the required Evidence through external source adapters

### FORGE

* Determines what Evidence content is required.
* Obtains the required Evidence through the appropriate source mechanism.
* Extracts and normalizes relevant information.
* Groups relevant information into structured topics.
* Preserves references to the originating Evidence Sources.

### Output

* Structured topics
* Evidence information
* Evidence references

Evidence Consolidation does not identify Business Requirements as a final domain result.

---

## Requirements Discovery

Requirements Discovery analyses the consolidated Evidence to identify and structure Business Requirements and identify Findings affecting those Business Requirements.

### FORGE

* Identifies Business Requirements.
* Consolidates information belonging to the same Business Requirement.
* Preserves supporting Evidence references.
* Identifies relationships between Business Requirements.
* Identifies conflicts, ambiguities, unresolved dependencies, insufficient information, and other relevant problems.
* Creates a Finding for each identified problem.
* Associates each Finding with the Business Requirements affected by the problem.

### Output

* Business Requirements
* Findings
* Business Requirement relationships
* Supporting Evidence references

Requirements Discovery does not create Questions.

Questions are created by Clarification from Findings when clarification is required.

---

## Clarification

Clarification processes Findings and, where possible, resolves the problems identified in Business Requirements through interaction with the user.

### FORGE

* Receives Business Requirements and Findings from Requirements Discovery.
* Creates one Question for each Finding when clarification is required.
* Presents Questions through the Interface.
* Processes user responses.
* Determines whether Findings have been resolved.
* Updates affected Business Requirements when clarification provides sufficient information.
* Identifies new problems revealed by clarification.
* Creates new Findings for newly identified problems.
* Incorporates available answers into the subsequent analysis.

### Human

* Provides answers to Questions when appropriate.
* May decide not to answer a Question.
* May allow clarification to continue or stop.
* Remains responsible for final decisions regarding requirements and product behaviour.

### Result

When a Finding is resolved, its corresponding Question ceases to exist.

If an answer reveals a new problem, the new problem becomes a new Finding and may produce a new Question.

When clarification ends with an unresolved Finding, that Finding becomes a Risk.

Each Risk retains traceability to the affected Business Requirement, the unresolved problem, the unresolved Question when applicable, and the originating capability.

FORGE does not remain blocked waiting for user answers.

---

## Traceability Analysis

Traceability Analysis establishes relationships between Business Requirements and available Test Cases.

### Input

* Business Requirements produced by Clarification
* Associated Risks
* Existing Test Cases
* Supporting Evidence

### FORGE

* Analyses Test Cases using the available evidence, including titles, descriptions, steps, expected results, explicit references, and other available information.
* Determines whether a Test Case can be related to a Business Requirement.
* Establishes Business Requirement ↔ Test Case relationships when the correspondence is sufficiently clear.
* Identifies Business Requirements that have no related Test Case.
* Identifies Test Cases that cannot be related to any Business Requirement.
* Produces a Specification for such Test Cases when required.
* Preserves relevant Business Requirement information, references, and Risks.

A Business Requirement may be related to multiple Test Cases, and a Test Case may be related to multiple Business Requirements.

FORGE does not establish a relationship when the correspondence is uncertain.

### Specification

A Specification may be synthesized when required to structure or clarify the relationship between a Test Case and a Business Requirement.

A Specification is an intermediate analysis artefact.

It does not become a Business Requirement and does not itself demonstrate Business Requirement Coverage.

---

## Coverage Analysis

Coverage Analysis calculates Current Business Requirement Coverage from the established Business Requirement ↔ Test Case relationships.

### FORGE

* Determines which Business Requirements are covered.
* Determines which Business Requirements have no Test Case coverage.
* Calculates Current Coverage according to the definition in `Glossary.md`.
* Preserves Risks received from previous capabilities.
* Produces the Coverage Result.

A Business Requirement is covered when it has at least one related Test Case.

Risks do not participate in the Coverage calculation.

An uncovered Business Requirement is not automatically converted into a Risk.

Insufficient evidence does not create a separate Coverage category. It may instead be reported as an explanation or limitation affecting the reliability or interpretation of the analysis.

### Coverage Result

Coverage Analysis produces the Business Requirement Coverage Result.

The result may include:

* Business Requirements
* Matching Test Cases
* Current Coverage
* Business Requirements without Test Case coverage
* Relevant Evidence limitations
* Relevant inconsistencies
* Preserved Risks

---

## Improvement of Requirement Coverage

Improvement of Requirement Coverage is a single capability containing two internal responsibilities:

* Test Case Planning
* Test Case Generation

The user may request improvement of the current Business Requirement Coverage by specifying a target coverage level.

The target represents the minimum desired coverage.

The default target is **95%**.

FORGE attempts to achieve at least the requested target.

### Test Case Planning

#### Input

* Business Requirements
* Existing Test Cases
* Current Coverage
* Requested Coverage Target
* Relevant context and Risks

#### FORGE

* Identifies Business Requirements requiring additional Test Case coverage.
* Estimates the Test Cases required to reach the requested target.
* Produces a Test Case Generation plan.

Planning does not determine whether the planned Test Cases are technically generable from the available information.

### Test Case Generation

#### Input

* Test Case Generation plan
* Business Requirements
* Existing Test Cases
* Approved Specifications
* Relevant context and references

#### FORGE

* Uses Business Requirements as the primary generation context.
* Uses approved Specifications as additional context when appropriate.
* Ignores unapproved Specifications.
* Detects conflicts involving Specifications.
* Reports conflicting Specifications without generating Test Cases based on them.
* Determines whether sufficient information exists to generate each planned Test Case.
* Generates Test Cases when sufficient information exists.
* Reports planned Test Cases that could not be generated and the reason.
* Establishes relationships between generated Test Cases and their target Business Requirements.

### Output

* Generated Test Cases
* Non-generated planned Test Cases and reasons
* Business Requirement ↔ generated Test Case relationships
* Generation results

Generated Test Cases are proposals for improving coverage.

Generation does not execute or validate the generated Test Cases.

---

## Projected Coverage

Projected Coverage represents the coverage that would result if the generated Test Cases were added to the existing Test Cases.

Projected Coverage uses the same coverage rule as Current Coverage.

Generated Test Cases have not necessarily been executed or validated.

Therefore, Projected Coverage must not be presented as evidence of executed Test Coverage.

If the requested target cannot be achieved, FORGE explicitly reports that the target was not achieved and provides the relevant causes.

---

## Workflow and User Interaction

The Workflow Orchestrator coordinates the complete execution.

The general execution pattern is:

```text
Interface
    ↓
Workflow Orchestrator
    ↓
Domain Capability
    ↓
Domain Result
    ↓
Workflow Orchestrator
    ↓
Next Capability or User Interaction
    ↓
...
```

The Interface:

* Collects user inputs and references to Evidence Sources.
* Presents Questions generated by Clarification.
* Collects user responses and decisions.
* Presents structured FORGE results.

The Workflow Orchestrator:

* Maintains execution state.
* Invokes capabilities with the required domain inputs.
* Processes capability results at workflow level.
* Decides which capability executes next.
* Controls workflow transitions and iterations.
* Manages states in which user input is required.
* Resumes execution after user input is received.

Capabilities:

* Receive domain inputs.
* Perform their defined domain responsibility.
* Produce structured domain results.
* Return control to the Workflow Orchestrator.

Capabilities do not invoke other capabilities directly and do not remain active while waiting for user input.

---

## MVP User Flow

The following six capabilities are part of the MVP:

* Evidence Consolidation
* Requirements Discovery
* Clarification
* Traceability Analysis
* Coverage Analysis
* Improvement of Requirement Coverage

The MVP also includes the internal responsibilities and domain results required to demonstrate the end-to-end flow, including:

* Test Case Planning
* Test Case Generation
* Current Coverage
* Risks affecting the analysis or its results
* Projected Coverage

The primary domain outcome of the MVP is the **Coverage Result** produced by Coverage Analysis.

The MVP therefore demonstrates both the analysis of existing Business Requirement Coverage and the ability to request improvement of that coverage through additional generated Test Cases.

---

## First Valuable Outcome

The first valuable outcome delivered by the MVP is the **Business Requirement Coverage Result**.

The result provides the user with:

* Business Requirements identified from the available Evidence
* Matching Test Cases
* Current Coverage
* Business Requirements without Test Case coverage
* Relevant Evidence limitations
* Relevant inconsistencies
* Risks affecting the analysis or its results

The Coverage Result is a domain result. Its presentation or reporting is a separate concern handled by the Interface.
