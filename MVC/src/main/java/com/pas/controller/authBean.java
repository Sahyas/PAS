package com.pas.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.pas.model.Auth;
import com.pas.service.RestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpClient.*;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;


@ApplicationScoped
@Named
@Getter
@Setter
public class authBean {
    @Inject
    private MvcJwt MvcJwt;
    @Inject
    private HttpServletRequest request;

    @Inject
    RestClient restClient;

    private String username;
    private String password;
    private String loginURL = "/auth/login";


    public String login() {
        String jwt;

        Auth auth = new Auth(username, password);
        String xd = null;
        try {
            xd = new ObjectMapper().writeValueAsString(auth);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        jwt = restClient.post(loginURL, xd, Collections.EMPTY_MAP, MediaType.valueOf(MediaType.APPLICATION_JSON)).readEntity(String.class);
        MvcJwt.setJwt(jwt);
        return "users";
    }



    public String logout() throws ServletException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
    }
}
