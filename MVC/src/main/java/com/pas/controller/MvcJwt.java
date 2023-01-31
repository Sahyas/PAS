package com.pas.controller;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;
@Named
@ManagedBean
@SessionScoped
@Data
public class MvcJwt implements Serializable {

    private String jwt = "";
    private String username = "";}