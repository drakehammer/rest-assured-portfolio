package com.portfolio.api.clients;

import com.portfolio.api.models.User;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/**
 * UserClient encapsula todas las llamadas HTTP relacionadas con el recurso /users.
 * Esto separa la infraestructura de comunicación (REST Assured) de la lógica de los tests.
 */
public class UserClient {

    private final RequestSpecification requestSpec;

    public UserClient(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    public Response getUsers(int page) {
        return given()
                .spec(requestSpec)
                .queryParam("page", page)
                .when()
                .get("/users");
    }

    public Response getUserById(int id) {
        return given()
                .spec(requestSpec)
                .pathParam("id", id)
                .when()
                .get("/users/{id}");
    }

    public Response createUser(User user) {
        return given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/users");
    }

    public Response updateUser(int id, User user) {
        return given()
                .spec(requestSpec)
                .pathParam("id", id)
                .body(user)
                .when()
                .put("/users/{id}");
    }

    public Response deleteUser(int id) {
        return given()
                .spec(requestSpec)
                .pathParam("id", id)
                .when()
                .delete("/users/{id}");
    }
}
