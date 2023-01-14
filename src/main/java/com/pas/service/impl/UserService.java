package com.pas.service.impl;

import com.pas.model.Client;
import com.pas.model.User;
import com.pas.repository.UserRepository;

import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
@ApplicationScoped
public class UserService {
    @Inject
    private UserRepository userRepository;

    public User getClientById(UUID id) {
        return userRepository.getById(id);
    }

    public User addClient(User user) {
        userRepository.add(new Client(UUID.randomUUID(), user.getLogin(), user.getPassword(), user.getFirstName(),
                user.getLastName(), user.getPersonalId(), user.getDebt(), user.getAge(), user.isActive()));
        return user;
    }

    public void deleteClient(User user) {
        userRepository.delete(user);
    }

    public List<User> findAllClients() {
        return userRepository.findAll();
    }

    public User updateClient(User user) {
        return userRepository.update(user);
    }
}
