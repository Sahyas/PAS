package com.pas.controller;

import com.pas.auth.AuthIdentityStore;
import com.pas.auth.JWT;
import com.pas.model.Auth;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Path("/auth")
@RequestScoped
public class AuthController {
    @Context
    private SecurityContext securityContext;

    @Inject
    private AuthIdentityStore authIdentityStore;
    private final JWT jwtGenerator = new JWT();

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("GUEST")
    public Response login(Auth auth) {
        try {
            UsernamePasswordCredential usernamePasswordCredential = new UsernamePasswordCredential(auth.getLogin(), auth.getPassword());
            CredentialValidationResult credentialValidationResult = authIdentityStore.validate(usernamePasswordCredential);

                String jwt = jwtGenerator.generateJWT(auth.getLogin(), credentialValidationResult.getCallerGroups().iterator().next());
                return Response.ok().entity(jwt).build();

        } catch (IndexOutOfBoundsException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
