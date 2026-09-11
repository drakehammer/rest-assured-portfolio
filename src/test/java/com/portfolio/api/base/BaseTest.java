package com.portfolio.api.base;

import com.portfolio.api.clients.UserClient;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;

/**
 * Clase base para todas las pruebas de API.
 * Centraliza la configuración de REST Assured (URL base, specs de
 * request/response, logging y el listener de Allure para reportes).
 */
public class BaseTest {

    protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;
    protected ResponseSpecification successResponseSpec;
    protected ResponseSpecification createdResponseSpec;
    protected ResponseSpecification noContentResponseSpec;
    protected UserClient userClient;

    @BeforeEach
    public void setUp() {
        // La URL base se carga dinámicamente desde el archivo config.properties
        String baseUrl = ConfigReader.getProperty("base.url");

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .addFilter(new ConditionalLoggingFilter())
                .build();

        responseSpec = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();

        successResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        createdResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        noContentResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(204)
                .log(LogDetail.ALL)
                .build();

        userClient = new UserClient(requestSpec);
    }
}
