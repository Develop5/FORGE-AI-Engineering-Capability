# FORGE Capability Map

## FORGE

FORGE is an AI Engineering capability for analysing Business Requirement coverage and improving insufficient coverage through additional Test Cases.

## Domain Capabilities

FORGE is composed of the following domain capabilities:

1. Evidence Consolidation
2. Requirements Discovery
3. Clarification
4. Traceability Analysis
5. Coverage Analysis
6. Improvement of Requirement Coverage

## Capability Responsibilities

### Evidence Consolidation

Consolidates heterogeneous evidence into structured topics that can be analysed by Requirements Discovery.

### Requirements Discovery

Identifies and structures Business Requirement candidates from consolidated evidence and identifies problems that require clarification.

### Clarification

Resolves, where possible, problems identified in Business Requirements through user clarification.

### Traceability Analysis

Establishes relationships between Business Requirements and available Test Cases and produces additional Specifications when required by the analysis.

### Coverage Analysis

Calculates Business Requirement Coverage from Business Requirements and established Test Case relationships and produces the Coverage Result.

### Improvement of Requirement Coverage

Improves Business Requirement Coverage by planning and, when possible, generating additional Test Cases for Business Requirements that currently have no Test Case coverage.

Improvement of Requirement Coverage contains two internal responsibilities:

- Test Case Planning
- Test Case Generation

## Architectural Boundary

The capabilities above represent FORGE domain responsibilities.

The following are not domain capabilities:

- Workflow Orchestration
- User Interface
- External Source Adapters
- LLM Providers
- Persistence and other Infrastructure

The Workflow Orchestrator coordinates capability execution and maintains workflow state. It does not perform the domain responsibilities of the capabilities.

Interfaces present structured domain results and collect user input but do not own domain decisions.