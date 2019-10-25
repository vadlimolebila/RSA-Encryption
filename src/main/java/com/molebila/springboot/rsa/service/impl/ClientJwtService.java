package com.molebila.springboot.rsa.service.impl;

import com.auth0.jwt.JWT;
import com.sun.org.apache.xml.internal.security.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class ClientJwtService {

    @Value("${client.publicKey}")
    private String publicKeyString;

    @Value("${client.privateKey}")
    private String privateKeyString;

//    public Algorithm buildJwtAlgorithm() {
//        KeyFactory kf = KeyFactory.getInstance('RSA');
//        byte[] publicKeyBytes = new PemReader(new StringReader(publicKeyString)).readPemObject().getContent();
//        RSAPublicKey publicKey = (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
//
//        byte[] privateKeyBytes = new PemReader(new StringReader(privateKeyString)).readPemObject().getContent();
//        RSAPrivateKey privateKey = (RSAPrivateKey) kf.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
//
//        Algorithm.RSA512(publicKey, privateKey);
//    }
//
//    String generateJsonWebToken() {
//        Algorithm jwtSigningAlgorithm = buildJwtAlgorithm()
//
//        JWT.create()
//                .withIssuer('My Company')
//                .withAudience('urn::https://mycomp.any/v1.0/')
//                .withClaim('nameid', 'My Company')
//                .sign(jwtSigningAlgorithm);
//    }
}
