


# Architecture Responsibility Map

Propongo construirlo en **cuatro niveles**, porque así evitamos mezclar responsabilidades.

```text
                    ┌─────────────────────────┐
                    │       INTERFACE          │
                    │   Web / Agent / Skill   │
                    └────────────┬────────────┘
                                 │
                                 ▼
                    ┌─────────────────────────┐
                    │  WORKFLOW ORCHESTRATION │
                    │                         │
                    │ execution lifecycle     │
                    │ state                   │
                    │ transitions             │
                    │ user decisions          │
                    └────────────┬────────────┘
                                 │
                                 ▼
        ┌─────────────────────────────────────────────┐
        │                 FORGE DOMAIN                 │
        │                                             │
        │  Evidence Consolidation                    │
        │  Requirements Discovery                     │
        │  Clarification                              │
        │  Traceability Analysis                      │
        │  Coverage Analysis                           │
        │  Coverage Improvement Planning               │
        │  Test Case Generation                        │
        │                                             │
        │  Domain concepts:                           │
        │  BR / Finding / Question / Risk /            │
        │  Specification / Test Case / Coverage Result │
        └──────────────────────┬──────────────────────┘
                               │
                               ▼
                    ┌─────────────────────────┐
                    │   EXTERNAL SERVICES     │
                    │                         │
                    │ LLM                     │
                    │ Jira                    │
                    │ Confluence              │
                    │ File sources             │
                    └─────────────────────────┘
```

**Pero este dibujo es todavía demasiado abstracto.** Ahora tenemos que asignar responsabilidades.

---

# 1. Interface / Adapters

### Responsabilidad

**Interactuar con el exterior de FORGE sin contener lógica de negocio.**

Puede:

* recibir inputs del usuario;
* presentar preguntas;
* recoger respuestas;
* mostrar resultados;
* permitir decisiones del usuario;
* adaptar formatos externos al modelo que espera FORGE;
* representar `Coverage Result`.

No debe:

* decidir si un BR está cubierto;
* detectar conflictos;
* generar Questions;
* decidir cuándo un Finding es Risk;
* calcular Coverage.

---

# 2. Workflow Orchestrator

Esta es probablemente la pieza que más hemos descubierto durante este ejercicio.

### Responsabilidad

**Controlar la ejecución de FORGE.**

Debe:

* iniciar una ejecución;
* mantener su estado;
* invocar capabilities en el orden correspondiente;
* saber qué fase está ejecutándose;
* gestionar esperas por interacción del usuario;
* reanudar una ejecución;
* recibir decisiones del usuario;
* decidir qué capability se ejecuta a continuación;
* controlar iteraciones;
* determinar cuándo termina el workflow.

No debe:

* analizar Requirements;
* generar Questions;
* calcular Coverage;
* generar Test Cases;
* construir la lógica funcional del Coverage Result.

En otras palabras:

> **Orchestrator controla el proceso; las capabilities hacen el trabajo de análisis.**

---

# 3. Evidence Consolidation

Responsable de:

* recibir diferentes tipos de evidencia;
* normalizar la información necesaria;
* relacionar evidencias;
* agruparlas por tópico;
* conservar las referencias.

Entrega:

```text
Topic
├── name
├── information found
└── references
```

No determina todavía que exista un BR.

---

# 4. Requirements Discovery

Responsable de:

* transformar Topics en candidatos a BR;
* separar necesidades distintas;
* relacionar evidencias;
* identificar duplicidades;
* identificar jerarquías/agregaciones;
* fusionar candidatos duplicados;
* detectar Findings;
* conservar referencias.

Entrega:

```text
Business Requirement Candidate
+
Findings
```

---

# 5. Clarification

Responsable de:

* recibir BRs y Findings;
* generar Questions;
* procesar respuestas;
* reevaluar los problemas;
* determinar si los Findings quedan resueltos;
* actualizar los BRs con las aclaraciones;
* convertir Findings no resueltos en Risks al finalizar.

No hace Requirements Discovery.

---

# 6. Traceability Analysis

Responsable de:

* relacionar BRs con Test Cases;
* determinar relaciones N:N;
* identificar BRs sin Test Case;
* registrar esos casos como Risk;
* identificar Test Cases que no pueden relacionarse con un BR;
* generar Specifications para esos Tests;
* conservar referencias y demás información de los BRs.

Y una regla importante:

> **No convierte Specifications en BRs.**

---

# 7. Coverage Analysis

Responsabilidad central:

> **Calcular Business Requirement Coverage.**

Entrada:

```text
BRs
+
Test Cases
+
BR ↔ Test Case relationships
```

Resultado:

```text
Current Coverage
BRs covered
BRs not covered
```

Los Risks se conservan pero **no participan en el cálculo**.

---

# 8. Coverage Improvement Planning

Responsable de:

* recibir Current Coverage;
* recibir target;
* identificar BRs no covered;
* determinar cuáles necesitan Tests;
* estimar cuántos Tests hacen falta;
* asociar esas necesidades a BRs.

No:

* genera Tests;
* consulta Specifications;
* determina si un Test puede generarse.

Entrega:

```text
Coverage Improvement Plan
```

---

# 9. Test Case Generation

Responsable de:

* recibir el plan;
* utilizar BRs;
* utilizar Acceptance Criteria;
* utilizar References como contexto disponible;
* utilizar Risks como contexto;
* utilizar Specifications **aprobadas**;
* determinar, individualmente, si existe información suficiente;
* generar Test Cases;
* informar qué necesidades no pudieron satisfacerse y por qué.

No:

* recalcula Coverage;
* modifica BRs;
* resuelve Clarification;
* vuelve a ejecutar Traceability.

---

# 10. Projected Coverage

No lo convertimos en capability.

Es una **responsabilidad de cálculo** dentro del Improvement.

Recibe:

```text
All BRs
+
Existing TCs
+
Generated TCs
+
BR ↔ TC relationships
```

Produce:

```text
Projected Coverage
BRs covered
BRs not covered
Target
```

Y **no utiliza** Risks ni Specifications para determinar coverage.

---

# 11. Coverage Result

Aquí está nuestra decisión arquitectónica nueva.

`Coverage Result` pertenece al **dominio de FORGE**.

No es una capability y no pertenece a la interfaz.

Puede contener:

* Current Coverage;
* Projected Coverage;
* Target;
* BRs covered;
* BRs not covered;
* Risks;
* generated Test Cases;
* Tests que no pudieron generarse;
* razones;
* información necesaria para explicar por qué no se alcanzó el target.

La interfaz decide cómo representarlo.

---

# 12. External Services

Y aquí hay otra frontera que debemos mantener.

FORGE puede utilizar:

* LLM;
* Jira;
* Confluence;
* ficheros;
* eventualmente otros sistemas.

Pero las capabilities **no deberían estar diseñadas alrededor de una implementación concreta de esos servicios**.

Por ejemplo:

```text
Requirements Discovery
        │
        ▼
   LLM capability
        │
        ▼
   LLM provider
```

No:

```text
Requirements Discovery
        │
        ▼
   OpenAI GPT-X
```

Esto será especialmente importante para tu objetivo de convertir FORGE posteriormente en una **Skill/capability reutilizable por distintos agentes**.

---

# El mapa que tenemos ahora

En forma compacta:

```text
┌──────────────────────────────────────────────┐
│                 INTERFACE                    │
│  interaction / presentation / adaptation     │
└───────────────────────┬──────────────────────┘
                        │
                        ▼
┌──────────────────────────────────────────────┐
│            WORKFLOW ORCHESTRATOR             │
│ lifecycle / state / transitions / decisions │
└───────────────────────┬──────────────────────┘
                        │
                        ▼
┌──────────────────────────────────────────────┐
│                 FORGE DOMAIN                 │
│                                              │
│ Evidence Consolidation                       │
│ Requirements Discovery                       │
│ Clarification                                │
│ Traceability Analysis                        │
│ Coverage Analysis                            │
│ Coverage Improvement Planning                │
│ Test Case Generation                         │
│                                              │
│ BR / Finding / Question / Risk               │
│ Specification / Test Case / Coverage Result  │
└───────────────────────┬──────────────────────┘
                        │
                        ▼
┌──────────────────────────────────────────────┐
│             EXTERNAL SERVICES                │
│ Jira / Confluence / Files / LLM providers    │
└──────────────────────────────────────────────┘
```

