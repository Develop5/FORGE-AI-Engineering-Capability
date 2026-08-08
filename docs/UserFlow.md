# Flujo E2E

## End-to-End Flow
Evidence Sources

↓

Requirements Discovery

↓

[Requirements + Conflicts + Unresolved Dependencies
 + Clarification Questions]

↓

Clarification

↓

Specification Synthesis

↓

Human checkpoint

↓

Accepted Specification

↓

Coverage Analysis

↓

Coverage Report


### Human Checkpoint — Specification Validation

FORGE presents the generated specifications to the user.

The user reviews each specification and can:
- Accept it
- Reject it

Only accepted specifications proceed to Coverage Analysis.

Rejected specifications do not contribute to the coverage calculation.

### Clarification
- Questions are asked to stakeholders in a human interview
- Answer to questions are introduced to FORGE by the user
- If answers are not provided, FORGE will ask whether continue or not
- Answers modify Business Requirements
- The flow continues after this step. Pending questions will be listed.
  

## First Valuable Outcome
The first valuable outcome delivered by the MVP is a Coverage Report showing:

• Business Requirements

• Matching Test Cases

• Coverage 

• Missing validation

• Functional inconsistencies

• Remaining implementation risk


### MVP User Flow


#### 1. Evidence Sources
Input:
- XRay tests and bugs from Jira
- 1–2 Confluence pages containing business requirements

#### 2. Requirements Discovery
FORGE:
- Analyze the input and provides a list of requirements
Output:
- Requirements
- Conflicts
- Unresolved Dependencies
- Clarification Questions

#### 3. Clarification
FORGE:
- Detects: ambiguities, conflicts and circular dependencies
User:
- Ask questions for a human to clarify: Ambiguities, conflicts and circular dependencies. If not all questions are clarified, the flow continues.

#### 4. Specification Synthesis
FORGE:
- Generates specifications 
Output:
- List of specifications to analyzed by humans

#### 5. Validation
Human:
- Accept / reject specifications

#### 6. Coverage Analysis
FORGE:
- Calculates the coverage

#### 7. Coverage Report
Displays:
- As defined in "First Value Outcome"


