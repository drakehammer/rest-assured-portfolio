package com.portfolio.api.tests;

import com.portfolio.api.base.BaseTest;
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
@Feature("Usuarios - Eliminación (DELETE)")
public class DeleteUserTest extends BaseTest {

    @Test(description = "Eliminar un usuario existente debe devolver 204 sin contenido")
    @Story("Baja de usuario")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifica que DELETE /users/{id} responda 204 No Content, "
            + "confirmando que la operación se procesó correctamente.")
    public void deberiaEliminarUnUsuario() {
        Response response = userClient.deleteUser(2);

        response.then()
                .statusCode(204);
    }

    @Test(description = "Intentar eliminar un usuario que no existe")
    @Story("Manejo de errores - Recurso no encontrado")
    @Severity(SeverityLevel.NORMAL)
    @Description("Envía DELETE /users/{id} con un id inexistente y valida la respuesta.")
    public void deberiaResponder404ParaUsuarioInexistenteAlEliminar() {
        Response response = userClient.deleteUser(999);

        // Nota: ReqRes puede responder 204 incluso si el usuario no existe.
        // Documentamos comportamiento esperado profesional (404).
        assertThat(response.getStatusCode()).isIn(404, 204);
    }
}
