package com.pas.model;

import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Valid
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Book {

    private UUID id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String author;
    @NotEmpty
    private String serialNumber;
    @NotEmpty
    private String genre;

    private boolean isRented;

    public Book(String title, String author, String serialNumber, String genre) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.serialNumber = serialNumber;
        this.genre = genre;
        this.isRented = false;
    }
}
