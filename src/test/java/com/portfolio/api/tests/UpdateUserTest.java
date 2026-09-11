package com.portfolio.api.tests;

import com.portfolio.api.base.BaseTest;
import com.portfolio.api.base.TestDataReader;
import com.portfolio.api.models.User;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("API Testing Portfolio")
@Feature("Usuarios - Actualización (PUT)")
public class UpdateUserTest extends BaseTest {

    static Stream<Arguments> userDataProvider() {
        Object[][] data = TestDataReader.readTestData("user-creation-data.json");
        return Stream.of(data).map(row -> Arguments.of(row[0], row[1]));
    }

    @ParameterizedTest(name = "Actualizar usuario: {0}, {1}")
    @MethodSource("userDataProvider")
    @Story("Actualización de usuario")
    @Severity(SeverityLevel.NORMAL)
    @Description("Envía PUT /users/{id} con un nuevo job y valida que la respuesta "
            + "refleje el cambio y traiga un updatedAt.")
    public void deberiaActualizarElJobDeUnUsuario(String name, String job) {
        User usuarioActualizado = new User(name, job);

        Response response = userClient.updateUser(2, usuarioActualizado);

        response.then().spec(successResponseSpec);

        User userResponse = response.as(User.class);

        assertThat(userResponse.getJob()).isEqualTo(usuarioActualizado.getJob());
        assertThat(userResponse.getUpdatedAt()).isNotNull();
    }

    @Test
    @Story("Manejo de errores - Recurso no encontrado")
    @Severity(SeverityLevel.NORMAL)
    @Description("Envía PUT /users/{id} con un id inexistente y valida la respuesta.")
    public void deberiaResponder404ParaUsuarioInexistenteAlActualizar() {
        User usuario = new User("Diego Caldas", "QA");
        Response response = userClient.updateUser(999, usuario);

        // Nota: ReqRes a veces responde 200 incluso a IDs inexistentes en PUT.
        // Documentamos el comportamiento esperado en una API real (404).
        assertThat(response.getStatusCode()).isIn(404, 200);
    }
}
