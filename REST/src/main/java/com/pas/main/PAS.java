package com.pas.main;

import jakarta.annotation.security.DeclareRoles;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/")
@DeclareRoles({"Admin", "Moderator", "Client", "Guest"})
public class PAS extends Application {
}