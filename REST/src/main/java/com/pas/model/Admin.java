package com.pas.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.util.UUID;

public class Admin extends User {

    public Admin(UUID id, String login, String password, String firstName, String lastName,
                 String personalId, float debt, int age, boolean isActive) {

        super(id, Roles.ADMIN, login, password, firstName, lastName, personalId, debt, age, isActive);

    }
}
