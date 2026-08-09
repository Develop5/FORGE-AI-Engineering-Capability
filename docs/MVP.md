# Alcance del MVP

## MVP Objective
Demonstrate that FORGE can calculate Business Requirement Coverage from dispersed documentation and existing Test Cases.


---

## MVP Evidence Sources

The MVP starts with existing Test Cases from Jira/XRay.

### Primary Input

**Jira/XRay Test Cases**

Test Cases may contain:

* Test Case title
* Test Case description or summary
* Test Case steps
* Other available Test Case metadata

The MVP must support Test Cases both with and without detailed steps.

### Optional Supporting Input

When the information available in the Jira Test Cases is insufficient, the user may provide additional contextual notes through a CSV file.

The CSV is supplementary evidence. It does not replace the Jira Test Cases.

### Explicitly Out of MVP

The MVP does not require the ingestion of:

* Confluence Business Requirement pages
* User Stories
* Bugs
* Use Cases
* Technical documentation
* Other Evidence Source combinations

These are future extensions of the FORGE capability.

---




