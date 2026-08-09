# Documentation Guide

This document defines the ownership and scope of the documentation maintained in the FORGE repository.

Each topic has a single authoritative document. Information should not be duplicated across documents unless a short summary or reference is necessary for context.

| Document                      | Source of truth for                                                                                   | Must not contain                                                                                            |
| ----------------------------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- |
| `PROJECT_BRIEF.md`            | High-level project context, purpose, current objective, and entry point to the documentation          | Detailed product definitions, implementation details, or duplicated documentation                           |
| `docs/Vision.md`              | Product vision, problem statement, long-term purpose, and strategic intent                            | Detailed MVP scope, implementation decisions, backlog items, or technical design                            |
| `docs/Product.md`             | Product definition, target users, product goal, principles, value proposition, and product boundaries | Technical implementation, detailed MVP tasks, architecture, or prompt design                                |
| `docs/MVP.md`                 | MVP objective, scope, included and excluded capabilities, evidence sources, and MVP constraints       | Long-term product vision, implementation details, or detailed task planning                                 |
| `docs/UserFlow.md`            | End-to-end user flow and expected user outcomes                                                       | Technical architecture, implementation tasks, or detailed demo narration                                    |
| `docs/Capability_Map.md`      | FORGE capabilities and the relationships between capabilities                                         | Product decisions, implementation details, or backlog items                                                 |
| `docs/Demo_Script.md`         | Demo narrative, sequence, scenarios, key messages, and expected demonstration outcome                 | Product strategy, architecture, or implementation tasks                                                     |
| `docs/Backlog.md`             | Planned work, priorities, implementation tasks, and outstanding items                                 | Product definitions, architectural decisions, or detailed rationale for strategic decisions                 |
| `docs/Decisions.md`           | Cross-cutting product and project decisions, including decision rationale and consequences            | Full definitions owned by other documents or detailed implementation specifications                         |
| `docs/Architecture.md`        | System architecture, components, integrations, data flows, and technical design                       | Product strategy, business requirements, or prompt-specific design                                          |
| `docs/Prompting.md`           | Prompting strategy, prompt patterns, instructions, evaluation approach, and prompt-related design     | Product definitions, general architecture, or business decisions                                            |
| `docs/ADR-*.md`               | Individual architectural decisions, their context, alternatives, and consequences                     | Product decisions, general architecture documentation, or implementation task lists                         |
| `docs/Glossary.md`            | Authoritative definitions of project-specific terminology                                             | New product decisions, architectural decisions, or extensive explanations better suited to another document |
| `docs/Documentation_Guide.md` | Documentation ownership, scope, hierarchy, and rules for maintaining project documentation            | Product, technical, or implementation content                                                               |

---

## Repository Access Rule

When the user asks to review, verify, or inspect a document stored in the FORGE GitHub repository, the current version in the repository is the source of truth.

The assistant must:

1. Read the document directly from the current `main` branch.
2. Prefer the raw GitHub URL using the `refs/heads/main` path.
3. Not rely on previously retrieved, cached, quoted, or remembered content of the document.
4. Treat the content retrieved from the repository as authoritative over any version previously discussed in the conversation.
5. When the user explicitly provides a repository or raw-file URL, use that exact URL as the source.
6. If the current repository version cannot be retrieved or verified, explicitly state this instead of reconstructing the document from previous conversation content.

This rule applies to all project documentation, including but not limited to:

* Product.md
* MVP.md
* UserFlow.md
* Architecture.md
* Decisions.md
* Documentation_Guide.md
* Prompting.md
* Backlog.md
* Capability_Map.md
* Vision.md
* Demo_Script.md

---

### Content Verification

When the user asks a factual question about the current contents of a repository document, the assistant must inspect the current repository version before answering.

Questions about exact text, headings, lines, table entries, counts, or the presence or absence of specific terms must never be answered from conversation history or previously retrieved versions.

If the retrieved repository content differs from content previously discussed, the repository version takes precedence.

---

