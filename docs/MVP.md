# MVP Scope

## MVP Objective

Demonstrate that FORGE can calculate **Business Requirement Coverage against existing Test Cases from Jira/XRay**, using available supplementary evidence when provided, and expose the functional risk that remains based on the available evidence.

The MVP is intentionally focused on existing testing evidence. It does not require FORGE to generate new Test Cases.

---

## MVP Evidence Sources

The MVP starts with **existing Test Cases from Jira/XRay as the primary Evidence Source**.

The available evidence may be incomplete. Test Cases may contain detailed steps or may provide only a title, summary, or other available information.

### Primary Input

**Jira/XRay Test Cases**

Test Cases may contain:

* Test Case title
* Test Case description or summary
* Test Case steps
* Other available Test Case metadata

The MVP must support Test Cases both with and without detailed steps.

### Optional Supporting Evidence

#### Jira Bugs

Jira Bugs may be provided as supplementary evidence.

They may provide additional information about:

* implemented or observed behaviour;
* functional areas;
* known defects;
* relationships between functionality and existing tests.

Jira Bugs are optional and do not replace the Jira/XRay Test Cases.

#### CSV Notes

The user may provide additional contextual notes through a CSV file when the available Jira evidence is insufficient.

The CSV is supplementary evidence and does not replace the Jira/XRay Test Cases.

---

## Requirements Discovery

FORGE analyses the available Jira/XRay Test Cases and any optional supporting evidence to identify and consolidate Business Requirements relevant to the coverage analysis.

Requirements Discovery may identify:

* Business Requirements
* Conflicts
* Unresolved Dependencies
* Clarification Questions

FORGE does not create or approve Business Requirements.

---

## Coverage Analysis

FORGE establishes traceability between the Business Requirements identified through Requirements Discovery and the existing Test Cases.

The MVP calculates **Business Requirement Coverage** using the existing Test Cases.

The coverage definition and formula are maintained in `Glossary.md`.

FORGE must distinguish between:

* Business Requirements with identified Test Case coverage;
* Business Requirements without identified Test Case coverage;
* Business Requirements for which the available evidence is insufficient to determine coverage.

---

## MVP Outcome

The primary outcome of the MVP is a **Business Requirement Coverage result**.

The result provides:

* Business Requirements identified;
* Matching Test Cases;
* Coverage percentage, when reliably calculable;
* Business Requirements without identified Test Case coverage;
* Insufficient or missing evidence;
* Relevant functional inconsistencies;
* Remaining functional risk.

---

## Explicitly Out of MVP

The MVP does not require the ingestion of:

* Confluence Business Requirement pages
* User Stories
* Use Cases
* Technical documentation
* Other Evidence Source combinations

These are future extensions of the FORGE capability.

The MVP does not include:

* Test Case Generation
* Authentication
* User Management
* Real-time Collaboration
* Comments
* Approval Workflows
* Project Management
* Advanced Editing
* Multiple Export Formats
