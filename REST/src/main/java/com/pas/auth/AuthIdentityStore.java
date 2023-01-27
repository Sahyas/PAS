package com.pas.auth;

import com.pas.model.User;
import com.pas.repository.UserRepository;
import com.pas.service.impl.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;

import java.util.*;
//@ApplicationScoped
public class AuthIdentityStore implements IdentityStore {
    @Inject
    private UserService userService;

//    @Inject
//    private UserRepository userRepository;

    @Override
    public int priority() {
        return 70;
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return EnumSet.of(ValidationType.VALIDATE);
    }

    @Override
    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
        User user = (User) userService.findAllClients().stream().filter(user1 -> user1.getLogin().equals(
                validationResult.getCallerPrincipal().getName()));
        return new HashSet<>(Collections.singleton(user.getUserType().toString()));
    }

    public CredentialValidationResult validate(UsernamePasswordCredential credential) {
        User user = userService.getByUsernameAndPasswd(credential.getCaller(),
                credential.getPasswordAsString()).get(0);
        if (user != null && user.isActive()) {
            return new CredentialValidationResult(user.getLogin(), new HashSet<>(Collections.singleton(user.getUserType().toString())));
        }
        return CredentialValidationResult.INVALID_RESULT;
    }
}
