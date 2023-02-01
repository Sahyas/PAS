package com.pas.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.pas.model.NewPassword;
import com.pas.service.RestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.core.MediaType;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.MethodOrderer;
import java.io.IOException;
import java.util.logging.Logger;

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

    private String changePasswordURL = "https://localhost:8181/users/changePassword";
    private static final Logger LOGGER = Logger.getLogger(MethodOrderer.MethodName.class.getName());
    public String changePassword() throws Exception {
        if (newPassword.equals(newPasswordRepeat)){

            NewPassword password = new NewPassword(newPassword, oldPassword);
            JSONObject object = new JSONObject();
            object.put("oldPassword", oldPassword);
            object.put("newPassword", newPassword);
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                RequestBuilder requestBuilder = RequestBuilder.patch()
                        .setUri(changePasswordURL)
                        .setHeader(HttpHeaders.CONTENT_TYPE, String.valueOf(MediaType.valueOf(MediaType.APPLICATION_JSON)))
                        .setEntity(new StringEntity(object.toString()));
                if (!mvcJwt.getJwt().equals("")) {
                    requestBuilder.setHeader("Authorization", "Bearer " + mvcJwt.getJwt());
                }
                HttpUriRequest request = requestBuilder.build();
                HttpResponse response = httpClient.execute(request);
                int statusCode = response.getStatusLine().getStatusCode();
                if(statusCode == 400) {
                    throw new Exception("Old password is wrong");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
       }
        return "navigation";
   }
}
