# FORGE Responsibility Map

This document defines the functional responsibilities of FORGE.

It describes what FORGE is responsible for doing. It does not define how those responsibilities are implemented, orchestrated, deployed, or exposed through a user interface.

The MVP is a subset of the capabilities and responsibilities defined here.

---

# 1. Evidence Consolidation

## Responsibility

Evidence Consolidation consolidates heterogeneous evidence into structured topics that can be analyzed by Requirements Discovery.

## Responsibilities

1. Receive references to external evidence sources.

2. Obtain the content required for analysis through the appropriate external source mechanism.

3. Extract and normalize relevant information from the available evidence.

4. Group evidence that belongs to the same topic or context without assuming that a topic represents a Business Requirement.

5. Preserve the reference associated with each piece of evidence.

6. Produce structured topics containing:
   - topic name;
   - information found;
   - references to the originating evidence.

7. Provide the consolidated topics to Requirements Discovery.

## Boundary

Evidence Consolidation determines what evidence content is required for its analysis, but it does not directly depend on concrete external systems such as Jira or Confluence. External access is provided through the corresponding source abstraction or adapter.

---

# 2. Requirements Discovery

## Responsibility

Requirements Discovery identifies and structures Business Requirement candidates from the consolidated evidence and identifies problems that require clarification.

## Responsibilities

1. Receive consolidated topics and their references from Evidence Consolidation.

2. Analyze the available evidence to identify Business Requirement candidates.

3. Relate evidence semantically to determine which evidence contributes to the same Business Requirement candidate.

4. Decompose a topic or grouping when it contains multiple distinct needs that should become separate Business Requirement candidates.

5. Identify duplicate Business Requirement candidates.

6. Merge duplicate candidates into a single candidate while preserving all references to the original evidence and candidates.

7. Identify relevant relationships between Business Requirements, including:
   - hierarchy;
   - aggregation;
   - duplication.

8. Identify problems affecting Business Requirement candidates, including ambiguities, conflicts, insufficient information, and other relevant inconsistencies.

9. Create a Finding for each identified problem.

10. Associate each Finding with all Business Requirements affected by the problem.

11. Preserve the relevant information of each Business Requirement candidate, including:
    - identifier;
    - title;
    - priority, when available;
    - acceptance criteria, when available;
    - dependencies;
    - references to supporting evidence.

12. Preserve Business Requirement candidates even when their information is incomplete or ambiguous.

13. Provide the resulting Business Requirement candidates, Findings, and references to Clarification.

## LLM Use

Requirements Discovery may use LLM capabilities to support semantic analysis and relationship identification. The capability remains independent of any specific LLM provider or model.

---

# 3. Clarification

## Responsibility

Clarification resolves, where possible, problems identified in Business Requirements through interaction with the user.

## Responsibilities

1. Receive Business Requirements, their references, and the Findings associated with them.

2. Analyze the received Findings according to the definitions established in the FORGE domain.

3. Generate one or more Questions for each Finding when clarification is required.

4. Treat each Question as the user-facing representation of the Finding it addresses.

5. Associate each Question with the Finding that originated it.

6. Receive user responses to the generated Questions.

7. Analyze each response together with the affected Business Requirement and Finding.

8. Determine whether the information provided resolves the Finding.

9. Update the affected Business Requirement when the response provides sufficient information.

10. Reanalyze the affected Business Requirement when new information may reveal additional problems.

11. Generate new Findings and Questions when the new information reveals new problems.

12. When the user leaves a Finding unresolved and clarification ends, convert that unresolved Finding into a Risk.

13. Ensure that each Risk retains traceability to:
    - the affected Business Requirement;
    - the unresolved problem;
    - the unanswered or unresolved Question, when applicable.

## Clarification Lifecycle

A Question exists only while it represents the current Finding being clarified.

When a response resolves the Finding, the Question is no longer required.

When a response reveals a different problem, the original Question is no longer the active question. The newly identified problem becomes a new Finding and may generate a new Question.

Clarification does not decide unilaterally when the workflow ends. The decision to continue or stop clarification belongs to the user and is coordinated by the Workflow Orchestrator.

---

# 4. Traceability Analysis

## Responsibility

Traceability Analysis determines the relationships between Business Requirements and available Test Cases.

## Responsibilities

1. Receive all Business Requirements produced by Clarification, together with their references and Risks.

2. Receive the available Test Cases.

3. Analyze the available evidence associated with each Test Case according to its type and completeness.

4. Determine whether a Test Case can be related to a Business Requirement using the available evidence, including when applicable:
   - title;
   - description;
   - steps;
   - expected result;
   - explicit references;
   - other available evidence.

5. Establish all Business Requirement ↔ Test Case relationships that are sufficiently clear.

6. Allow Business Requirements and Test Cases to have N:N relationships.

7. Do not establish a relationship when the correspondence is uncertain.

8. Identify Business Requirements that have no related Test Case.

9. Register a Risk for each Business Requirement without Test Case coverage.

   The Risk contains:
   - affected Business Requirement;
   - problem: `No business requirement` is not applicable here; the problem is the absence of Test Case coverage;
   - unanswered question: `no question generated`.

10. Preserve all Business Requirement information received from Clarification, including references and Risks.

11. Identify Test Cases that cannot be related to any Business Requirement.

12. Create Functional Specifications for such Test Cases when required.

13. Ensure that each generated Specification complies with the definition established in `Glossary.md`.

14. Produce the Business Requirement ↔ Test Case traceability relationships.

15. Produce, when applicable, the list of generated Specifications and the Test Cases associated with each Specification.

## Coverage Rule

A Business Requirement is considered covered when it has at least one related Test Case.

## Specification Boundary

A Specification created for a Test Case that cannot be related to a Business Requirement is an additional analysis output.

It does not become a Business Requirement and does not modify the Business Requirement set used for Coverage Analysis.

---

# 5. Coverage Analysis

## Responsibility

Coverage Analysis calculates Business Requirement Coverage according to the definition established in `Glossary.md`.

## Responsibilities

1. Receive:
   - all Business Requirements;
   - all Business Requirement ↔ Test Case relationships;
   - associated Risks.

2. Determine which Business Requirements are covered by at least one Test Case.

3. Determine which Business Requirements have no Test Case coverage.

4. Calculate the Business Requirement Coverage percentage.

5. Produce the Coverage Result.

6. Preserve the Risks received from previous capabilities for inclusion in subsequent results and reporting.

## Coverage Rule

A Business Requirement is covered when it has at least one related Test Case.

## Risk Boundary

Risks received by Coverage Analysis do not participate in the Coverage calculation.

They are preserved so that subsequent reporting can explain relevant weaknesses or unresolved issues affecting the analysis.

---

# 6. Improvement of Requirement Coverage

## Responsibility

Improvement of Requirement Coverage improves the Business Requirement Coverage by estimating and, when possible, generating additional Test Cases for Business Requirements that currently have no Test Case coverage.

Improvement of Requirement Coverage is a single FORGE capability containing two internal responsibilities:

- Test Case Planning;
- Test Case Generation.

## Inputs

The capability receives:

- all Business Requirements;
- existing Test Cases;
- Specifications;
- Risks;
- references associated with the Business Requirements and available information.

## 6.1 Test Case Planning

Test Case Planning estimates the Test Cases required to reach the requested coverage target.

### Responsibilities

1. Determine the target Business Requirement Coverage.

2. Use the user-provided target when one is supplied.

3. Use the FORGE default target when no target is supplied.

4. Identify Business Requirements that currently have no Test Case coverage.

5. Estimate how many additional Test Cases are required to reach the target.

6. Identify which Business Requirements the estimated Test Cases would need to cover.

7. Produce the Test Case Generation plan.

8. Do not determine yet whether the planned Test Cases can technically or functionally be generated from the available information.

## 6.2 Test Case Generation

Test Case Generation attempts to generate the Test Cases identified by the plan.

### Responsibilities

1. Receive the Business Requirements identified by the plan.

2. Use the available Business Requirement information as the primary context for generation.

3. Use approved Specifications as additional context when attempting to generate a Test Case.

4. Ignore unapproved Specifications when generating Test Cases.

5. Detect when a Specification conflicts with the Business Requirement or available information.

6. Report such a conflict and do not generate a Test Case based on the conflicting Specification.

7. Determine whether sufficient information exists to generate each planned Test Case.

8. Generate Test Cases when sufficient information is available.

9. When a Test Case cannot be generated because information is insufficient, report the planned Test Case and the reason it was not generated.

10. Each generated Test Case contains:
    - identifier;
    - Business Requirement it intends to cover;
    - preconditions;
    - steps;
    - expected result.

11. Establish the relationship between each generated Test Case and its corresponding Business Requirement.

12. Continue generation until:
    - the target is reached;
    - the user interrupts the generation;
    - or there is insufficient information to generate additional planned Test Cases.

## Projected Coverage

Projected Coverage is calculated using:

- all Business Requirements;
- all existing Test Cases;
- all generated Test Cases.

The same coverage rule applies as in Coverage Analysis:

> A Business Requirement is covered when it has at least one related Test Case.

## Output

Improvement of Requirement Coverage produces a Coverage Result containing:

- generated Test Cases;
- Business Requirements covered by the generated Test Cases;
- Projected Coverage percentage;
- Business Requirements covered;
- Business Requirements not covered;
- the requested target;
- whether the target was reached;
- the reason why generation ended;
- causes explaining why the target was not reached, when applicable.

Generated Test Cases are not considered executed or validated merely because FORGE generated them.

---

# Responsibility Boundaries

The following responsibilities are intentionally outside the capabilities defined in this document.

## Workflow Orchestration

The Workflow Orchestrator is responsible for:

- maintaining execution state;
- validating capability inputs;
- invoking capabilities;
- deciding which capability executes next;
- controlling workflow transitions and iterations;
- coordinating user interaction;
- deciding how technical errors affect execution.

Capabilities produce domain results and do not control workflow progression.

## User Interaction

The Interface is responsible for:

- collecting user input;
- presenting Questions;
- collecting user responses and decisions;
- presenting structured FORGE results.

Capabilities do not interact directly with users.

## External Source Access

Capabilities determine what information they require but do not directly depend on concrete external systems.

External Source Adapters provide access to systems such as Jira, Confluence, files, or future evidence sources.

## LLM Providers

Capabilities may use LLM functionality through an LLM abstraction.

Capabilities and the Workflow Orchestrator do not depend directly on a specific LLM provider or model.

## Infrastructure

Credentials, persistence mechanisms, provider configuration, deployment mechanisms, and other infrastructure concerns are outside FORGE domain capabilities.