package com.pas.model;

import java.util.UUID;

public class Adult extends Client {
    public Adult(UUID id, String firstName, String lastName, String personalId, float debt, int age) {
        super(id, firstName, lastName, personalId, debt, age);
    }
}
