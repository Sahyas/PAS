package com.pas.controller;

import com.pas.model.User;
import com.pas.service.impl.RentService;
import com.pas.service.impl.UserService;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@ApplicationScoped
public class UserController {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Inject
    private UserService userService;
    @Inject
    private RentService rentService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/client")
    public Response saveClient(User user) {
        Set<ConstraintViolation<User>> violation = validator.validate(user);
        List<String> errors = violation.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        if (!violation.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
        }
        user.setId(UUID.randomUUID());
        user.setActive(false);
        User createdUser = userService.addClient(user);
        if (createdUser != null) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity("This login is taken").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/match")
    public User getUserByLogin(@QueryParam("login") String login) {
        return userService.getUserByLogin(login);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/matchAll")
    public List<User> getUsersMatchingLogin(@QueryParam("login") String login) {
        return userService.getUsersMatchingLogin(login);
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
    public Response deleteClient(@PathParam("userId") UUID userId) {
        if (rentService.getRentByClient(getClient(userId)) == null) {
            userService.deleteClient(userService.getClientById(userId));
            return Response.ok().entity("User with id: " + userId + "successfully deleted").build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity("This user have active rents").build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User updateClient(User user) {
        return userService.updateClient(user);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/activity/{userId}")
    public User changeActivity(@PathParam("userId") UUID userId) {
        User user = userService.getClientById(userId);
        user.setActive(!user.isActive());
        return userService.updateClient(user);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/moderator")
    public Response saveModerator(User user) {
        Set<ConstraintViolation<User>> violation = validator.validate(user);
        List<String> errors = violation.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        if (!violation.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
        }
        user.setId(UUID.randomUUID());
        user.setActive(false);
        User createdUser = userService.addModerator(user);
        if (createdUser != null) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity("This login is taken").build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/admin")
    public Response saveAdmin(User user) {
        Set<ConstraintViolation<User>> violation = validator.validate(user);
        List<String> errors = violation.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        if (!violation.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
        }
        user.setId(UUID.randomUUID());
        user.setActive(false);
        User createdUser = userService.addAdmin(user);
        if (createdUser != null) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity("This login is taken").build();
        }
    }
}
