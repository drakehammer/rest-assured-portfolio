package com.portfolio.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Representa el payload de usuario usado en las pruebas de creación
 * y actualización contra el endpoint /users de ReqRes.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String name;
    private String job;

    public User() {
    }

    public User(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
