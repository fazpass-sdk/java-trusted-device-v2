package com.fazpass.javatdv2;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class TrustedDeviceImpl implements TrustedDevice{
    private final String privateKey;
    public TrustedDeviceImpl(String pathKey) throws IOException {
        try{
            privateKey = readKeyFromFile(pathKey);
        }catch (IOException e){
            throw new IOException("key not found");
        }
    }
    private String readKeyFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
    @Override
    public Meta extract(String meta) {
        Utils u = new Utils(this.privateKey);
        return u.decryptResponse(meta);
    }
}

