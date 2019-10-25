package com.molebila.springboot.rsa.config;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class CustomWriteKey {

    private final static String PRIVATE_KEY_LOG = "PRIVATE KEY";

    private final static String PUBLIC_KEY_LOG = "PUBLIC KEY";

    public void writeKeyToFile(String fileName, String encodedKey) {
        String LOG_KEY = getKeyStaticLog(fileName);
        try{

            Writer out = new FileWriter(fileName + ".key");
            out.write("-----BEGIN RSA "+ LOG_KEY +"-----\n");
            out.write(encodedKey);
            out.write("\n-----END RSA "+ LOG_KEY +"-----\n");
            out.close();
        }catch (IOException e){
            System.out.println("Get error when trying to save key as file" + e );
        }

    }

    private String getKeyStaticLog(String key){
        switch (key) {
            case "private" :
                return PRIVATE_KEY_LOG;
            case "public" :
                return PUBLIC_KEY_LOG;
            default:
                return null;
        }
    }
}
