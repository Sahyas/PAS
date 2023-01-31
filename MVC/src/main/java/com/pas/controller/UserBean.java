package com.pas.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pas.model.User;
import com.pas.service.RestClient;

import java.io.IOException;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.core.MediaType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;

@Named
@RequestScoped
@Getter
@Setter
@Slf4j
public class UserBean {

    @Inject
    private MvcJwt mvcJwt;
    private final RestClient restClient = new RestClient();
    private org.json.JSONArray users;
    private User user = new User();
    private int statusCode;
    private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    public UserBean() {
    }

    public JSONArray getUsers() {
        org.json.JSONArray arr = getAll();
        if (arr != null) {
            this.users = arr;
        }
//        users = restClient.get("/users").readEntity(List.class);
        return users;
    }

    public org.json.JSONArray getAll() {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            RequestBuilder requestBuilder = RequestBuilder.get()
                    .setUri("https://localhost:8181/users");
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


    public void deleteUser(String id) {
//        restClient.delete("/users/" + id);

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpDelete httpDelete = new HttpDelete("https://localhost:8181/users/" + id);
            if (!mvcJwt.getJwt().equals("")) {
                httpDelete.setHeader("Authorization", "Bearer " + mvcJwt.getJwt());
            }

            HttpResponse response = httpclient.execute(httpDelete);
            statusCode = response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeActivity(String id) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            RequestBuilder requestBuilder = RequestBuilder.patch()
                    .setUri("https://localhost:8181/users/activity/" + id);
            if (!mvcJwt.getJwt().equals("")) {
                requestBuilder.setHeader("Authorization", "Bearer " + mvcJwt.getJwt());
            }
            HttpUriRequest request = requestBuilder.build();

            HttpResponse response =  httpclient.execute(request);
            statusCode = response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void createClient(User user) throws JsonProcessingException {
        String clientJSON = objectMapper.writeValueAsString(user);

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            RequestBuilder requestBuilder = RequestBuilder.post()
                    .setUri("https://localhost:8181/users/client")
                    .setHeader(HttpHeaders.CONTENT_TYPE, String.valueOf(MediaType.valueOf(MediaType.APPLICATION_JSON)))
                    .setEntity(new StringEntity(clientJSON));
            if (!mvcJwt.getJwt().equals("")) {
                requestBuilder.setHeader("Authorization", "Bearer " + mvcJwt.getJwt());
            }
            HttpUriRequest request = requestBuilder.build();
            HttpResponse response =  httpclient.execute(request);
            statusCode = response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
