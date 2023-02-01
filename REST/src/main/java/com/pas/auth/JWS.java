package com.pas.auth;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;

import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;

public class JWS {

    private String secrect = "xdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd";

    public String generateJws(String payload) throws JOSEException {
        JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(payload));
        JWSSigner signer = new MACSigner(secrect);
        jwsObject.sign(signer);
        return jwsObject.serialize();
    }

    public boolean verify(String jws) throws JOSEException, ParseException {
        SignedJWT signedJWT = SignedJWT.parse(jws);

        JWSVerifier verifier = new MACVerifier(secrect);

        return signedJWT.verify(verifier);
    }
}
