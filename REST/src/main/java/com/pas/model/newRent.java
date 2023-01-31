package com.pas.model;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
public class newRent {
    private UUID clientId;
    private UUID bookId;
    @JsonbCreator
    public newRent(@JsonbProperty("clientId")UUID clientId, @JsonbProperty("bookId")UUID bookId) {
        this.clientId = clientId;
        this.bookId = bookId;
    }

    public newRent() {
    }
}
