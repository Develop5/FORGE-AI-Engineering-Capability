
# FORGE

```text
FORGE
│
├── Evidence Consolidation
├── Requirements Discovery
├── Clarification
├── Traceability Analysis
├── Coverage Analysis
├── Functional Risk
└── Improvement of Requirement Coverage
        │
        ▼
      MVP
        │
        └── subset / implementation scope
````

**Responsabilidades de FORGE**, no del MVP.

---

# 1. Evidence Consolidation

### Responsabilidades acordadas

1. **Aceptar evidencias heterogéneas** procedentes de las fuentes soportadas por FORGE:

   * páginas de Confluence;
   * tickets de Jira;
   * ficheros CSV;
   * ficheros de texto.

2. **Extraer y normalizar la información relevante** de cada tipo de evidencia, teniendo en cuenta que distintas fuentes pueden expresar la misma información de formas diferentes.

3. **Agrupar evidencias que pertenecen al mismo contexto/tópico**, sin asumir que un grupo equivale necesariamente a un Business Requirement.

4. **Mantener la referencia a la fuente de cada evidencia**, para preservar la trazabilidad.

5. **Entregar las evidencias agrupadas y normalizadas a Requirements Discovery**, junto con sus referencias.

### Punto deliberadamente abierto

La agrupación por similitud es una señal; **no hemos establecido todavía cómo se determina semánticamente que evidencias pertenecen al mismo contexto**.

---

# 2. Requirements Discovery

### Responsabilidades acordadas

1. **Recibir las evidencias agrupadas** procedentes de Evidence Consolidation.

2. **Analizar las evidencias para identificar candidatos a Business Requirements**, sin atribuirse la autoridad de declarar un Business Requirement oficialmente aprobado.

3. **Relacionar evidencias semánticamente** para determinar cuáles contribuyen al mismo candidato a Business Requirement.

4. **Descomponer agrupaciones que contienen varias necesidades** en candidatos separados cuando corresponda.

5. **Identificar duplicidades entre candidatos** y hacer merge de los candidatos duplicados, conservando todas sus referencias.

6. **Detectar problemas** en los candidatos:

   * ambigüedades;
   * conflictos;
   * dependencias circulares;
   * y otras relaciones relevantes para el análisis.

7. **Preservar la información relevante del candidato**, incluyendo:

   * identificador;
   * título;
   * prioridad, si está disponible;
   * Acceptance Criteria, si existe en las fuentes;
   * dependencias;
   * referencias a las evidencias que lo sustentan.

8. **Identificar información insuficiente o ambigua sin descartar el candidato**, derivándola a Clarification.

9. **Mantener la trazabilidad entre un candidato y las evidencias que justifican su identificación.**

### Relaciones descubiertas

Hemos identificado:

* jerarquía;
* agregación;
* duplicidad.

La duplicidad se resuelve mediante merge, pero se conservan las referencias de los candidatos originales.

### Punto deliberadamente abierto

La relación semántica entre evidencias/candidatos probablemente requerirá ayuda de un LLM, pero **no hemos decidido todavía cómo se implementará**.

---

# 3. Clarification

### Responsabilidades acordadas

1. **Recibir candidatos a Business Requirements junto con sus referencias y los problemas detectados.**

2. **Analizar los problemas pendientes** conforme a las definiciones del dominio.

3. **Generar preguntas de aclaración**, vinculando cada pregunta con el problema/finding que la originó.

4. **Recibir las respuestas del usuario** y mantener su relación con las preguntas correspondientes.

5. **Evaluar si las respuestas proporcionan información suficiente para resolver los problemas detectados.**

6. **Reanalizar el candidato con la nueva información aportada.**

7. **Generar nuevas preguntas** cuando la nueva información revele problemas adicionales.

8. **Actualizar los candidatos con las aclaraciones resueltas**, manteniendo simultáneamente la trazabilidad de preguntas y respuestas.

9. **Registrar como riesgos las cuestiones que el usuario decide dejar sin resolver**, incluyendo como mínimo:

   * Business Requirement afectado;
   * problema encontrado;
   * pregunta no respondida.

10. **Mantener el historial de la interacción de clarificación**, evitando perder las preguntas y respuestas originales.

11. **Determinar el estado de la clarificación** y permitir continuar cuando corresponda.

12. **Permitir que el usuario decida finalizar la iteración**, incluso cuando existan cuestiones pendientes; esas cuestiones pasan entonces a riesgos.

### Decisión importante

**Clarification no decide unilateralmente cuándo termina el proceso. El usuario tiene esa capacidad.**

Y hemos eliminado la idea anterior del límite de dos iteraciones.

---

# 4. Traceability Analysis

### Responsabilidades acordadas

1. **Recibir los Business Requirements procedentes de Clarification**, junto con referencias y riesgos.

2. **Analizar los Test Cases disponibles** para determinar relaciones justificables con los Business Requirements.

3. **Establecer relaciones Business Requirement ↔ Test Case**, permitiendo una relación N:N.

4. **Analizar la información disponible en cada Test Case** según su tipo y completitud:

   * título;
   * descripción;
   * steps;
   * expected result;
   * referencias explícitas;
   * y demás evidencia disponible.

5. **Establecer todas las relaciones suficientemente claras** entre Tests y Business Requirements.

6. **No establecer una relación cuando la correspondencia sea incierta.**

7. **Identificar los Business Requirements que no tienen ningún Test Case relacionado.**

8. **Registrar como riesgo cada Business Requirement sin Test Case**, conservando la trazabilidad correspondiente.

9. **Synthesize Functional Specifications when they are required to structure or clarify the relationship between Business Requirements and Test Cases.**

10. **Maintain the traceability between any synthesized Specification, the relevant Business Requirements, and the Test Cases it helps structure or clarify.**

11. **Mantener todos los datos recibidos de los Business Requirements**, incluidas sus referencias y riesgos.

### Regla de Coverage establecida

Un Business Requirement está **covered** si tiene **al menos un Test Case relacionado**.

---

# 5. Coverage Analysis

### Responsabilidad

> **Calcular Business Requirement Coverage conforme a la definición establecida en `Glossary.md`, utilizando las relaciones de trazabilidad establecidas entre Business Requirements y Test Cases.**

Coverage Analysis produce el Coverage Result como parte de esta misma responsabilidad.

No existe una etapa separada de Coverage Result.

### Responsabilidades acordadas

1. **Calcular el Current Coverage** utilizando los Business Requirements y las relaciones establecidas con los Test Cases existentes.

2. **Identificar los Business Requirements que tienen cobertura.**

3. **Identificar los Business Requirements que no tienen cobertura identificada.**

4. **Identificar los casos en los que la evidencia disponible es insuficiente para determinar la cobertura.**

5. **Producir el Coverage Result** con la información necesaria para que el usuario pueda entender el estado de la cobertura.

6. **Mantener la distinción entre Current Coverage y Projected Coverage.**

### Punto deliberadamente abierto

**No vamos a inventarle trabajo adicional simplemente porque sea una fase separada.**

---

# 6. Functional Risk

### Responsabilidad

**Identificar y presentar el functional risk derivado de la información producida durante el análisis.**

El riesgo puede estar asociado, entre otros casos, a:

* Business Requirements sin Test Case relacionado;
* evidencia insuficiente;
* conflictos o inconsistencias no resueltos;
* preguntas de Clarification que el usuario decide dejar sin resolver.

Functional Risk no constituye un sistema genérico de project risk management.

---

# 7. Improvement of Requirement Coverage

### Responsabilidad

**Mejorar el Business Requirement Coverage mediante la generación de Test Cases adicionales cuando el usuario lo solicite.**

El usuario puede proporcionar un target de coverage.

El target representa el **minimum desired coverage**.

El valor por defecto es **95%**.

FORGE intenta alcanzar **at least** el target solicitado.

### Responsabilidades acordadas

1. **Recibir el Current Coverage y la información de trazabilidad disponible.**

2. **Identificar los Business Requirements que requieren cobertura adicional para alcanzar el target.**

3. **Generar Test Cases adicionales destinados a cubrir esos Business Requirements.**

4. **Establecer la trazabilidad entre los Test Cases generados y los Business Requirements correspondientes.**

5. **Calcular el Projected Coverage resultante de incorporar los Test Cases generados.**

6. **Identificar los Business Requirements que permanecerían sin suficiente cobertura.**

7. **Determinar si el target solicitado ha sido alcanzado.**

8. **Informar explícitamente cuando el target no pueda ser alcanzado.**

### Regla de Projected Coverage

**Projected Coverage no constituye evidencia de Test Coverage ejecutado.**

Los Test Cases generados por FORGE no se consideran ejecutados ni validados por el mero hecho de haber sido generados.

### Punto deliberadamente abierto

Todavía no hemos definido en Architecture cómo se generarán los Test Cases ni cómo se determinará técnicamente la mejor combinación de Test Cases para alcanzar el target solicitado.


