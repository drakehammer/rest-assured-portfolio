# REST Assured API Testing Portfolio

Suite de pruebas automatizadas de API construida con **REST Assured + Java + TestNG**, diseñada como proyecto de portafolio para demostrar habilidades de QA Automation en pruebas de servicios REST.

## 🎯 Objetivo

Validar el comportamiento completo (CRUD) de la API pública [ReqRes](https://reqres.in), aplicando buenas prácticas de automatización usadas en entornos profesionales:

- Separación de configuración (`BaseTest` y `ConfigReader`) y lógica de prueba.
- Implementación de **API Client Pattern** para encapsular llamadas HTTP.
- Validación de esquemas JSON (contratos de API).
- **Data-Driven Testing** usando `@DataProvider` para cobertura de casos de borde.
- POJOs para serialización/deserialización de requests.
- Casos positivos, negativos y de borde.
- Reportes visuales con Allure integrados en un Pipeline de CI/CD.
- Anotaciones descriptivas (`@Epic`, `@Feature`, `@Story`, `@Severity`) para trazabilidad.

## 🛠️ Stack técnico

| Herramienta | Uso |
|---|---|
| Java 17 | Lenguaje base |
| REST Assured | Cliente HTTP y aserciones fluidas |
| TestNG | Runner y organización de pruebas |
| AssertJ | Aserciones avanzadas y legibles |
| Jackson | Serialización de POJOs a JSON |
| JSON Schema Validator | Validación de contrato de la API |
| Allure | Reportes de ejecución |
| Maven | Gestión de dependencias y build |
| GitHub Actions | CI/CD y despliegue de reportes |

## 📁 Estructura del proyecto

```
src/test/java/com/portfolio/api/
├── base/
│   ├── BaseTest.java          # Configuración común (specs, logging)
│   └── ConfigReader.java      # Carga de propiedades (config.properties)
├── clients/
│   └── UserClient.java        # Encapsulamiento de endpoints (API Client Pattern)
├── models/
│   └── User.java              # POJO del payload de usuario
└── tests/
    ├── GetUsersTest.java       # GET: listado, detalle, caso 404
    ├── CreateUserTest.java     # POST: creación de usuario
    ├── UpdateUserTest.java     # PUT: actualización de usuario
    └── DeleteUserTest.java     # DELETE: eliminación de usuario

src/test/resources/
├── config.properties           # Variables de entorno (URL Base, etc)
└── schemas/                    # Esquemas JSON para validar contratos
```

## ▶️ Cómo ejecutar las pruebas

Requisitos: Java 17+ y Maven instalados.

```bash
mvn clean test
```

## 📊 Reportes y Evidencia

### Reporte Interactivo (Live)
El proyecto cuenta con un pipeline de CI/CD que despliega automáticamente los resultados en GitHub Pages. Puedes acceder al reporte interactivo aquí:
👉 **[Link al Reporte de Allure](https://<TU_USUARIO>.github.io/rest-assured-portfolio/)** *(Sustituir con tu link de GH Pages)*

### Capturas de Pantalla
*(Sugerencia: Sube imágenes a una carpeta `/screenshots` en el repo y enlaza aquí)*
- **Dashboard de Allure:** `![Dashboard](screenshots/dashboard.png)`
- **Detalle de ejecución:** `![Execution](screenshots/execution.png)`

## 🧪 Casos cubiertos

- ✅ Listar usuarios paginados y validar esquema de respuesta.
- ✅ Obtener un usuario por id y validar datos consistentes.
- ✅ Manejo de errores 404 para usuarios inexistentes.
- ✅ Crear usuarios con diversos datos (Data-Driven) y validar eco de respuesta.
- ✅ Validar comportamiento ante payloads mal formados (Casos Negativos).
- ✅ Actualizar y eliminar usuarios validando códigos de respuesta.

---

**Autor:** Diego Fernando Caldas A. — Ingeniero de Sistemas | QA Automation
**LinkedIn:** [Tu Perfil de LinkedIn] | **GitHub:** [Tu Perfil de GitHub]
