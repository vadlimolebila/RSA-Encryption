package com.molebila.springboot.rsa.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface KeyPairService {

    String generateKeyPairToClient() throws IOException, NoSuchAlgorithmException;

    void loadPrivateKeyFromFile() throws Exception;

    void loadPublicKeyFromFile() throws Exception;

}
