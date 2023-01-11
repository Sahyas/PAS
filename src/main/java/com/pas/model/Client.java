package com.pas.model;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {

    private UUID id;

    private String firstName;

    private String lastName;

    private String personalId;

    private float debt;

    private int age;

    private ClientType clientType;

    public Client(UUID id, String firstName, String lastName, String personalId, float debt, int age, ClientType clientType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.debt = debt;
        this.age = age;
        this.clientType = clientType;
    }
}
