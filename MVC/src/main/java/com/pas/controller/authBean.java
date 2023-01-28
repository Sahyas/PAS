package com.pas.controller;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;


@ApplicationScoped
@Named
public class authBean {

    private String username;
    private String password;
    private String loginURL = "http://localhost:8080/MVC-1.0-SNAPSHOT/faces/login";

}
