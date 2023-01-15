package com.pas.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Valid
public class Rent {

    private UUID id;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;
    @NotEmpty
    private Book book;
    @NotEmpty
    private User user;

    public Rent(UUID id, LocalDateTime beginTime, LocalDateTime endTime, Book book, User user) {
        this.id = id;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.book = book;
        this.user = user;
    }
}
