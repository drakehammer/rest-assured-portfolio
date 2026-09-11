package com.portfolio.api.base;

import com.portfolio.api.clients.UserClient;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

/**
 * Clase base para todas las pruebas de API.
 * Centraliza la configuración de REST Assured (URL base, specs de
 * request/response, logging y el listener de Allure para reportes).
 */
public class BaseTest {

    protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;
    protected UserClient userClient;

    @BeforeClass
    public void setUp() {
        // La URL base se carga dinámicamente desde el archivo config.properties
        String baseUrl = ConfigReader.getProperty("base.url");

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();

        userClient = new UserClient(requestSpec);
    }
}
