package com.pas.auth;

import com.pas.model.User;
import com.pas.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;

import java.util.*;

public class AuthIdentityStore implements IdentityStore {

    @Inject
    private UserRepository userRepository;

    @Context
    private SecurityContext securityContext;

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
        User user = new ArrayList<>(userRepository.findAll().stream().filter(user1 -> user1.getLogin().equals(
                validationResult.getCallerPrincipal().getName())).toList()).get(0);
        return new HashSet<>(Collections.singleton(user.getUserType().toString()));
    }

    public CredentialValidationResult validate(UsernamePasswordCredential credential) {
        User user = userRepository.getByUsernameAndPasswd(credential.getCaller(),
                credential.getPasswordAsString());
        if (user != null && user.isActive()) {
            return new CredentialValidationResult(user.getLogin(), new HashSet<>(Collections.singleton(user.getUserType().toString())));
        }
        return CredentialValidationResult.INVALID_RESULT;
    }
}
