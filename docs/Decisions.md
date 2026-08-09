# Registro de decisiones de producto


| ID    | Date       | Decision                                                                                                  | Reason                                                                                                            |
|-------|------------|-----------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|
| D-001 | 2026-08-07 | Primary user changed from QA Analyst to Product Manager.                                                  | The Product Manager has the broadest visibility of release risk while maintaining the same underlying capability. |
| D-002 | 2026-08-07 | The primary deliverable of the MVP is the Coverage Report.                                                | It represents the highest-value outcome for the target user.                                                      |
| D-003 | 2026-08-07 | Requirements Discovery is an internal capability, not the product itself.                                 | The product exists to calculate Business Requirement Coverage.                                                   |
| D-004 | 2026-08-07 | The MVP initially supports two Evidence Sources: Confluence pages and Jira tickets (Test Cases, Bugs, User Stories and Epics). | These sources are sufficient to demonstrate the end-to-end value proposition while allowing Requirements Discovery to identify Business Requirements across heterogeneous evidence. |                                            |
| D-005 | 2026-08-07 | FORGE supports collaboration between roles but does not modify existing organizational responsibilities | Collaboration among different roles provides high value and a smooth process.    |
| D-006 | 2026-08-08 | Glossary definitions are added progressively when discussions or identified ambiguities require them. | Avoid defining universally understood terms prematurely and keep the glossary focused on terms that require explicit clarification. |



El usuario introduce en la plataforma:
- Páginas de Confluence
- Listado de tickets en Jira: Test cases, bugs, user stories, epics
El sistema analiza y determina cuáles son posibles business requirements. Y entre ellos:
- Busca conflictos 
- Localiza dependencias circulares
- Identifica ambigüedades.
Le brinda al usuario el resultado de ese análisis y un listado de preguntas necesarias para aclarar esos problemas encontrados
Al recibir el feedback del usuario, la plataforma comienza la síntesis de especificaciones. Si algunas preguntas no son contestadas (dudas no resueltas), el sistema sigue trabajando y las señalará en el reporte final de Coverage como riesgos asumidos.
Con las aclaraciones dadas por el usuario, la plataforma genera especificaciones.
Estas especificaciones las tomará el usuario para discusiones con stakeholders y su aprobación o rechazo.
Con las especificaciones aprobadas, la plataforma hará un cálculo de coverage (definido en Glossary)
Finalmente la plataforma suministra un Coverage Report

