package com.pas.controller;

import com.pas.auth.JWT;
import jakarta.annotation.security.RolesAllowed;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;


public class AuthController {
//    private final JWT jwtGenerator = new JWT();
//
//    @POST
//    @Path("/login")
//    @RolesAllowed("GUEST")
//    public Response login(HttpServletRequest request) {
//        try {
//            UsernamePasswordCredential usernamePasswordCredential = new UsernamePasswordCredential(request.getParameter("login"), request.getParameter("password"));
//            CredentialValidationResult credentialValidationResult = ;
//
//                String jwt = jwtGenerator.generateJWT(request.getParameter("login"), credentialValidationResult.getCallerGroups().iterator().next());
//                return Response.ok().entity(jwt).build();
//
//        } catch (IndexOutOfBoundsException e) {
//            return Response.status(Response.Status.UNAUTHORIZED).build();
//        }
//        return Response.status(Response.Status.BAD_REQUEST).build();
//    }
}
