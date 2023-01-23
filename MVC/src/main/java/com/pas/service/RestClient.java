package com.pas.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class RestClient {

    private Client client;

    private WebTarget target;

    public RestClient() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080");
    }

    public Response get(String path) {
        return target.path(path).request().get();
    }

    public Response post(String path, Object body) {
        return target.path(path).request().post(Entity.entity(body, MediaType.APPLICATION_JSON));
    }

    public Response put(String path, Object body) {
        return target.path(path).request().put(Entity.entity(body, MediaType.APPLICATION_JSON));
    }

    public Response delete(String path) {
        return target.path(path).request().delete();
    }
}
