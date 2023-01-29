package com.pas.controller;

import com.pas.model.Rent;
import com.pas.service.impl.RentService;

import java.util.List;
import java.util.UUID;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/rents")
@ApplicationScoped
public class RentController {
    @Inject
    private RentService rentService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response createRent(@QueryParam("clientId") UUID clientId, @QueryParam("bookId") UUID bookId) {
        Rent rent = rentService.rentBook(clientId, bookId);
        if (rent != null) {
            return Response.status(Response.Status.CREATED).entity(rent).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity("This book is already rented or user is not active").build();
        }
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/return/{bookId}")
    public Response returnRent(@PathParam("bookId") UUID bookId) {
        if (rentService.returnBook(bookId) != null) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity("Book with id " + bookId + " is not rented").build();
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rent> getRents() {
        return rentService.findAllRents();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/current")
    public List<Rent> getCurrentRents() {
        return rentService.findAllCurrentRents();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/ended")
    public List<Rent> getEndedRents() {
        return rentService.findAllEndedRents();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/{rentId}")
    public Rent getRent(@PathParam("rentId") UUID rentId) {
        return rentService.getRentById(rentId);
    }

    @DELETE
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/{rentId}")
    public Response deleteRent(@PathParam("rentId") UUID rentId) {
        if (rentService.deleteRent(rentService.getRentById(rentId))) {
            return Response.ok().entity("Rent with id " + rentId + " deleted").build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity("This rent is active").build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Rent updateRent(Rent rent) {
        return rentService.updateRent(rent);
    }
}
