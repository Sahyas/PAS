package com.pas.controller;

import com.pas.model.Rent;
import com.pas.service.RestClient;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class RentBean {
    private List<Rent> rents;
    private RestClient restClient = new RestClient();

    public RentBean() {
    }

    public List<Rent> getRents() {
        this.rents = restClient.get("/rents").readEntity(List.class);
        return rents;
    }

    public void delete(String id) {
        restClient.delete("/rents/" + id);
    }
}
