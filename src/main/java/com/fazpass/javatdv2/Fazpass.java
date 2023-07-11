package com.fazpass.javatdv2;

public final class Fazpass {
    private Fazpass(){
    }

    public static TrustedDevice initialize(String pathKey){
        if (pathKey == null || pathKey.isEmpty()) {
            throw new IllegalArgumentException("private keys must be non-null and not empty.");
        }
        return new TrustedDeviceImpl(pathKey);
    }
}
