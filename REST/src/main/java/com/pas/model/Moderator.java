package com.pas.model;

import java.util.UUID;

public class Moderator extends User {
    public Moderator(UUID id, String login, String password, String firstName, String lastName,
                     String personalId, float debt, int age, boolean isActive) {
        super(id, login, password, firstName, lastName, personalId, debt, age, isActive);
    }
}
