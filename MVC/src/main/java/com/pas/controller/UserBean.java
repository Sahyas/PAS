package com.pas.controller;

import com.pas.model.User;
import com.pas.service.RestClient;

import java.util.Collections;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Named
@ApplicationScoped
@Getter
@Setter
@Slf4j
public class UserBean {
    private final RestClient restClient = new RestClient();
    private List<User> users;
    private User user = new User();
    private int statusCode;
    private String filter;
    private boolean filterFlag;

    public UserBean() {
    }

    public List<User> getUsers() {
        return users;
    }

    @PostConstruct
    public void initUsers() {
        users = restClient.get("/users").readEntity(List.class);
    }

    public void deleteUser(String id) {
        restClient.delete("/users/" + id);
    }

    public void addUser(User user) {
        statusCode = restClient.post("/users/client", user, Collections.EMPTY_MAP, MediaType.APPLICATION_JSON_TYPE).getStatus();
    }

    public void changeActivity(String id) {
        statusCode = restClient.patch("/users/activity/" + id).getStatus();
    }

    public String editUser(User user) {
        statusCode = restClient.put("/users", user).getStatus();
        this.user = new User();
        return "usersPage";
    }

    public String editUserSetter(String id) {
        this.user = restClient.get("/users/" + id).readEntity(User.class);
        return "userEdit";
    }

    public void filterUsers() {
        filterFlag = true;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        target = target.queryParam("login", filter);
        log.error(target.path("/users/matchAll").request().get().toString());
        this.users = target.path("/users/matchAll").request().get().readEntity(List.class);
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}

