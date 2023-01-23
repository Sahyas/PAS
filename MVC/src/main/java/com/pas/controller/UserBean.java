package com.pas.controller;

import com.pas.model.User;
import com.pas.service.RestClient;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class UserBean {
    private final RestClient restClient = new RestClient();
    private List<User> users;

    public UserBean() {
    }

    public List<User> getUsers() {
        users = restClient.get("/users").readEntity(List.class);
        return users;
    }

    public void deleteUser(String id) {
        restClient.delete("/users/" + id);
    }
}
