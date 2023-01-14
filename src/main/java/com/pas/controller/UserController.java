package com.pas.controller;

import com.pas.model.User;
import com.pas.service.impl.UserService;

import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/users")
@ApplicationScoped
public class UserController {
    @Inject
    private UserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User saveClient(User user) {
        user.setId(UUID.randomUUID());
        userService.addClient(user);
        return user;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/{userId}")
    public User getClient(@PathParam("userId") UUID userId) {
        return userService.getClientById(userId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getClients() {
        return userService.findAllClients();
    }

    @DELETE
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/{userId}")
    public void deleteClient(@PathParam("userId") UUID userId) {
        userService.deleteClient(userService.getClientById(userId));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User updateClient(User user) {
        return userService.updateClient(user);
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/activity/{userId}")
    public User changeActivity(@PathParam("userId") UUID userId) {
        User user = userService.getClientById(userId);
        user.setActive(!user.isActive());
        return userService.updateClient(user);
    }
}
