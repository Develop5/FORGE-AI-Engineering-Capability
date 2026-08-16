                    ┌───────────────┐
                    │   Interface   │
                    └───────┬───────┘
                            │
                            ▼
                    ┌───────────────┐
                    │ Orchestrator  │
                    └───────┬───────┘
                            │
                            ▼
              ┌─────────────────────────┐
              │   FORGE Capabilities    │
              └───────────┬─────────────┘
                          │
                ┌─────────┴─────────┐
                ▼                   ▼
          Source adapters      LLM abstraction
                │                   │
                ▼                   ▼
          External systems     LLM providers