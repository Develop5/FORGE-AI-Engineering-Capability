

> **Continuamos FORGE desde Project Coordination y Architecture.**
>
> Este chat es exclusivamente para **Engineering**.
>
> ## Estado del proyecto
>
> * Product está cerrado en esta fase.
> * Architecture está cerrada en esta fase.
> * Las contradicciones y decisiones conceptuales entre Product, MVP, UserFlow, Architecture, Glossary, Capability Map, Responsibility Map y Decisions ya han sido trabajadas en los chats correspondientes.
> * **No reabrir Product ni Architecture**, salvo que durante la implementación aparezca un bloqueo técnico real que haga imposible cumplirlas.
> * El objetivo inmediato es construir una **demo funcional E2E del MVP en menos de una semana**.
>
> ## Source of Truth
>
> El repositorio GitHub sigue siendo la única fuente de verdad de la documentación.
>
> Antes de utilizar cualquier documento, debes leer su versión actual directamente desde `main`/Raw. Nunca reconstruyas su contenido desde memoria o desde esta conversación.
>
> Documentos:
>
> * Architecture.md
>   [https://raw.githubusercontent.com/Develop5/FORGE-AI-Engineering-Capability/refs/heads/main/docs/Architecture.md](https://raw.githubusercontent.com/Develop5/FORGE-AI-Engineering-Capability/refs/heads/main/docs/Architecture.md)
> * Glossary.md
>   [https://raw.githubusercontent.com/Develop5/FORGE-AI-Engineering-Capability/refs/heads/main/docs/Glossary.md](https://raw.githubusercontent.com/Develop5/FORGE-AI-Engineering-Capability/refs/heads/main/docs/Glossary.md)
> * Forge_Responsibility_Map.md
>   [https://raw.githubusercontent.com/Develop5/FORGE-AI-Engineering-Capability/refs/heads/main/docs/Forge_Responsibility_Map.md](https://raw.githubusercontent.com/Develop5/FORGE-AI-Engineering-Capability/refs/heads/main/docs/Forge_Responsibility_Map.md)
> * Capability_Map.md
>   [https://raw.githubusercontent.com/Develop5/FORGE-AI-Engineering-Capability/refs/heads/main/docs/Capability_Map.md](https://raw.githubusercontent.com/Develop5/FORGE-AI-Engineering-Capability/refs/heads/main/docs/Capability_Map.md)
> * Decisions.md
>   [https://raw.githubusercontent.com/Develop5/FORGE-AI-Engineering-Capability/refs/heads/main/docs/Decisions.md](https://raw.githubusercontent.com/Develop5/FORGE-AI-Engineering-Capability/refs/heads/main/docs/Decisions.md)
> * Product.md
>   [https://raw.githubusercontent.com/Develop5/FORGE-AI-Engineering-Capability/refs/heads/main/docs/Product.md](https://raw.githubusercontent.com/Develop5/FORGE-AI-Engineering-Capability/refs/heads/main/docs/Product.md)
> * MVP.md
>   [https://raw.githubusercontent.com/Develop5/FORGE-AI-Engineering-Capability/refs/heads/main/docs/MVP.md](https://raw.githubusercontent.com/Develop5/FORGE-AI-Engineering-Capability/refs/heads/main/docs/MVP.md)
> * UserFlow.md
>   [https://raw.githubusercontent.com/Develop5/FORGE-AI-Engineering-Capability/refs/heads/main/docs/UserFlow.md](https://raw.githubusercontent.com/Develop5/FORGE-AI-Engineering-Capability/refs/heads/main/docs/UserFlow.md)
>
> **No necesito un nuevo análisis de contradicciones.** Si una decisión ya está establecida en estos documentos, trátala como requisito.
>
> ---
>
> ## Decisiones de Engineering ya tomadas
>
> ### Runtime
>
> La primera implementación **no requiere Spring Boot ni REST API**.
>
> Queremos separar:
>
> ```text
> FORGE Engine
>       ↓
> Interface / Runtime
> ```
>
> El primer runtime puede ser local y orientado a Agent/Skill. La interfaz concreta no debe contaminar el núcleo.
>
> ### Infraestructura
>
> Para el MVP inicial quedan fuera:
>
> * RAG
> * ChromaDB
> * embeddings
> * vector databases
> * Jira/Confluence reales
> * cloud deployment
> * infraestructura empresarial
>
> No introducir ninguna de ellas salvo que una necesidad real de implementación lo haga imprescindible.
>
> La evidencia inicial será local/controlada para la demo.
>
> ### Diseño
>
> El objetivo no es diseñar una plataforma completa. Es construir el **mínimo vertical slice que demuestre el flujo E2E del MVP**.
>
> Tampoco quiero crear nuevos documentos todavía. Primero resolveremos el diseño técnico aquí y solo documentaremos permanentemente aquello que realmente necesite mantenerse.
>
> ---
>
> # Punto exacto donde empieza Engineering
>
> Architecture ya ha establecido la separación entre:
>
> ```text
> Interface
>     ↓
> Workflow Orchestrator
>     ↓
> Capabilities
>     ↓
> Domain / infrastructure abstractions
> ```
>
> El trabajo de Engineering es ahora convertir eso en un diseño técnico mínimo que podamos implementar.
>
> ---
>
> # Secuencia de trabajo obligatoria
>
> Resolveremos estos pasos **uno cada vez**, sin saltar al siguiente:
>
> ### 1. Domain Model
>
> Derivar de los documentos actuales únicamente los objetos que necesita el MVP.
>
> Para cada uno:
>
> * propósito;
> * información mínima;
> * relaciones;
> * qué es persistente durante una execution;
> * qué es input/output.
>
> Mantener explícitamente la distinción:
>
> ```text
> FORGE knowledge / analysis
> ```
>
> frente a:
>
> ```text
> generated / proposed artifacts
> ```
>
> Los Generated Test Cases son artefactos generados por FORGE y forman parte del flujo del MVP, pero no deben tratarse automáticamente como evidencia equivalente a Test Cases existentes.
>
> **No diseñar todavía clases Java.**
>
> ---
>
> ### 2. Execution Model
>
> Definir el mínimo necesario para ejecutar el workflow:
>
> * Execution;
> * estado;
> * contexto;
> * transición entre etapas;
> * pausa/reanudación;
> * interacción de Clarification.
>
> El Orchestrator posee el estado lógico.
>
> No diseñar todavía persistencia concreta salvo que sea necesaria para resolver el modelo.
>
> ---
>
> ### 3. Capability Contracts
>
> Para cada capability definida por Architecture:
>
> ```text
> Input → Capability → Output
> ```
>
> Determinar:
>
> * input;
> * output;
> * dependencias;
> * qué puede producir;
> * qué no puede producir;
> * si puede requerir interacción.
>
> No añadir capabilities nuevas solo porque aparezca una actividad dentro del User Flow.
>
> ---
>
> ### 4. Runtime / Interface
>
> Una vez definido el Engine:
>
> * elegir el runtime local mínimo;
> * definir cómo se invoca;
> * definir cómo recibe evidencia;
> * definir cómo devuelve resultados;
> * mantenerlo desacoplado del Engine.
>
> ---
>
> ### 5. Primer vertical slice
>
> Implementar primero el camino mínimo que demuestre que el Engine funciona.
>
> Después incorporar progresivamente las partes restantes del MVP hasta conseguir:
>
> ```text
> Evidence
>   ↓
> Requirements
>   ↓
> Clarification
>   ↓
> Traceability
>   ↓
> Coverage
>   ↓
> Improvement
>   ↓
> Generated Test Cases
>   ↓
> Projected Coverage
> ```
>
> siempre respetando el flujo real definido actualmente en `UserFlow.md`.
>
> ---
>
> ## Estilo de trabajo
>
> Quiero respuestas **concisas y accionables**.
>
> No hagas grandes resúmenes del repositorio.
>
> No repitas decisiones ya tomadas.
>
> No diseñes cinco pasos por adelantado cuando estamos resolviendo uno.
>
> En cada turno:
>
> 1. indica brevemente qué estás resolviendo;
> 2. presenta la propuesta;
> 3. señala únicamente las decisiones que necesito validar;
> 4. espera mi confirmación antes de pasar al siguiente paso.
>
> **Primera tarea ahora: Domain Model mínimo.**

---

## El roadmap que queda

Así, el nuevo chat no tiene que "descubrir FORGE" otra vez. Tiene que **construirlo**:

```text
                  ENGINEERING
                       │
                       ▼
              1. Domain Model
                       │
                       ▼
             2. Execution Model
                       │
                       ▼
           3. Capability Contracts
                       │
                       ▼
             4. Runtime local
                       │
                       ▼
          5. First vertical slice
                       │
                       ▼
               6. E2E MVP demo
```

Y hay una regla que considero especialmente importante para este nuevo chat:

> **Si una decisión ya está establecida por la documentación actual, Engineering la implementa; no vuelve a debatirla. Si una decisión no está establecida, Engineering propone la mínima solución necesaria para el MVP y te pide validación.**

