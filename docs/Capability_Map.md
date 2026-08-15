
# Capability Map

This document defines the functional capabilities of FORGE.

Capabilities represent stable units of domain responsibility. They produce domain results and do not control workflow progression.

The Workflow Orchestrator coordinates the capabilities, maintains execution state, validates inputs before invoking capabilities, handles user interaction through the Interface, and decides what happens next.

---

## Capability Overview

FORGE is composed of the following six capabilities:

1. Evidence Consolidation
2. Requirements Discovery
3. Clarification
4. Traceability Analysis
5. Coverage Analysis
6. Improvement of Requirement Coverage

The capabilities form a logical processing flow, but they are not required to execute as a rigid linear pipeline.

The Workflow Orchestrator determines which capability executes next according to the current state and the result produced by the previous capability.

---

## 1. Evidence Consolidation

### Purpose

Consolidate heterogeneous project Evidence into structured information that can be analysed by Requirements Discovery.

### Inputs

* References to Evidence Sources
* Access to the required Evidence through external source adapters

### Responsibilities

* Determine what Evidence content is required.
* Obtain the required Evidence through the appropriate source mechanism.
* Extract and normalize relevant information.
* Group relevant information into structured topics.
* Preserve references to the originating Evidence Sources.

### Outputs

* Structured topics
* Evidence information
* Evidence references

### Does Not

* Identify Business Requirements as a final domain result.
* Create Findings.
* Create Questions.
* Establish Business Requirement ↔ Test Case traceability.
* Calculate Coverage.
* Generate Test Cases.

---

## 2. Requirements Discovery

### Purpose

Identify and structure Business Requirements from consolidated Evidence and identify Findings affecting those Business Requirements.

### Inputs

* Structured topics from Evidence Consolidation
* Evidence
* Evidence references

### Responsibilities

* Identify Business Requirements.
* Consolidate information belonging to the same Business Requirement.
* Preserve supporting Evidence references.
* Identify relationships between Business Requirements.
* Identify conflicts, ambiguities, unresolved dependencies, and other problems.
* Create Findings.
* Associate Findings with affected Business Requirements.

### Outputs

* Business Requirements
* Findings
* Business Requirement relationships
* Supporting Evidence references

### Does Not

* Create Questions.
* Interact directly with users.
* Resolve Findings through user interaction.
* Calculate Coverage.
* Generate Test Cases.

---

## 3. Clarification

### Purpose

Resolve Findings through interaction with the user.

### Inputs

* Business Requirements
* Findings
* Supporting Evidence references
* User responses

### Responsibilities

* Create Questions from Findings.
* Present Questions through the Interface.
* Process user responses.
* Determine whether Findings have been resolved.
* Update Business Requirements when clarification provides sufficient information.
* Identify new problems revealed by clarification.
* Create new Findings for newly identified problems.
* Convert unresolved Findings into Risks when Clarification ends with those Findings unresolved.

### Outputs

* Updated Business Requirements
* Resolved Findings
* Risks resulting from unresolved Findings
* User interaction results
* New Findings, when applicable

### Does Not

* Decide workflow progression.
* Interact with external source systems directly.
* Calculate Coverage.
* Generate Test Cases.

---

## 4. Traceability Analysis

### Purpose

Establish relationships between Business Requirements and available Test Cases.

### Inputs

* Business Requirements
* Test Cases
* Supporting Evidence
* Risks

### Responsibilities

* Analyze Test Cases.
* Determine sufficiently supported Business Requirement ↔ Test Case relationships.
* Establish traceability relationships.
* Identify uncovered Business Requirements.
* Identify Test Cases that cannot be related to a Business Requirement.
* Produce Specifications when required for those Test Cases.
* Preserve relevant references and Risks.

### Outputs

* Business Requirement ↔ Test Case relationships
* Uncovered Business Requirements
* Specifications, when applicable
* Test Case analysis results

### Does Not

* Convert uncovered Business Requirements into Risks automatically.
* Calculate Coverage.
* Generate Test Cases.
* Decide workflow progression.

---

## 5. Coverage Analysis

### Purpose

Calculate Business Requirement Coverage from established Business Requirement ↔ Test Case relationships.

### Inputs

* Business Requirements
* Business Requirement ↔ Test Case relationships
* Existing Test Cases
* Risks

### Responsibilities

* Determine which Business Requirements are covered.
* Determine which Business Requirements are uncovered.
* Calculate Current Coverage.
* Preserve received Risks.
* Produce the Coverage Result.

### Outputs

* Current Coverage
* Covered Business Requirements
* Uncovered Business Requirements
* Coverage Result
* Preserved Risks

### Does Not

* Use Risks in the Coverage calculation.
* Convert uncovered Business Requirements into Risks.
* Generate Test Cases.
* Decide workflow progression.

---

## 6. Improvement of Requirement Coverage

### Purpose

Improve Business Requirement Coverage by planning and generating additional Test Cases for uncovered Business Requirements.

Improvement of Requirement Coverage is a single capability containing two internal responsibilities:

* Test Case Planning
* Test Case Generation

---

### 6.1 Test Case Planning

#### Inputs

* Business Requirements
* Existing Test Cases
* Current Coverage
* Requested Coverage Target
* Relevant context and Risks

#### Responsibilities

* Identify Business Requirements requiring additional Test Case coverage.
* Estimate the Test Cases required to reach the requested target.
* Produce a Test Case Generation plan.

#### Outputs

* Test Case Generation plan
* Target Business Requirements
* Estimated Test Case requirements

#### Does Not

* Determine whether planned Test Cases are technically generable.
* Generate Test Cases.
* Calculate final Projected Coverage.

---

### 6.2 Test Case Generation

#### Inputs

* Test Case Generation plan
* Business Requirements
* Existing Test Cases
* Approved Specifications
* Relevant context and references

#### Responsibilities

* Use Business Requirements as the primary generation context.
* Use approved Specifications as additional context.
* Ignore unapproved Specifications.
* Detect conflicts involving Specifications.
* Report conflicting Specifications without generating Test Cases based on them.
* Determine whether sufficient information exists to generate each planned Test Case.
* Generate Test Cases when sufficient information exists.
* Report planned Test Cases that could not be generated and the reason.
* Establish relationships between generated Test Cases and their target Business Requirements.

#### Outputs

* Generated Test Cases
* Non-generated planned Test Cases and reasons
* Business Requirement ↔ generated Test Case relationships
* Generation results

#### Does Not

* Execute Test Cases.
* Validate generated Test Cases through execution.
* Write generated Test Cases to external systems as part of the MVP.
* Decide workflow progression.
* Produce the Coverage Result.

---

# Capability Interaction

The typical logical flow is:

```text
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

This flow is coordinated by the Workflow Orchestrator.

Capabilities do not invoke other capabilities directly.

The Workflow Orchestrator decides which capability to invoke next based on the current execution state and the result returned by the previous capability.

---

# Cross-Capability Concepts

The following concepts may be produced by one capability and consumed by another:

| Concept                     | Produced by                                                                             | May be consumed by                                                                           |
| --------------------------- | --------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------- |
| Evidence                    | Evidence Consolidation                                                                  | Requirements Discovery, Traceability Analysis, Clarification                                 |
| Business Requirement        | Requirements Discovery                                                                  | Clarification, Traceability Analysis, Coverage Analysis, Improvement of Requirement Coverage |
| Finding                     | Requirements Discovery, Clarification                                                   | Clarification                                                                                |
| Question                    | Clarification                                                                           | Interface / user interaction                                                                 |
| Risk                        | Clarification and other capabilities when an explicitly identified issue becomes a Risk | Subsequent capabilities and reporting                                                        |
| Test Case                   | External sources / Traceability Analysis context                                        | Traceability Analysis, Coverage Analysis, Improvement of Requirement Coverage                |
| Specification               | Traceability Analysis                                                                   | Improvement of Requirement Coverage                                                          |
| BR ↔ Test Case relationship | Traceability Analysis                                                                   | Coverage Analysis, Improvement of Requirement Coverage                                       |
| Current Coverage            | Coverage Analysis                                                                       | Improvement of Requirement Coverage, reporting                                               |
| Generated Test Case         | Improvement of Requirement Coverage                                                     | Projected Coverage calculation / reporting                                                   |
| Projected Coverage          | Derived from existing + generated Test Cases                                            | Reporting                                                                                    |

---

# Capability Boundary Principles

The following principles apply to all FORGE capabilities:

1. Capabilities produce domain results.
2. Capabilities do not control workflow progression.
3. Capabilities do not interact directly with users.
4. Capabilities do not depend directly on concrete external source SDKs or APIs.
5. Capabilities do not depend directly on a specific LLM provider or model.
6. Capabilities do not maintain long-lived execution state.
7. Capabilities receive validated inputs from the Workflow Orchestrator.
8. Capabilities return structured domain results.
9. Technical errors are returned to the Workflow Orchestrator, which decides how to handle them.
10. Cross-capability transitions are controlled by the Workflow Orchestrator.

````

### Una precisión importante

He usado **seis capabilities**, pero no he convertido `Test Case Planning` y `Test Case Generation` en capabilities independientes. Son **responsabilidades internas de `Improvement of Requirement Coverage`**, exactamente como decidimos.

Eso mantiene la estructura:

```text
6 capabilities
    ↓
Improvement of Requirement Coverage
    ├── Planning
    └── Generation
````

