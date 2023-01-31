package com.pas;

import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@FacesConfig
public class PAS extends Application {
}