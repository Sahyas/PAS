package com.pas.controller;

import com.pas.model.Rent;
import com.pas.service.impl.RentService;

import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/rents")
@ApplicationScoped
public class RentController {
    @Inject
    private RentService rentService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Rent createRent(@QueryParam("clientId") UUID clientId, @QueryParam("bookId") UUID bookId) {
        return rentService.rentBook(clientId, bookId);
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/return/{bookId}")
    public Rent returnRent(@PathParam("bookId") UUID bookId) {
        return rentService.returnBook(bookId);
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
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/{rentId}")
    public Rent getRent(@PathParam("rentId") UUID rentId) {
        return rentService.getRentById(rentId);
    }

    @DELETE
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/{rentId}")
    public void deleteRent(@PathParam("rentId") UUID rentId) {
        rentService.deleteRent(rentService.getRentById(rentId));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Rent updateRent(Rent rent) {
        return rentService.updateRent(rent);
    }
}
