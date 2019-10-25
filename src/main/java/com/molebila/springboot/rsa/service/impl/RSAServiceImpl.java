package com.molebila.springboot.rsa.service.impl;

import com.molebila.springboot.rsa.config.RSAEncryption;
import com.molebila.springboot.rsa.config.RSAUtil;
import com.molebila.springboot.rsa.domain.AnyDto;
import com.molebila.springboot.rsa.domain.KeyDto;
import com.molebila.springboot.rsa.service.RSAService;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

@Service
public class RSAServiceImpl extends RSAUtil implements RSAService {

    private RSAEncryption rsaEncryption;

    @Override
    public AnyDto encrypt(AnyDto anyDto) throws Exception{
        AnyDto newAnyDto = new AnyDto();
        newAnyDto.setPlainText(anyDto.getPlainText());
        // Encryption
        byte[] cipherTextArray = rsaEncryption.encrypt(anyDto.getPlainText(), "D:\\Teravin Technovation\\klan-spring-boot\\RSA-Encryption\\public.key");
        String encryptedText = Base64.getEncoder().encodeToString(cipherTextArray);
        System.out.println("Encrypted Text : " + encryptedText);
        newAnyDto.setChiperText(encryptedText);
        return newAnyDto;
    }

    @Override
    public AnyDto decrypt(AnyDto anyDto) throws Exception {
        AnyDto newAnyDto = new AnyDto();
        newAnyDto.setPlainText(anyDto.getChiperText());
        byte[] tr = Base64.getDecoder().decode(anyDto.getChiperText());
        String decryptedText = rsaEncryption.decrypt(tr, "D:\\Teravin Technovation\\klan-spring-boot\\RSA-Encryption\\private.key");
        System.out.println("DeCrypted Text : " + decryptedText);
        newAnyDto.setPlainText(decryptedText);
        return newAnyDto;
    }

    @Override
    public KeyDto generateKeyPair() throws Exception {
        // Get an instance of the RSA key generator
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(4096);

        // Generate the KeyPair
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Get the public and private key
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Get the RSAPublicKeySpec and RSAPrivateKeySpec
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
        RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);

        // Saving the Key to the file
        rsaEncryption.saveKeyToFile("public.key", publicKeySpec.getModulus(), publicKeySpec.getPublicExponent());
        rsaEncryption.saveKeyToFile("private.key", privateKeySpec.getModulus(), privateKeySpec.getPrivateExponent());

        KeyDto keyDto = new KeyDto();
        keyDto.setModulus(publicKeySpec.getModulus().toString());
        keyDto.setExponent(publicKeySpec.getPublicExponent().toString());
        return keyDto;
    }

    @Override
    public AnyDto encrypt2(AnyDto anyDto) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {
        AnyDto anyDto1 = new AnyDto();
        if((!"".equals(anyDto.getPlainText()) || (!"".equals(anyDto.getPublicKey())))) {
            System.out.println("public_key: "+anyDto.getPublicKey());
            try {
                String encryptedString = Base64.getEncoder().encodeToString(encryptString(anyDto.getPlainText(), anyDto.getPublicKey()));
                System.out.println(encryptedString);
                anyDto1.setChiperText(encryptedString);
            } catch (NoSuchAlgorithmException e) {
                System.err.println(e.getMessage());
            }
        }else {
            System.err.println("Plain text or Public key is empty");
        }

        return anyDto1;
    }

    @Override
    public AnyDto decrypt2(AnyDto anyDto) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {
        AnyDto anyDto1 = new AnyDto();
        try{
            String decryptedString = decryptString(anyDto.getChiperText(), anyDto.getPrivateKey());
            System.out.println(decryptedString);
            anyDto1.setPlainText(decryptedString);
        }catch (NoSuchAlgorithmException e){
            System.err.println(e.getMessage());
        }
        return anyDto1;
    }


}
