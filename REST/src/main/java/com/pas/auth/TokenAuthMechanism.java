package com.pas.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TokenAuthMechanism implements HttpAuthenticationMechanism {

    private JWT jwtGenerator = new JWT();

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext context) throws AuthenticationException {
        String authorizationHeader = request.getHeader("Authorization");
        Set<String> roles = new HashSet<>();
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jws = authorizationHeader.substring("Bearer ".length());
            try {
                String token = authorizationHeader.replace("Bearer ", "");
                Claims claims = jwtGenerator.parseJWT(token).getBody();
                roles.add(claims.get("role", String.class));
                    return context.notifyContainerAboutLogin(claims.getSubject(), roles);

            } catch (Exception e) {
                // Invalid JWS, return 401 Unauthorized
                return context.responseUnauthorized();
            }
        }

        // No or invalid Authorization header, return 401 Unauthorized
        roles.add("Guest");
        return context.notifyContainerAboutLogin("guest", roles);
    }
}
