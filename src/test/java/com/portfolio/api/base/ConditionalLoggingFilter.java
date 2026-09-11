package com.portfolio.api.base;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

/**
 * Filtro que registra la petición y respuesta únicamente cuando ocurre un error (Status >= 400).
 * Esto evita inundar los reportes con logs de pruebas exitosas.
 */
public class ConditionalLoggingFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        // Ejecutamos la petición llamando al siguiente paso en el contexto
        Response response = ctx.next(requestSpec, responseSpec);

        // Si el status es >= 400, registramos todo el detalle
        if (response != null && response.getStatusCode() >= 400) {
            requestSpec.log().all();
            response.then().log().all();
        }

        return response;
    }
}
