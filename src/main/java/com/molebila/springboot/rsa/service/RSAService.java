package com.molebila.springboot.rsa.service;

import com.molebila.springboot.rsa.domain.AnyDto;
import com.molebila.springboot.rsa.domain.KeyDto;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;

public interface RSAService {

    AnyDto encrypt(AnyDto anyDto) throws Exception;

    AnyDto decrypt(AnyDto anyDto) throws Exception;

    KeyDto generateKeyPair() throws Exception;

    AnyDto encrypt2(AnyDto anyDto)throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException;

    AnyDto decrypt2(AnyDto anyDto) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException;
}
