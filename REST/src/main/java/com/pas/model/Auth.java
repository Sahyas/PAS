package com.pas.model;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Auth {
    String login;
    String password;

    @JsonbCreator
    public Auth(@JsonbProperty("login")String login, @JsonbProperty("password")String password) {
        this.login = login;
        this.password = password;
    }

    public Auth() {
    }
}
