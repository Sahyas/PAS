package com.pas.model;

import com.pas.controller.MvcJwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.AutoApplySession;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;


@ApplicationScoped
@AutoApplySession
public class MVCAuthenticationMechanism implements HttpAuthenticationMechanism {

    @Inject
    private MvcJwt mvcJwt;

    private String secret = "xdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd";

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpMessageContext httpMessageContext) {
        Set<String> roles = new HashSet<>();
        if (mvcJwt.getJwt().equals("")) {
            roles.add("Guest");
            return httpMessageContext.notifyContainerAboutLogin("Guest", roles);
        }
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(mvcJwt.getJwt()).getBody();
        roles.add(claims.get("role", String.class));
        return httpMessageContext.notifyContainerAboutLogin(claims.getSubject(), roles);
    }
}