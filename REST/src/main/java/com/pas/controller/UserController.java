package com.pas.controller;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.pas.auth.JWS;
import com.pas.model.NewPassword;
import com.pas.model.User;
import com.pas.service.impl.RentService;
import com.pas.service.impl.UserService;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jdk.jshell.spi.ExecutionControl;

@Path("/users")
@ApplicationScoped
public class UserController {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Inject
    private SecurityContext securityContext;
    @Inject
    private UserService userService;
    @Inject
    private RentService rentService;

    @Inject
    JWS jwsGen;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Admin"})
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
    @RolesAllowed({"Admin", "Moderator", "Client"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/match/{login}")
    public Response getUserByLogin(@PathParam("login") String login) throws  JOSEException{
        if(userService.getUserByLogin(login) == null) {
            return Response.status(404).build();
        }
        return Response.ok().entity(userService.getUserByLogin(login)).header("ETag", getJwsFromUser(login)).build();
    }

    public String getJwsFromUser(String login) throws NotFoundException, JOSEException {
        User user = userService.getUserByLogin(login);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("login", user.getLogin().toString());
        return this.jwsGen.generateJws(jsonObject.toString());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/matchAll")
    public List<User> getUsersMatchingLogin(@QueryParam("login") String login) {
        return userService.getUsersMatchingLogin(login);
    }

    @GET
    @RolesAllowed({"Admin", "Moderator", "Client"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/profile/{login}")
    public User getProfile(@PathParam("login") String login) {
        return userService.getUserByLogin(login);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/{userId}")
    public User getClient(@PathParam("userId") UUID userId) {
        return userService.getClientById(userId);
    }

    @GET
    @RolesAllowed({"Admin", "Moderator"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getClients() {
        return userService.findAllClients();
    }

    @DELETE
    @RolesAllowed({"Admin"})
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
    @RolesAllowed({"Admin", "Moderator", "Client"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateClient(User user, @Context HttpServletRequest request) {
                try {
            String jws = request.getHeader("If-Match");
            if (jws == null) {
                throw new BadRequestException();
            }
            userService.changeClient(user, jws);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (BadRequestException | IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        }
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Admin"})
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
    @PATCH
    @RolesAllowed({"Admin", "Moderator", "Client"})
    @Path("/changePassword")
    public Response changePassword(NewPassword newPassword) {
    try {
        userService.changePassword(newPassword.getNewPassword(), newPassword.getOldPassword());
        return Response.ok("Password changed").build();
    } catch (IllegalStateException ex) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    }

}
