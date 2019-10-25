package com.molebila.springboot.rsa.domain;

public class AnyDto {

    private String plainText;

    private String chiperText;

    private String privateKey;

    private String publicKey;

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public String getChiperText() {
        return chiperText;
    }

    public void setChiperText(String chiperText) {
        this.chiperText = chiperText;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKeyy) {
        this.publicKey = publicKeyy;
    }
}
