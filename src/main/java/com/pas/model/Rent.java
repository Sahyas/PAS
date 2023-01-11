package com.pas.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rent {

    private UUID id;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;

    private Book book;

    private Client client;

    public Rent(UUID id, LocalDateTime beginTime, LocalDateTime endTime, Book book, Client client) {
        this.id = id;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.book = book;
        this.client = client;
    }
}
