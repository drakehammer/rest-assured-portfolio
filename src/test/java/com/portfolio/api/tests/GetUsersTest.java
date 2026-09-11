package com.portfolio.api.tests;

import com.portfolio.api.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("API Testing Portfolio")
@Feature("Usuarios - Consulta (GET)")
public class GetUsersTest extends BaseTest {

    @DataProvider(name = "pageDataProvider")
    public Object[][] pageData() {
        return new Object[][]{
                {1},
                {2},
        };
    }

    @Test(dataProvider = "pageDataProvider",
          description = "Listar usuarios de diferentes páginas y validar estructura")
    @Story("Listado de usuarios - Data Driven")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifica que GET /users?page={page} responda 200, cumpla el esquema JSON "
            + "y contenga datos.")
    public void deberiaListarUsuariosDePaginas(int page) {
        Response response = userClient.getUsers(page);

        response.then()
                .spec(responseSpec)
                .statusCode(200)
                .time(lessThan(2000L), TimeUnit.MILLISECONDS)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/user-list-schema.json"));

        // Usamos AssertJ para validar los datos del cuerpo
        int actualPage = response.jsonPath().getInt("page");
        java.util.List<Object> data = response.jsonPath().getList("data");

        assertThat(actualPage).isEqualTo(page);
        assertThat(data).isNotEmpty();
    }

    @Test(description = "Obtener un usuario existente por id y validar sus datos")
    @Story("Detalle de usuario")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifica que GET /users/{id} devuelva 200 con el esquema esperado "
            + "y datos consistentes (email no nulo, avatar con formato de URL).")
    public void deberiaObtenerUnUsuarioPorId() {
        Response response = userClient.getUserById(2);

        response.then()
                .spec(responseSpec)
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/single-user-schema.json"));

        // Extraemos el objeto data para validaciones fluidas con AssertJ
        com.portfolio.api.models.User user = response.jsonPath().getObject("data", com.portfolio.api.models.User.class);

        assertThat(user.getId()).isEqualTo(2);
        assertThat(user.getEmail()).isNotNull().contains("@");
        assertThat(user.getAvatar()).startsWith("https://");
    }

    @Test(description = "Solicitar un usuario inexistente debe responder 404")
    @Story("Manejo de errores")
    @Severity(SeverityLevel.NORMAL)
    @Description("Caso negativo: un id de usuario que no existe (id=23) debe devolver 404, "
            + "no un 200 con body vacío.")
    public void deberiaResponder404ParaUsuarioInexistente() {
        Response response = userClient.getUserById(23);

        response.then()
                .statusCode(404);
    }

    private static org.hamcrest.Matcher<Long> lessThan(long value) {
        return org.hamcrest.Matchers.lessThan(value);
    }
}
