package com.pas.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;
@Named
@SessionScoped
@Data
public class MvcJwt implements Serializable {

    private String jwt = "";
    private String username = "";}