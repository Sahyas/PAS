package com.pas.controller;


import com.nimbusds.jose.shaded.json.JSONObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;


@ApplicationScoped
@Named
public class authBean {

    @Inject
    private HttpServletRequest request;
    private String login;
    private String password;
    private String loginURL = "http://localhost:8080/MVC-1.0-SNAPSHOT/faces/login";

    private String jwt = "";
    public String login(){
        JSONObject obj = new JSONObject();
        obj.put("login", login);
        obj.put("password", password);
        String jwt = "trzeba dokonczyc";
        return "coś";
    }

    public String logout() throws ServletException {
        request.logout();
        jwt = "";
        return "menu";
    }
}
