Sí. Te las dejo **como referencia de trabajo**, incorporando las correcciones y matices que fuimos aprobando. No las considero todavía el documento oficial de arquitectura.

## Evidence Consolidation

1. **Aceptar evidencias heterogéneas** de las fuentes soportadas:

   * páginas de Confluence;
   * tickets de Jira;
   * ficheros CSV;
   * ficheros de texto.

2. **Extraer y normalizar la información relevante** de cada tipo de evidencia, teniendo en cuenta que una misma información puede aparecer expresada de formas diferentes.

3. **Agrupar evidencias que pertenecen al mismo contexto o tópico**, sin asumir que un grupo equivale necesariamente a un Business Requirement.

4. **Relacionar evidencias que pertenecen a un mismo contexto**, utilizando las características disponibles de cada evidencia.

5. **Mantener la referencia a la fuente original** de cada evidencia.

6. **Entregar la información agrupada y normalizada** a Requirements Discovery, con las referencias correspondientes.

> Punto importante: Evidence Consolidation **no determina que exista un Business Requirement**. Produce agrupaciones de evidencia para que Requirements Discovery pueda hacerlo.

---

# Clarification

1. **Recibir los candidatos a Business Requirements**, junto con sus referencias y los problemas/findings detectados.

2. **Analizar los problemas pendientes** de acuerdo con las definiciones del dominio.

3. **Generar preguntas de aclaración**, vinculando cada pregunta con el finding que la originó.

4. **Recibir las respuestas del usuario** y mantener su relación con las preguntas correspondientes.

5. **Determinar si las respuestas son suficientemente aclaratorias**, volviendo a analizar el candidato con la nueva información.

6. **Detectar nuevos problemas** que puedan aparecer como consecuencia de las respuestas.

7. **Generar nuevas preguntas** cuando esos nuevos problemas requieran aclaración.

8. **Incorporar al Business Requirement la información que haya quedado aclarada**, manteniendo la trazabilidad con las preguntas y respuestas originales.

9. **Clasificar las respuestas** como, al menos:

   * respuesta que aporta nueva información;
   * respuesta vacía;
   * respuesta que indica que el asunto queda pendiente/debe ignorarse por ahora.

10. **Registrar como riesgo las cuestiones que el usuario decide dejar sin resolver**, conservando:

    * Business Requirement afectado;
    * problema encontrado;
    * pregunta no respondida.

11. **Mantener el historial de preguntas y respuestas** para preservar la trazabilidad de la clarificación.

12. **Determinar si existen todavía problemas que requieran otra iteración.**

13. **Permitir que el usuario decida finalizar la clarificación**, incluso cuando existan problemas pendientes; estos quedan entonces registrados como riesgos.

> Corrección importante respecto a una versión anterior: **Clarification no tiene un límite de iteraciones de dos**. El usuario decide cuándo dejar de iterar.

---

# Traceability Analysis

1. **Recibir los Business Requirements** procedentes de Clarification, junto con sus referencias y riesgos.

2. **Recibir/analizar los Test Cases existentes** y la información disponible sobre ellos.

3. **Evaluar la información disponible de cada Test Case** según su tipo y completitud, incluyendo cuando estén disponibles:

   * título;
   * descripción;
   * steps;
   * expected result;
   * referencias explícitas;
   * otras evidencias relevantes.

4. **Determinar las relaciones entre Test Cases y Business Requirements**.

5. **Establecer todas las relaciones suficientemente claras**, permitiendo una relación N:N entre Business Requirements y Test Cases.

6. **No establecer una relación cuando la correspondencia sea incierta.**

7. **Determinar qué Business Requirements tienen al menos un Test Case relacionado.**

8. **Identificar los Business Requirements que no tienen ningún Test Case relacionado.**

9. **Registrar como riesgo cada Business Requirement sin Test Case.**

10. **Generar Specifications para los Test Cases que no puedan relacionarse con ningún Business Requirement**, como salida adicional para análisis posterior.

11. **Mantener todos los datos recibidos de los Business Requirements**, incluidas referencias y riesgos.

### Regla establecida para Coverage

```text
BR → ≥ 1 Test Case relacionado → COVERED
BR → 0 Test Cases relacionados → NOT COVERED
```

Y una relación incierta **no cuenta como relación de trazabilidad**.

---

# Coverage Analysis

Aquí hemos sido deliberadamente muy estrictos:

### Única responsabilidad

> **Calcular Business Requirement Coverage conforme a la definición establecida en `Glossary.md`, utilizando las relaciones de trazabilidad establecidas entre Business Requirements y Test Cases.**

Es decir:

```text
Business Requirements
        +
Traceability BR ↔ Test
        ↓
Coverage Analysis
        ↓
Business Requirement Coverage
```

No genera preguntas, no genera Tests, no modifica Requirements y no decide qué hacer con los riesgos.

---

## Y una observación importante para lo que viene

Estas cuatro capacidades ya nos están dando algo muy útil para arquitectura:

```text
Evidence Consolidation
        ↓
Requirements Discovery
        ↓
Clarification
        ↓
Traceability Analysis
        ↓
Coverage Analysis
        ↓
Improvement of Requirement Coverage
```

Y ahora estamos descubriendo que **cada frontera no es simplemente un paso del workflow**: cada una transforma información y tiene responsabilidades propias.

Eso es precisamente el material que necesitaremos para construir el **Architecture Boundary Map** cuando terminemos de definir las capacidades nuevas.


---

# Improvement of Requirement Coverage

1. Recibir los Test Cases existentes, Specifications, Business Requirements, riesgos y referencias relevantes.
2. Determinar qué Business Requirements requieren nuevos Test Cases para mejorar el Coverage.
3. Determinar el target de Business Requirement Coverage aplicable.
4. Determinar cuántos Test Cases adicionales son necesarios.
5. Generar los Test Cases adicionales para los Business Requirements identificados.
6. Calcular el **Projected Coverage** considerando todos los Business Requirements y los Test Cases existentes y generados.
7. Determinar cuándo finalizar la generación:

   * target alcanzado;
   * usuario interrumpe;
   * información insuficiente para generar más Test Cases;
   * **[pendiente de decidir] imposibilidad de mejorar Coverage pese a disponer de información suficiente**.
8. Entregar los resultados de la mejora, incluyendo los Test Cases generados y el Projected Coverage.

---



