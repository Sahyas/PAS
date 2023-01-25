package com.pas.repository;

import java.util.List;
import java.util.UUID;

public interface RepositoryInterface<T> {
    void add(T entity);

    T getById(UUID id);

    void delete(T entity);

    T update(T entity);

    long size();

    List<T> findAll();
}
