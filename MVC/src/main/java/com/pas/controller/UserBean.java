package com.pas.controller;

import com.pas.model.User;
import com.pas.service.RestClient;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.core.MediaType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Named
@RequestScoped
@Getter
@Setter
@Slf4j
public class UserBean {
    private final RestClient restClient = new RestClient();
    private List<User> users;
    private User user = new User();
    private int statusCode;

    public UserBean() {
    }

    public List<User> getUsers() {
        users = restClient.get("/users").readEntity(List.class);
        return users;
    }

    public void deleteUser(String id) {
        restClient.delete("/users/" + id);
    }

    public void changePassword(Principal principal, String password){
        changePassword(principal, password);
    }
}
