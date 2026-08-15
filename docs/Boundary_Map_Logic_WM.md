┌─────────────────────────┐
│ Interface / User / Agent│
└────────────┬────────────┘
             │
             ▼
┌─────────────────────────┐
│ Workflow Orchestrator   │
└────────────┬────────────┘
             │
             ▼
┌─────────────────────────┐
│ Evidence Consolidation  │
└────────────┬────────────┘
             │ Topics
             ▼
┌─────────────────────────┐
│ Requirements Discovery  │
└────────────┬────────────┘
             │ BRs + Findings
             ▼
┌─────────────────────────┐
│ Clarification           │
└────────────┬────────────┘
             │ Final BRs + Risks
             ▼
┌─────────────────────────┐
│ Traceability Analysis   │
└────────────┬────────────┘
             │ BR ↔ Tests
             ▼
┌─────────────────────────┐
│ Coverage Analysis       │
└────────────┬────────────┘
             │ Current Coverage
             ▼
┌─────────────────────────┐
│ Coverage Improvement    │
│ Planning                │
└────────────┬────────────┘
             │ Plan
             ▼
┌─────────────────────────┐
│ Test Case Generation    │
└────────────┬────────────┘
             │ Generated TCs
             ▼
┌─────────────────────────┐
│ Projected Coverage      │
│ calculation             │
└────────────┬────────────┘
             │
             ▼
      Coverage Result /
          Reporting