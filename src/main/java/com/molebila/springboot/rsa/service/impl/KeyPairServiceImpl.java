package com.molebila.springboot.rsa.service.impl;

import com.molebila.springboot.rsa.config.CustomWriteKey;
import com.molebila.springboot.rsa.config.RSAKeyPairGenerator;
import com.molebila.springboot.rsa.service.KeyPairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Service
public class KeyPairServiceImpl implements KeyPairService {

    private CustomWriteKey customWriteKey;

    @Override
    public String generateKeyPairToClient() throws IOException, NoSuchAlgorithmException{

        return generateKeyPair();
    }

    @Override
    public void loadPrivateKeyFromFile() throws Exception {
        /* Read all bytes from the private key file */
        Path path = Paths.get("D:\\Teravin Technovation\\klan-spring-boot\\RSA-Encryption\\private.key");
        byte[] bytes = Files.readAllBytes(path);

        /* Generate private key. */
        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey pvt = kf.generatePrivate(ks);
    }

    @Override
    public void loadPublicKeyFromFile() throws Exception {
        /* Read all the public key bytes */
        Path path = Paths.get("D:\\Teravin Technovation\\klan-spring-boot\\RSA-Encryption\\public.key");
        byte[] bytes = Files.readAllBytes(path);

        /* Generate public key. */
        X509EncodedKeySpec ks = new X509EncodedKeySpec(bytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey pub = kf.generatePublic(ks);
    }


    private String generateKeyPair() throws IOException, NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();
        Key pub = kp.getPublic();
        Key pvt = kp.getPrivate();

        Base64.Encoder encoder = Base64.getEncoder();


        String pS = "privateKey";
        Writer out = new FileWriter(pS + ".key");
        out.write("-----BEGIN RSA PRIVATE KEY-----\n");
        out.write(encoder.encodeToString(pvt.getEncoded()));
        out.write("\n-----END RSA PRIVATE KEY-----\n");
        out.close();

        String pB  = "publicKey";
        out = new FileWriter(pB + ".pub");
        out.write("-----BEGIN RSA PUBLIC KEY-----\n");
        out.write(encoder.encodeToString(pub.getEncoded()));
        out.write("\n-----END RSA PUBLIC KEY-----\n");
        out.close();

//        customWriteKey.writeKeyToFile("private", encoder.encodeToString(pvt.getEncoded()));
//        customWriteKey.writeKeyToFile("public", encoder.encodeToString(pub.getEncoded()));

        return encoder.encodeToString(pub.getEncoded());
    }


}
