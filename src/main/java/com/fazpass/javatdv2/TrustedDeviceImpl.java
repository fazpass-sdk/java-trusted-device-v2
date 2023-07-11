package com.fazpass.javatdv2;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class TrustedDeviceImpl implements TrustedDevice{
    private String privateKey;
    public TrustedDeviceImpl(String pathKey) {
        try{
            privateKey = readKeyFromFile(pathKey);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private String readKeyFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
    @Override
    public Device extract(String meta) {
        Utils u = new Utils(this.privateKey);
        return u.decryptResponse(meta);
    }
}

