package com.fazpass.javatdv2;

public final class Fazpass {
    private Fazpass(){
    }

    public static TrustedDevice initialize(String privateKey, String baseUrl){
        if (privateKey == null || privateKey.isEmpty() || baseUrl == null || baseUrl.isEmpty()) {
            throw new IllegalArgumentException("Url and private keys must be non-null and not empty.");
        }
        return new TrustedDeviceImpl(privateKey, baseUrl);
    }
}
