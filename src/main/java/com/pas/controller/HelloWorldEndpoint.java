package com.pas.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/hello")
public class HelloWorldEndpoint {


    @GET
    public String helloWorld() {
        return "Hello World!";
    }
}
