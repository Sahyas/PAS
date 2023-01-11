package com.pas.repository;

import com.pas.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientRepository implements RepositoryInterface<Client> {

    private List<Client> clients;

    public ClientRepository() {
        this.clients = new ArrayList<>();
    }

    @Override
    public void add(Client entity) {
        clients.add(entity);
    }

    @Override
    public Client getById(UUID id) {
        return clients.stream()
                .filter(client -> client.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(Client entity) {
        clients.remove(entity);
    }

    @Override
    public void update(Client entity) {
        clients.add(entity);
    }

    @Override
    public long size() {
        return clients.size();
    }

    @Override
    public List<Client> findAll() {
        return clients;
    }
}
