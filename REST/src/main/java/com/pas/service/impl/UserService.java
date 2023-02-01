package com.pas.service.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.pas.auth.JWS;
import com.pas.model.Admin;
import com.pas.model.Client;
import com.pas.model.Moderator;
import com.pas.model.User;
import com.pas.repository.RentRepository;
import com.pas.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jdk.jshell.spi.ExecutionControl;

@ApplicationScoped
public class UserService {
    @Inject
    private UserRepository userRepository;
    @Inject
    private RentRepository rentRepository;
    @Inject
    private SecurityContext securityContext;
    private final Logger log = Logger.getLogger(getClass().getName());
    private JWS jwsGen = new JWS();
    public User getClientById(UUID id) {
        return userRepository.getById(id);
    }

    public User addClient(User user) {
        if (getUserByLogin(user.getLogin()) == null) {
            userRepository.add(new Client(UUID.randomUUID(), user.getLogin(), user.getPassword(), user.getFirstName(),
                    user.getLastName(), user.getPersonalId(), user.getDebt(), user.getAge(), user.isActive()));
            return user;
        }
        return null;
    }

    public User addModerator(User user) {
        if (getUserByLogin(user.getLogin()) == null) {
            userRepository.add(new Moderator(UUID.randomUUID(), user.getLogin(), user.getPassword(), user.getFirstName(),
                    user.getLastName(), user.getPersonalId(), user.getDebt(), user.getAge(), user.isActive()));
            return user;
        }
        return null;
    }

    public User addAdmin(User user) {
        if (getUserByLogin(user.getLogin()) == null) {
            userRepository.add(new Admin(UUID.randomUUID(), user.getLogin(), user.getPassword(), user.getFirstName(),
                    user.getLastName(), user.getPersonalId(), user.getDebt(), user.getAge(), user.isActive()));
            return user;
        }
        return null;
    }

    public void deleteClient(User user) {
        userRepository.delete(user);
    }

    public List<User> findAllClients() {
        return userRepository.findAll();
    }

    public User updateClient(User user) {
        return userRepository.update(user);}
    public void changeClient(User user, String jws) {
        try {
            if (this.jwsGen.verify(jws)) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("login", user.getLogin());
                String newJwt = this.jwsGen.generateJws(jsonObject.toString());
                if(newJwt.equals(jws)) {
                    User oldUser = userRepository.getByLogin(user.getLogin());
                    oldUser.setFirstName(user.getFirstName());
                    oldUser.setLastName(user.getLastName());
                    oldUser.setAge(user.getAge());
                    oldUser.setPersonalId(user.getPersonalId());
                    userRepository.update(oldUser);
                }
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Client does not exist");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    public void changePassword(String newPassword, String oldPassword){
        String username = securityContext.getCallerPrincipal().getName();
        if (username == null ) {
            throw new IllegalArgumentException("Old password is wrong.");
        }
        try {
            User user = userRepository.getByLogin(username);
            if (!user.getPassword().equals(oldPassword)) {
                throw new IllegalArgumentException("Old password is wrong.");
            }
            user.setPassword(newPassword);
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Client does not exist");
        }
    }
}
