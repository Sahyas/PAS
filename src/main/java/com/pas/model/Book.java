package com.pas.model;

import java.util.UUID;

import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Book {

    private UUID id;

    private String title;

    private String author;

    private String serialNumber;

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
