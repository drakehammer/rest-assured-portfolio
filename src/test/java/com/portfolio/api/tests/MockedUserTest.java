package com.portfolio.api.tests;

import com.portfolio.api.base.BaseMockTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("API Testing Portfolio")
@Feature("Usuarios - Aislamiento (Mocking)")
public class MockedUserTest extends BaseMockTest {

    @Test
    @Story("Manejo de Errores del Servidor")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Simula un error 500 Internal Server Error y valida que el framework lo capture.")
    public void deberiaManejarError500CuandoLaApiFalla() {
        // Stubbing: Cuando GET /users/1 -> responde 500
        wireMockServer.stubFor(get(urlEqualTo("/users/1"))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"error\": \"Internal Server Error\"}")));

        Response response = userClient.getUserById(1);

        assertThat(response.getStatusCode()).isEqualTo(500);
        assertThat(response.jsonPath().getString("error")).isEqualTo("Internal Server Error");
    }

    @Test
    @Story("Manejo de Latencia")
    @Severity(SeverityLevel.NORMAL)
    @Description("Simula un retraso en la respuesta para validar el comportamiento de timeout.")
    public void deberiaManejarTimeoutCuandoLaApiTarda() {
        // Stubbing: Cuando GET /users/1 -> tarda 3 segundos y responde 200
        wireMockServer.stubFor(get(urlEqualTo("/users/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withFixedDelay(3000)));

        Response response = userClient.getUserById(1);
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Test
    @Story("Manejo de Datos Corruptos")
    @Severity(SeverityLevel.NORMAL)
    @Description("Simula una respuesta con JSON mal formado para validar la resiliencia del framework.")
    public void deberiaManejarJsonCorrupto() {
        // Stubbing: Cuando GET /users/1 -> responde 200 pero con un cuerpo corrupto
        wireMockServer.stubFor(get(urlEqualTo("/users/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"name\": \"Diego\", \"job\": ")));

        Response response = userClient.getUserById(1);

        assertThat(response.getStatusCode()).isEqualTo(200);
    }
}
