package com.pas.main;

import jakarta.annotation.security.DeclareRoles;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/")
@DeclareRoles({"ADMIN", "MODERATOR", "CLIENT", "GUEST"})
public class PAS extends Application {
}