package com.pas.controller;

import com.pas.model.Rent;
import com.pas.service.RestClient;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.core.MediaType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Named
@ApplicationScoped
@Getter
@Setter
@Slf4j
public class RentBean {
    private List<Rent> rents;
    private RestClient restClient = new RestClient();
    private Rent rent = new Rent();
    private int statusCode;
    private String userId;
    private String bookId;

    public RentBean() {
    }

    public List<Rent> getRents() {
        this.rents = restClient.get("/rents").readEntity(List.class);
        return rents;
    }

    public void endRent(String id) {
        log.error("/rents/return/" + id);
        restClient.post("/rents/return/" + id, "", Collections.EMPTY_MAP, MediaType.valueOf(MediaType.TEXT_PLAIN));
    }

    public void addRent() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("clientId", userId);
        queryParams.put("bookId", bookId);
        statusCode = restClient.post("/rents", "", queryParams, MediaType.valueOf(MediaType.TEXT_PLAIN)).getStatus();
    }

    public int getStatusCode() {
        return statusCode;
    }
}
