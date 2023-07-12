package com.fazpass.javatdv2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import javax.crypto.Cipher;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.Security;
import java.util.Base64;

class Utils {

    private final String privateKey;

    public Utils(String privateKey) {
        this.privateKey = privateKey;
    }

    protected Device decryptResponse(String encryptedMeta) {
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedMeta);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            PrivateKey privateKey = getPrivateKey(this.privateKey);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            String jsonString = new String(decryptedBytes, StandardCharsets.UTF_8);
            System.out.println(jsonString);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, Device.class);
        } catch (Exception e) {
            return new Device();
        }
    }


    PrivateKey getPrivateKey(String base64PrivateKey) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        try (Reader privateKeyReader = new StringReader(base64PrivateKey);
             PEMParser privatePemParser = new PEMParser(privateKeyReader)) {

            Object privateObject = privatePemParser.readObject();
            PEMKeyPair pemKeyPair = (PEMKeyPair) privateObject;
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
            return converter.getPrivateKey(pemKeyPair.getPrivateKeyInfo());
        } catch (Exception e) {
            throw new Exception("error generating private key", e);
        }
    }

}
