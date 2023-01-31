package com.pas.model;

import com.pas.controller.MvcJwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

//import javax.faces.bean.ApplicationScoped;
//import javax.faces.bean.ManagedBean;
//import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Objects;

@Named
@ManagedBean
@ApplicationScoped
public class UserRole implements Serializable {

//    @Inject
//    MvcJwt mvcJwt;
//    private String secrect = "xddddddddddddddddd";
//
//    Claims claims = Jwts.parser().setSigningKey(secrect).parseClaimsJws(mvcJwt.getJwt()).getBody();
//    String role = claims.get("roles", String.class);

    public boolean isAdmin() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Admin");
    }

    public boolean isClient() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Client");
    }

    public boolean isModerator() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Moderator");
    }

    public boolean isGuest() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Guest");
    }

    public String getRole() {
        if (this.isAdmin()) {
            return "Admin";
        }
        if (this.isModerator()) {
            return "Moderator";
        }
        if (this.isClient()) {
            return "Client";
        }
        return "Guest";
    }

}
