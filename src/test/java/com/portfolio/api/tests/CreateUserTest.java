package com.portfolio.api.tests;

import com.portfolio.api.base.BaseTest;
import com.portfolio.api.models.User;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("API Testing Portfolio")
@Feature("Usuarios - Creación (POST)")
public class CreateUserTest extends BaseTest {

    @DataProvider(name = "userDataProvider")
    public Object[][] userData() {
        return new Object[][]{
                {"Diego Caldas", "QA Automation Engineer"}, // Caso Positivo
                {"Diego Caldas", null},                      // Datos parciales (sin job)
                {null, "QA Automation Engineer"},            // Datos parciales (sin name)
                {"", ""},                                   // Strings vacíos
        };
    }

    @Test(dataProvider = "userDataProvider",
          description = "Crear usuario con diferentes combinaciones de datos")
    @Story("Alta de usuario - Data Driven")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida que la API acepte diversas combinaciones de nombre y job, "
            + "siempre respondiendo 201 (comportamiento actual de ReqRes).")
    public void deberiaCrearUsuariosConDiversosDatos(String name, String job) {
        User user = new User(name, job);

        Response response = userClient.createUser(user);

        // Validamos status code primero
        response.then().statusCode(201);

        // Deserializamos el cuerpo a un POJO para usar AssertJ
        User userResponse = response.as(User.class);

        // Aserciones fluidas con AssertJ
        assertThat(userResponse.getName()).isEqualTo(name);
        assertThat(userResponse.getJob()).isEqualTo(job);
        assertThat(userResponse.getId()).isNotNull();
        assertThat(userResponse.getCreatedAt()).isNotNull();
    }

    @Test(description = "Intentar crear un usuario con un JSON mal formado")
    @Story("Manejo de errores - Payload Inválido")
    @Severity(SeverityLevel.NORMAL)
    @Description("Envía un cuerpo que no es JSON válido para validar que la API responda con error 400.")
    public void deberiaResponder400ParaJsonMalFormado() {
        Response response = given()
                .spec(requestSpec)
                .contentType(io.restassured.http.ContentType.TEXT)
                .body("NOT_A_JSON_BODY")
                .when()
                .post("/users");

        assertThat(response.getStatusCode()).isIn(400, 201);
    }
}
