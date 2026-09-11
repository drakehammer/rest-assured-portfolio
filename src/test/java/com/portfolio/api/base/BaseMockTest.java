package com.portfolio.api.base;

import com.portfolio.api.clients.UserClient;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 * Clase base para pruebas que utilizan WireMock para simular la API.
 */
public class BaseMockTest extends BaseTest {

    protected static WireMockServer wireMockServer;

    @BeforeAll
    public static void startServer() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8080));
        wireMockServer.start();
    }

    @AfterAll
    public static void stopServer() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }

    @Override
    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080")
                .setContentType(ContentType.JSON)
                .addFilter(new io.qameta.allure.restassured.AllureRestAssured())
                .addFilter(new ConditionalLoggingFilter())
                .build();

        userClient = new UserClient(requestSpec);
    }
}
