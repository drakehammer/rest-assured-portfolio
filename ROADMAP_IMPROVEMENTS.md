# 🚀 Roadmap de Mejoras - REST Assured Portfolio

Este documento sirve como guía de pasos a seguir para elevar el nivel del proyecto de un nivel básico/intermedio a un nivel profesional/senior.

## 🏗️ 1. Arquitectura y Diseño (Clean Code)
- [x] **Implementar API Client Pattern (Manager):**
    - Crear una clase `UserClient` que encapsule las llamadas HTTP (`given().when().get(...)`).
    - El test solo debe llamar al método del cliente y validar la respuesta.
    - *Objetivo:* Separar la infraestructura (HTTP) de la lógica de negocio (Test).
- [x] **Pruebas Parametrizadas (Data-Driven Testing):**
    - Utilizar `@DataProvider` de TestNG para ejecutar los mismos escenarios con múltiples sets de datos.
    - Cubrir casos de borde: strings vacíos, caracteres especiales, límites de longitud.
    - *Objetivo:* Aumentar la cobertura sin duplicar código.

## ⚙️ 2. Entorno y DevOps (Professional Workflow)
- [x] **Externalización de Configuración:**
    - Mover la `BASE_URL` y otros parámetros a un archivo `config.properties` o `.env`.
    - Implementar la capacidad de cambiar de entorno mediante parámetros de Maven (ej: `-Denv=staging`).
    - *Objetivo:* Evitar el hardcoding y facilitar la ejecución en diferentes ambientes.
- [x] **Pipeline de CI/CD con GitHub Actions:**
    - Crear un workflow `.yml` que ejecute `mvn clean test` en cada push.
    - Configurar la publicación automática de los reportes de Allure en **GitHub Pages**.
    - *Objetivo:* Demostrar conocimientos de integración continua y visibilidad de resultados.

## 🛠️ 3. Pulido Técnico (Advanced Testing)
- [x] **Migrar a AssertJ:**
    - Sustituir algunas aserciones de Hamcrest por AssertJ para obtener validaciones más fluidas y potentes (especialmente para comparaciones de objetos POJO).
    - *Objetivo:* Mejorar la legibilidad y capacidad de las aserciones.
- [x] **Expandir Casos Negativos y de Seguridad:**
    - Validar respuestas ante payloads mal formados (JSON inválido).
    - Probar endpoints con datos inexistentes o prohibidos.
    - *Objetivo:* Demostrar un enfoque exhaustivo de "qué pasa si algo sale mal".

## 🎨 4. Presentación y Documentación (Portfolio Impact)
- [ ] **Evidencia Visual en README:**
    - Añadir capturas de pantalla del reporte de Allure (gráficas de pastel, timeline, etc.).
    - Añadir un enlace directo al reporte desplegado en GitHub Pages.
    - *Objetivo:* Captar la atención del reclutador inmediatamente sin que tenga que clonar el repo.

---
**Prioridad Sugerida:**
`CI/CD` $\rightarrow$ `API Client Pattern` $\rightarrow$ `Data-Driven Testing` $\rightarrow$ `Evidencia Visual`
