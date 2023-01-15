package com.pas.service.impl;

import com.pas.model.Admin;
import com.pas.model.Client;
import com.pas.model.Moderator;
import com.pas.model.User;
import com.pas.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public User addModerator(User user) {
        userRepository.add(new Moderator(UUID.randomUUID(), user.getLogin(), user.getPassword(), user.getFirstName(),
                user.getLastName(), user.getPersonalId(), user.getDebt(), user.getAge(), user.isActive()));
        return user;
    }

    public User addAdmin(User user) {
        userRepository.add(new Admin(UUID.randomUUID(), user.getLogin(), user.getPassword(), user.getFirstName(),
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

    public User getUserByLogin(String login) {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }

    public List<User> getUsersMatchingLogin(String login) {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getLogin().contains(login))
                .collect(Collectors.toList());
    }
}
