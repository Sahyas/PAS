package com.pas.controller;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pas.model.Book;
import com.pas.model.User;
import com.pas.service.RestClient;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
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
import org.json.JSONObject;

@Named
@ViewScoped
@Getter
@Setter
@Slf4j
public class BookBean implements Serializable {
    @Inject
    private MvcJwt mvcJwt;
    private Book book = new Book();
    private RestClient restClient = new RestClient();
    private int statusCode;
    private org.json.JSONArray books;
    private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    public BookBean() {
    }

    public JSONArray getBooks() {
        return books;
    }

    @PostConstruct
    public void updateBooks() {
        org.json.JSONArray arr = getAllBooks();
        if (arr != null) {
            this.books = arr;
        }
    }

    public org.json.JSONArray getAllBooks() {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            RequestBuilder requestBuilder = RequestBuilder.get()
                    .setUri("https://localhost:8181/books");
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

    public String deleteBook(org.json.JSONObject book) {
        String bookId = String.valueOf(book.get("id"));
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpDelete httpDelete = new HttpDelete("https://localhost:8181/books/" + bookId);
            if (!mvcJwt.getJwt().equals("")) {
                httpDelete.setHeader("Authorization", "Bearer " + mvcJwt.getJwt());
            }

            HttpResponse response = httpclient.execute(httpDelete);
            statusCode = response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "booksList";
    }

    public void addBook(Book book) throws JsonProcessingException {
        String clientJSON = objectMapper.writeValueAsString(book);

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            RequestBuilder requestBuilder = RequestBuilder.post()
                    .setUri("https://localhost:8181/books")
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

    public int getStatusCode() {
        return statusCode;
    }
}
