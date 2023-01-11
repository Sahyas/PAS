package com.pas.model;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {

    private UUID id;

    private String title;

    private String author;

    private String serialNumber;

    private String genre;

    private boolean isRented;

    public Book(UUID id, String title, String author, String serialNumber, String genre, boolean isRented) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.serialNumber = serialNumber;
        this.genre = genre;
        this.isRented = isRented;
    }
}
