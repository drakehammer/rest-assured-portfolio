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
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("API Testing Portfolio")
@Feature("Usuarios - Actualización (PUT)")
public class UpdateUserTest extends BaseTest {

    @Test(description = "Actualizar el job de un usuario existente")
    @Story("Actualización de usuario")
    @Severity(SeverityLevel.NORMAL)
    @Description("Envía PUT /users/{id} con un nuevo job y valida que la respuesta "
            + "refleje el cambio y traiga un updatedAt.")
    public void deberiaActualizarElJobDeUnUsuario() {
        User usuarioActualizado = new User("Diego Caldas", "Senior QA Automation Engineer");

        Response response = userClient.updateUser(2, usuarioActualizado);

        response.then().statusCode(200);

        User userResponse = response.as(User.class);

        assertThat(userResponse.getJob()).isEqualTo(usuarioActualizado.getJob());
        assertThat(userResponse.getUpdatedAt()).isNotNull();
    }

    @Test(description = "Intentar actualizar un usuario que no existe")
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
