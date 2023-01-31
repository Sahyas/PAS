package com.pas.repository;


import com.pas.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class RentRepository implements RepositoryInterface<Rent> {

    List<Rent> rents = Collections.synchronizedList(new ArrayList<>());

    @Override
    public synchronized void add(Rent entity) {
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
    public synchronized void delete(Rent entity) {
        rents.remove(entity);
    }

    @Override
    public synchronized Rent update(Rent entity) {
        Rent foundRent = rents.stream()
                .filter(rent -> rent.getId().equals(entity.getId()))
                .findFirst()
                .orElse(null);
        if (foundRent != null) {
            int index = rents.indexOf(foundRent);
            rents.set(index, entity);
            return entity;
        } else {
            log.warn("object " + entity + " do not exist");
            return null;
        }
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
