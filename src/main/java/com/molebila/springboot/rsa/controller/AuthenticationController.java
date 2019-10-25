package com.molebila.springboot.rsa.controller;

import com.molebila.springboot.rsa.domain.AnyDto;
import com.molebila.springboot.rsa.domain.KeyDto;
import com.molebila.springboot.rsa.service.KeyPairService;
import com.molebila.springboot.rsa.service.RSAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RequestMapping(path = "/api/v1/authentication")
@RestController
public class AuthenticationController {

    @Autowired
    private RSAService rsaService;

    @Autowired
    private KeyPairService keyPairService;


    @GetMapping(value = "/generate")
    public String generateKey() throws IOException, NoSuchAlgorithmException {

        return keyPairService.generateKeyPairToClient();

    }

    @PostMapping(value = "/encrypt", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AnyDto encryptMessage(@RequestBody AnyDto anyDto) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {

        return rsaService.encrypt2(anyDto);

    }

    @PostMapping(value = "/decrypt", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AnyDto decryptMessage(@RequestBody AnyDto anyDto) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {

        return rsaService.decrypt2(anyDto) ;
    }

}
