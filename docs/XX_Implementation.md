ForgeEngine
 ├── start(Execution)
 └── resume(Execution, response)
                  │
                  ▼
        WorkflowOrchestrator
                  │
          ExecutionContext
          ├── pendingQuestion
          └── pendingResponse
                  │
                  ▼
          ClarificationInput
          ├── BusinessRequirements
          ├── Findings
          ├── Question
          └── response
                  │
                  ▼
            Clarification
                  │
                  ▼
          ClarificationOutput
          ├── BusinessRequirements
          ├── Findings
          ├── Questions
          └── Risks
                  │
                  ▼
        Workflow Orchestrator
                  │
          ┌───────┴────────┐
          ▼                ▼
   nueva Question       continuar
          │                │
       WAITING         Traceability