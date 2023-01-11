package com.pas.model;

import java.util.UUID;

public class Child extends Client {
    public Child(UUID id, String firstName, String lastName, String personalId, float debt, int age) {
        super(id, firstName, lastName, personalId, debt, age);
    }
}
