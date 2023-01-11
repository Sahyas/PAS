package com.pas.service;

import com.pas.model.Adult;
import com.pas.model.Child;
import com.pas.model.Client;
import com.pas.model.ClientType;
import com.pas.repository.ClientRepository;

import java.util.List;
import java.util.UUID;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getClientById(UUID id) {
        return clientRepository.getById(id);
    }

    public Client addClient(String firstName, String lastName, String personalId, float debt, int age, ClientType clientType) {
        if (age < 18) {
            Client child = new Child(UUID.randomUUID(), firstName, lastName, personalId, debt, age, clientType);
            clientRepository.add(child);
            return child;
        }
        if (age > 18) {
            Client adult = new Adult(UUID.randomUUID(), firstName, lastName, personalId, debt, age, clientType);
            clientRepository.add(adult);
            return adult;
        }
        return null;
    }

    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }
}
