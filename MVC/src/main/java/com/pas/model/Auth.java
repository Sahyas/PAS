package com.pas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Auth {
    @JsonProperty
    String login;
    @JsonProperty
    String password;


    public Auth(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Auth() {
    }
}
