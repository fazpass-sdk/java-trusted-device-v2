package com.fazpass.javatdv2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fazpass.javatdv2.exception.DecryptionException;
import com.fazpass.javatdv2.exception.FazpassException;
import com.fazpass.javatdv2.exception.ResponseException;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.junit.Ignore;

import javax.crypto.Cipher;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.Security;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

class Utils {
    private HttpClient client;
    private final ObjectMapper objectMapper;

    private String baseUrl;
    private String privateKey;
    public Utils(HttpClient client, ObjectMapper objectMapper, String privateKey, String baseUrl) {
        this.client = client;
        this.objectMapper = objectMapper;
        this.privateKey = privateKey;
        this.baseUrl = baseUrl;
    }

    @Ignore
    protected Device processDeviceRequest(String endpoint, String picId, String meta) throws FazpassException {
        try {
            HttpResponse<String> response = sendRequest(endpoint, picId, meta);
            return parseDeviceFromResponse(response.body());
        } catch (IOException | InterruptedException e) {
            throw new ResponseException(e.getMessage());
        }
    }

    @Ignore
    protected CompletableFuture<Optional<Device>> processAsyncDeviceRequest(String endpoint, String picId, String meta) {
        return sendAsyncRequest(endpoint, picId, meta);
    }

    @Ignore
    protected Device parseDeviceFromResponse(String responseBody) throws DecryptionException {
        return decryptResponse(responseBody).orElseThrow(() -> new DecryptionException("Invalid device response"));
    }

    @Ignore
    protected Optional<Device> parseDeviceFromAsyncResponse(String responseBody) {
        return decryptResponse(responseBody);
    }

    @Ignore
    protected Optional<Device> decryptResponse(String responseBody) {
        try {
            Map<String, Object> response = objectMapper.readValue(responseBody, Map.class);
            Map<String, Object> data = (Map<String, Object>) response.get("data");
            // Mendapatkan string 'meta'
            String encryptedMeta = (String) data.get("meta");
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedMeta);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            PrivateKey privateKey = getPrivateKey(this.privateKey);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            String jsonString = new String(decryptedBytes, StandardCharsets.UTF_8);
            Device device = objectMapper.readValue(jsonString, Device.class);
            return Optional.ofNullable(device);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    @Ignore
    private PrivateKey getPrivateKey(String base64PrivateKey) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        try (Reader privateKeyReader = new StringReader(base64PrivateKey);
             PEMParser privatePemParser = new PEMParser(privateKeyReader)) {

            Object privateObject = privatePemParser.readObject();
            if (!(privateObject instanceof PEMKeyPair)) {
                throw new Exception("Invalid private key format");
            }

            PEMKeyPair pemKeyPair = (PEMKeyPair) privateObject;
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
            return converter.getPrivateKey(pemKeyPair.getPrivateKeyInfo());
        } catch (Exception e) {
            throw new Exception("Error generating Private Key", e);
        }
    }

    @Ignore
    protected HttpResponse<String> sendRequest(String endpoint, String picId, String meta) throws IOException, InterruptedException {
        Map<String, String> requestData = new HashMap<>();
        if(endpoint.equals("check") || endpoint.equals("enroll")){
            requestData.put("picId", picId);
            requestData.put("meta", meta);
        }else{
            requestData.put("fazpass_id", picId);
            requestData.put("meta", meta);
        }

        String requestBody = objectMapper.writeValueAsString(requestData);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.baseUrl + endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Ignore
    protected CompletableFuture<Optional<Device>> sendAsyncRequest(String endpoint, String picId, String meta) {
        Map<String, String> requestData = new HashMap<>();
        if(endpoint.equals("check") || endpoint.equals("enroll")){
            requestData.put("picId", picId);
            requestData.put("meta", meta);
        }else{
            requestData.put("fazpass_id", picId);
            requestData.put("meta", meta);
        }
        String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(requestData);
        } catch (JsonProcessingException e) {
            CompletableFuture<Optional<Device>> exceptionalFuture = new CompletableFuture<>();
            exceptionalFuture.completeExceptionally(e);
            return exceptionalFuture;
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.baseUrl + endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(this::parseDeviceFromAsyncResponse)
                .exceptionally(e -> {
                    e.printStackTrace();
                    return Optional.empty();
                });
    }

    @Ignore
    protected void setHttpClient(HttpClient mockedClient) {
        this.client = mockedClient;
    }
    // codecov ignore end
}
