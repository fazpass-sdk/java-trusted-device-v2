package com.fazpass.javatdv2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Security;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UtilsTest {
    private Utils utils;
    private HttpClient mockClient;
    private ObjectMapper objectMapper;
    private String privateKey;
    private String baseUrl;
    String responseBody = "";
    @BeforeEach
    void setUp() {
        mockClient = Mockito.mock(HttpClient.class);
        objectMapper = new ObjectMapper();
        try {
            privateKey = readKeyFromFile("./key.priv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        responseBody = "{\n" +
                "    \"status\": true,\n" +
                "    \"code\": 200,\n" +
                "    \"data\": {\n" +
                "        \"meta\": \"YX/bW4mYEJaVhzJubb7l3PLqReUwUSmPaZLfzgmV7O3pCUt/z7bLCDFIyC0QBEQh7rI6rZXbuD5Uwe7meh3tlUJ/IotYE+nEwh5QhpGaqCmH53yhRGQpbF1Yz3FuJyWOfjMuzJVjLIVAPuKCr1NQHTROo8+pqePNcurI/vVT5knmNOcnup2iwb4T95pUtZ3KTNvtDPRFmeRiQ+fxbo8pZGQ9XFfYAb8tp2dvgsADFAQWaN7b4PSju2Rg7oxm1ByNfUNuY9tV99++AnBgXHpS7AqFgIM4LGr6wDCbDLo2/obG3fJEkVhHuJSm9J+ldMTr+j8gVu7UQh6/3jM8VS9QKiAuMR/rYaoTbEmHeDxQEiDpUl+l56TMypYucBtHUW5GBUXnVKBRtgh4RVojl5Q9fqZ7OMCs5B7Gv4ULriUoa4ibSm7CIq80fRSaNDRJ9abFPTBe0Hnem3Q+zTaNBe+DfCsNfgq8OTA0UbbuPSRtFolrWKnE457CWzIb/h5ZuIOOkMvCGgzt+DJrXBZAGAXrTL4CeVoIMYzJhlBr0YMFtP4braBG5hu/+B41cZp6RDHrDRkEzjJ1Fzjrgpj60OEs/bMT6Yr2NFhu2NXtBhmcPf/CdEG4IWX2040Lqx8f8RIZw/tEk0Y3ifRMhHXI/g8vlvuibNv3K11vx2pYnA5dRTI=\"\n" +
                "    }\n" +
                "}";
        baseUrl = "https://localhost.com";
        utils = new Utils(mockClient, objectMapper, privateKey, baseUrl);
    }
    public static String readKeyFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
    @Test
    void testProcessDeviceRequest() {
        try {

            HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);

            when(mockResponse.body()).thenReturn(responseBody);
            when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

            Device device = utils.processDeviceRequest("endpoint", "picId", "meta", "appId");
            assertNotNull(device);
        } catch (Exception e) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    void testProcessAsyncDeviceRequest() {
        try {
            HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);

            when(mockResponse.body()).thenReturn(responseBody);
            when(mockClient.sendAsync(any(), any()))
                    .thenAnswer(invocation -> {
                        HttpRequest request = invocation.getArgument(0);
                        HttpResponse.BodyHandler<String> bodyHandler = invocation.getArgument(1);
                        CompletableFuture<HttpResponse<String>> future = CompletableFuture.completedFuture(mockResponse);
                        return future;
                    });

            Optional<Device> device = utils.processAsyncDeviceRequest("endpoint", "picId", "meta", "appId").get();
            assertTrue(device.isPresent());
        } catch (Exception e) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    void testDecryptResponse() {
        Security.addProvider(new BouncyCastleProvider());

        Optional<Device> device = utils.decryptResponse(responseBody);
        assertTrue(device.isPresent());
    }

    @Test
    void testParseDeviceFromResponse() {
        try {
            Security.addProvider(new BouncyCastleProvider());

            Device device = utils.parseDeviceFromResponse(responseBody);
            assertNotNull(device);
        } catch (Exception e) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    void testParseDeviceFromAsyncResponse() {

        Security.addProvider(new BouncyCastleProvider());

        Optional<Device> device = utils.parseDeviceFromAsyncResponse(responseBody);
        assertTrue(device.isPresent());
    }

    @Test
    void testSendRequest() {
        try {
            HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);

            when(mockResponse.body()).thenReturn(responseBody);
            when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

            HttpResponse<String> response = utils.sendRequest("endpoint", "picId", "meta", "appId");
            assertNotNull(response);
        } catch (Exception e) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    void testSendAsyncRequest() {
        try {
            HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);

            when(mockResponse.body()).thenReturn(responseBody);
            when(mockClient.sendAsync(any(), any()))
                    .thenAnswer(invocation -> {
                        HttpRequest request = invocation.getArgument(0);
                        HttpResponse.BodyHandler<String> bodyHandler = invocation.getArgument(1);
                        CompletableFuture<HttpResponse<String>> future = CompletableFuture.completedFuture(mockResponse);
                        return future;
                    });
            CompletableFuture<Optional<Device>> response = utils.sendAsyncRequest("endpoint", "picId", "meta", "appId");
            assertNotNull(response.get());
        } catch (Exception e) {
            fail("Exception should not have been thrown.");
        }
    }
    @Test
    void testGetUrl(){
        Utils u = new Utils(mockClient, objectMapper, "","http://localhost");
        assertEquals(u.getBaseUrl(), "http://localhost");
    }
    // ...Tambahkan test lainnya di sini
}
