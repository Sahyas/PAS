package com.pas.controller;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.pas.model.Rent;
import com.pas.service.RestClient;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.core.MediaType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;

@Named
@ApplicationScoped
@Getter
@Setter
@Slf4j
public class RentBean {

    @Inject
    private MvcJwt mvcJwt;
    private RestClient restClient = new RestClient();
    private Rent rent = new Rent();
    private int statusCode;
    private String userId;
    private String bookId;
    private org.json.JSONArray rents;
    public RentBean() {
    }
//    public List<Rent> getRents() {
//        this.rents = restClient.get("/rents").readEntity(List.class);
//        return rents;
//    }
//

    public JSONArray getRents() {
        org.json.JSONArray arr = getAllRents();
        if (arr != null) {
            this.rents = arr;
        }
//        users = restClient.get("/users").readEntity(List.class);
        return rents;
    }

    public org.json.JSONArray getAllRents() {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            RequestBuilder requestBuilder = RequestBuilder.get()
                    .setUri("https://localhost:8181/rents");
            if (!mvcJwt.getJwt().equals("")) {
                requestBuilder.setHeader("Authorization", "Bearer " + mvcJwt.getJwt());
            }
            HttpUriRequest request = requestBuilder.build();
            HttpResponse response = httpclient.execute(request);
            String responseString = new BasicResponseHandler().handleResponse(response);
            return new org.json.JSONArray(responseString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public void endRent(String id) {
//        log.error("/rents/return/" + id);
//        restClient.post("/rents/return/" + id, "", Collections.EMPTY_MAP, MediaType.valueOf(MediaType.TEXT_PLAIN));
//    }

    public void addRent() {
//        Map<String, String> queryParams = new HashMap<>();
//        queryParams.put("clientId", userId);
//        queryParams.put("bookId", bookId);
        JSONObject object = new JSONObject();
        object.put("clientId", userId);
        object.put("bookId", bookId);
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            RequestBuilder requestBuilder = RequestBuilder.post()
                    .setUri("https://localhost:8181/rents")
                    .setHeader(HttpHeaders.CONTENT_TYPE, String.valueOf(MediaType.valueOf(MediaType.APPLICATION_JSON)))
                    .setEntity(new StringEntity(object.toString()));
            if (!mvcJwt.getJwt().equals("")) {
                requestBuilder.setHeader("Authorization", "Bearer " + mvcJwt.getJwt());
            }
            HttpUriRequest request = requestBuilder.build();
            HttpResponse response = httpclient.execute(request);
            statusCode = response.getStatusLine().getStatusCode();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getStatusCode() {
        return statusCode;
    }
}
