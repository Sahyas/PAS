package com.pas.auth;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;

import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;

public class JWS {

    private String secrect = "xddddddddddddddddd";

    public String generateJws(String payload) throws JOSEException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        Payload jwsPayload = new Payload(payload);
        JWSObject jwsObject = new JWSObject(header, jwsPayload);
        byte[] secret = secrect.getBytes();
        JWSSigner signer = new MACSigner(secret);
        jwsObject.sign(signer);
        return jwsObject.serialize();
    }

    public boolean verify(String jws) throws JOSEException, ParseException {
        SignedJWT signedJWT = SignedJWT.parse(jws);

        JWSVerifier verifier = new MACVerifier(secrect);

        return signedJWT.verify(verifier);
    }
}
