# Contribuyendo al REST Assured API Testing Portfolio

¡Gracias por tu interés en mejorar este proyecto! Para mantener la alta calidad y los estándares arquitectónicos de este portafolio, por favor sigue estas guías al realizar contribuciones.

## 🛠️ Estándares Arquitectónicos

Este proyecto sigue el **API Client Pattern** para separar la infraestructura de comunicación de la lógica de las pruebas.

1.  **UserClient**: Todas las llamadas HTTP deben estar encapsuladas aquí. No utilices `given().when().get()` directamente dentro de las clases de prueba.
2.  **BaseTest**: Utiliza las `RequestSpecification` y `ResponseSpecification` proporcionadas para asegurar un logging y comportamiento consistentes.
3.  **POJOs**: Utiliza siempre los modelos en `com.portfolio.api.models` para los cuerpos de petición y respuesta para garantizar la seguridad de tipos.

## 🧪 Cómo Añadir una Nueva Prueba

1.  **Datos Primero**: Si la prueba requiere múltiples conjuntos de datos, añádelos a un archivo JSON en `src/test/resources/testdata/`.
2.  **Método del Cliente**: Si el endpoint es nuevo, añade el método correspondiente en `UserClient`.
3.  **Implementación**:
    - Crea un nuevo método de prueba en la clase de test apropiada.
    - Utiliza `@ParameterizedTest` y `@MethodSource` si usas datos externos.
    - Utiliza `UserAssert` para las validaciones y mantener la prueba expresiva.
4.  **Validación de Contrato**: Incluye siempre una validación de esquema JSON para asegurar que el contrato de la API se mantiene.

## 📈 Guía de Estilo

### Aserciones
Utilizamos **AssertJ** para todas las validaciones. Evita el uso de `assertEquals` o Hamcrest.
- **Mal:** `assertEquals(user.getName(), "Diego");`
- **Bien:** `UserAssert.assertThat(user).hasName("Diego");`

### Reportes
Todas las pruebas deben estar anotadas para **Allure**:
- `@Epic`, `@Feature`, `@Story` para trazabilidad.
- `@Severity` para indicar la criticidad del caso.
- `@Description` para explicar el "por qué" de la prueba.

## 🚀 Ejecución de las Pruebas

Para ejecutar la suite completa y generar el reporte:
```bash
mvn clean test
```

El reporte estará disponible en la carpeta `allure-results` y puede visualizarse mediante:
```bash
allure serve allure-results
```

---
*¡Feliz testing!*
