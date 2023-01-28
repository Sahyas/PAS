package com.pas.service;

import java.util.Map;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RequestScoped
public class RestClient {

    private Client client;

    private WebTarget target;

    public RestClient() {
        client = ClientBuilder.newClient();
        target = client.target("https://localhost:8181");
    }

    public Response get(String path) {
        return target.path(path).request().get();
    }

    public Response post(String path, Object body, Map<String, String> queryParams, MediaType mediaType) {
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            target = target.queryParam(entry.getKey(), entry.getValue());
        }
        Response response = target.path(path).request().post(Entity.entity(body, mediaType));
        target = client.target("https://localhost:8181");
        return response;
    }

    public Response put(String path, Object body) {
        return target.path(path).request().put(Entity.entity(body, MediaType.APPLICATION_JSON));
    }

    public Response delete(String path) {
        return target.path(path).request().delete();
    }
}
