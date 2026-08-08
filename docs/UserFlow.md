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


## MVP User Flow
Input — qué introduce el usuario: Jira/XRay + Confluence

Step 1 — Analiza las fuentes proporcionadas y realiza
Requirements Discovery:
- Business Requirements identificados
- conflictos
- dependencias no resueltas
- preguntas de clarificación

Step 2 — qué genera FORGE: specifications to be analyzed accepted or rejected by a human

Final output — qué artefactos vemos en pantalla: a table is shown with every business requirement and its accepted specifications, each of which is linked to 1 or 2 test cases

Definition of Done — cuándo podemos decir "la demo funciona": the value of calculated coverage is shown

