package com.pas.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.pas.model.NewPassword;
import com.pas.service.RestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

@ApplicationScoped
@Named
@Getter
@Setter
public class passwordBean {
    @Inject
    private MvcJwt mvcJwt;
    @Inject
    RestClient restClient;
    private String newPassword;
    private String newPasswordRepeat;
    private String oldPassword;

    private String changePasswordURL = "/users/changePassword";

    public void changePassword() {
        if (newPassword.equals(newPasswordRepeat)) {

            NewPassword password = new NewPassword(newPassword, oldPassword);
            String body;
            try {
                body = new ObjectMapper().writeValueAsString(password);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            restClient.put(changePasswordURL, body);

//            JSONObject object = new JSONObject();
//            object.put("oldPassword", oldPassword);
//            object.put("newPassword", newPassword);
//            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
//                RequestBuilder requestBuilder = RequestBuilder.patch()
//                        .setUri(changePasswordURL)
//                        .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
//                        .setEntity(new StringEntity(object.toString()));
//                if (!mvcJwt.getJwt().equals("")) {
//                    requestBuilder.setHeader("Authorization", "Bearer " + mvcJwt.getJwt());
//                }
//                HttpUriRequest request = requestBuilder.build();
//                HttpResponse response = httpClient.execute(request);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
       }
   }
}
