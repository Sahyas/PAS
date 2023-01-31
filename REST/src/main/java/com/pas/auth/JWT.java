package com.pas.auth;
import io.jsonwebtoken.*;
import java.time.Instant;
import java.util.Date;

public class JWT {
    private String secrect = "xddddddddddddddddd";
    public String generateJWT(String login, String role) {
        return Jwts.builder()
                .setSubject(login)
                .setIssuedAt(new Date())
                .claim("role", role)
                .setExpiration(Date.from(Instant.now().plusSeconds(3600)))
                .signWith(SignatureAlgorithm.HS256, secrect)
                .compact();
    }
    public Jws<Claims> parseJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(secrect)
                .parseClaimsJws(jwt);
    }

}
