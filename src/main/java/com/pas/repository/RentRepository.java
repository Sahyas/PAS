package com.pas.repository;

import com.pas.model.Rent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RentRepository implements RepositoryInterface<Rent> {

    List<Rent> rents;

    public RentRepository() {
        this.rents = new ArrayList<>();
    }

    @Override
    public void add(Rent entity) {
        rents.add(entity);
    }

    @Override
    public Rent getById(UUID id) {
        return rents.stream()
                .filter(rent -> rent.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(Rent entity) {
        rents.remove(entity);
    }

    @Override
    public void update(Rent entity) {
        rents.add(entity);
    }

    @Override
    public long size() {
        return rents.size();
    }

    @Override
    public List<Rent> findAll() {
        return rents;
    }
}
