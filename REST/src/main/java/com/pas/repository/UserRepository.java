package com.pas.repository;

import com.pas.model.Client;
import com.pas.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class UserRepository implements RepositoryInterface<User> {

    private List<User> users = Collections.synchronizedList(new ArrayList<>());

    @Override
    public synchronized void add(User entity) {
        users.add(entity);
    }

    @Override
    public User getById(UUID id) {
        return users.stream()
                .filter(client -> client.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    public User getByLogin(String login) {
        return users.stream()
                .filter(client -> client.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }
    @Override
    public synchronized void delete(User entity) {
        users.remove(entity);
    }

    @Override
    public synchronized User update(User entity) {
        User foundUser = users.stream()
                .filter(client -> client.getId().equals(entity.getId()))
                .findFirst()
                .orElse(null);
        if (foundUser != null) {
            int index = users.indexOf(foundUser);
            users.set(index, entity);
            return entity;
        } else {
            log.warn("object " + entity + " do not exist");
            return null;
        }
    }

    @Override
    public long size() {
        return users.size();
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @PostConstruct
    public void init() {
        User client = new Client(UUID.randomUUID(), "login", "password", "name",
                "lastName", "1", 4, 12, true);
        User client2 = new Client(UUID.randomUUID(), "login1", "password1", "name1",
                "lastName1", "11", 41, 121, true);
        User client3 = new Client(UUID.randomUUID(), "login2", "password2", "name2",
                "lastName2", "12", 42, 122, true);

        this.add(client);
        this.add(client2);
        this.add(client3);
    }


}
