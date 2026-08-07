# FORGE – Project Brief

## Purpose

FORGE is an AI Engineering Capability designed to determine and justify Business Requirement Coverage from heterogeneous and incomplete evidence.

Its purpose is not to replace existing engineering roles, but to support Requirements Discovery and provide reliable traceability between Business Requirements and validation assets.

---

## Problem Statement

Business Requirements are frequently distributed across multiple repositories and formats:

- Confluence pages
- Existing Test Cases
- Jira issues
- Notes
- Use Cases
- Technical documentation

This information is often incomplete, inconsistent or outdated.
As a consequence, Product Managers and Quality teams cannot confidently answer:
> Which business functionality is actually covered by existing tests?
or
> What functional risk remains before a release?

---

## Vision

FORGE enables organizations to calculate Business Requirement Coverage even when information is incomplete and dispersed across multiple evidence sources.

---

## Primary User

Product Manager
The capability is also valuable for QA Analysts, QA Managers, Business Analysts and Technical Leads, but the MVP is designed from the Product Manager perspective.

---

## MVP Goal

Demonstrate that FORGE can:
1. consolidate heterogeneous evidence,
2. perform AI-assisted Requirements Discovery,
3. identify inconsistencies,
4. calculate Business Requirement Coverage,
5. expose remaining functional risk.

---

## Main Capability

FORGE does not create Business Requirements.

Instead, it:
- consolidates evidence,
- performs Requirements Discovery,
- generates traceable specifications,
- analyses coverage,
- identifies missing validation,
- highlights functional risk.

---

## End-to-End Flow

Evidence Sources
↓
Requirements Discovery
↓
Clarification
↓
Specification Synthesis
↓
Validation
↓
Coverage Analysis
↓
Coverage Report

---

## Primary Deliverable

Coverage Report

The report shows:
- Business Requirements
- Matching Test Cases
- Coverage
- Missing validation
- Functional inconsistencies
- Remaining implementation risk

---

## Repository Structure

High-level project information is available in this document.
Detailed documentation is maintained under `/docs`.
Each topic has a single authoritative document to avoid duplication.

---

## Current Status

Current objective:
Deliver an end-to-end functional MVP demonstrating Business Requirement Coverage in less than one week.