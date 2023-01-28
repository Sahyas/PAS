package com.pas.dto;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthDto {
    String login;
    String password;

    @JsonbCreator
    public AuthDto(@JsonbProperty("login")String login, @JsonbProperty("password")String password) {
        this.login = login;
        this.password = password;
    }

    public AuthDto() {
    }
}
