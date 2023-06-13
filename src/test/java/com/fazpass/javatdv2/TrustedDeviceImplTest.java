package com.fazpass.javatdv2;

import com.fazpass.javatdv2.exception.FazpassException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrustedDeviceImplTest {
    private TrustedDevice trustedDevice;

    @BeforeEach
    void setUp() {
        String privateKey = "";
        try {
            privateKey = readKeyFromFile("./key.priv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String baseUrl = "http://localhost";
        trustedDevice = new TrustedDeviceImpl(privateKey, baseUrl);
    }
    public static String readKeyFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
    @Test
    void checkDevice_ValidInput_ReturnsDevice() {
        try {
            String responseBody = "{\n" +
                    "    \"status\": true,\n" +
                    "    \"code\": 200,\n" +
                    "    \"data\": {\n" +
                    "        \"meta\": \"YX/bW4mYEJaVhzJubb7l3PLqReUwUSmPaZLfzgmV7O3pCUt/z7bLCDFIyC0QBEQh7rI6rZXbuD5Uwe7meh3tlUJ/IotYE+nEwh5QhpGaqCmH53yhRGQpbF1Yz3FuJyWOfjMuzJVjLIVAPuKCr1NQHTROo8+pqePNcurI/vVT5knmNOcnup2iwb4T95pUtZ3KTNvtDPRFmeRiQ+fxbo8pZGQ9XFfYAb8tp2dvgsADFAQWaN7b4PSju2Rg7oxm1ByNfUNuY9tV99++AnBgXHpS7AqFgIM4LGr6wDCbDLo2/obG3fJEkVhHuJSm9J+ldMTr+j8gVu7UQh6/3jM8VS9QKiAuMR/rYaoTbEmHeDxQEiDpUl+l56TMypYucBtHUW5GBUXnVKBRtgh4RVojl5Q9fqZ7OMCs5B7Gv4ULriUoa4ibSm7CIq80fRSaNDRJ9abFPTBe0Hnem3Q+zTaNBe+DfCsNfgq8OTA0UbbuPSRtFolrWKnE457CWzIb/h5ZuIOOkMvCGgzt+DJrXBZAGAXrTL4CeVoIMYzJhlBr0YMFtP4braBG5hu/+B41cZp6RDHrDRkEzjJ1Fzjrgpj60OEs/bMT6Yr2NFhu2NXtBhmcPf/CdEG4IWX2040Lqx8f8RIZw/tEk0Y3ifRMhHXI/g8vlvuibNv3K11vx2pYnA5dRTI=\"\n" +
                    "    }\n" +
                    "}";
            HttpResponse<String> mockedResponse = mock(HttpResponse.class);
            when(mockedResponse.body()).thenReturn(responseBody);

            HttpClient mockedClient = mock(HttpClient.class);
            when(mockedClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockedResponse);
            String privateKey = "";
            try {
                privateKey = readKeyFromFile("./key.priv");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String baseUrl = "http://localhost";
            TrustedDeviceImpl trustedDevice = new TrustedDeviceImpl(privateKey, baseUrl);
            trustedDevice.setHttpClient(mockedClient);

            Device expectedDevice = new Device();
            TrustedDeviceImpl spyDevice = Mockito.spy(trustedDevice);
            Mockito.doReturn(expectedDevice).when(spyDevice).parseDeviceFromResponse(responseBody);

            String picId = "your-pic-id";
            String meta = "your-meta-data";
            Device resultDevice = spyDevice.checkDevice(picId, meta);

            assertEquals(expectedDevice, resultDevice);
            Mockito.verify(mockedClient, Mockito.times(1)).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        } catch (FazpassException | IOException | InterruptedException ignored) {
        }
    }

    @Test
    void checkAsyncDevice_ValidInput_ReturnsDevice() {
        String responseBody = "{\n" +
                "    \"status\": true,\n" +
                "    \"code\": 200,\n" +
                "    \"data\": {\n" +
                "        \"meta\": \"YX/bW4mYEJaVhzJubb7l3PLqReUwUSmPaZLfzgmV7O3pCUt/z7bLCDFIyC0QBEQh7rI6rZXbuD5Uwe7meh3tlUJ/IotYE+nEwh5QhpGaqCmH53yhRGQpbF1Yz3FuJyWOfjMuzJVjLIVAPuKCr1NQHTROo8+pqePNcurI/vVT5knmNOcnup2iwb4T95pUtZ3KTNvtDPRFmeRiQ+fxbo8pZGQ9XFfYAb8tp2dvgsADFAQWaN7b4PSju2Rg7oxm1ByNfUNuY9tV99++AnBgXHpS7AqFgIM4LGr6wDCbDLo2/obG3fJEkVhHuJSm9J+ldMTr+j8gVu7UQh6/3jM8VS9QKiAuMR/rYaoTbEmHeDxQEiDpUl+l56TMypYucBtHUW5GBUXnVKBRtgh4RVojl5Q9fqZ7OMCs5B7Gv4ULriUoa4ibSm7CIq80fRSaNDRJ9abFPTBe0Hnem3Q+zTaNBe+DfCsNfgq8OTA0UbbuPSRtFolrWKnE457CWzIb/h5ZuIOOkMvCGgzt+DJrXBZAGAXrTL4CeVoIMYzJhlBr0YMFtP4braBG5hu/+B41cZp6RDHrDRkEzjJ1Fzjrgpj60OEs/bMT6Yr2NFhu2NXtBhmcPf/CdEG4IWX2040Lqx8f8RIZw/tEk0Y3ifRMhHXI/g8vlvuibNv3K11vx2pYnA5dRTI=\"\n" +
                "    }\n" +
                "}";
        HttpResponse<String> mockedResponse = mock(HttpResponse.class);
        when(mockedResponse.body()).thenReturn(responseBody);

        HttpClient mockedClient = mock(HttpClient.class);
        when(mockedClient.sendAsync(any(), any()))
                .thenAnswer(invocation -> {
                    HttpRequest request = invocation.getArgument(0);
                    HttpResponse.BodyHandler<String> bodyHandler = invocation.getArgument(1);
                    CompletableFuture<HttpResponse<String>> future = CompletableFuture.completedFuture(mockedResponse);
                    return future;
                });
        String privateKey = "";
        try {
            privateKey = readKeyFromFile("./key.priv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String baseUrl = "http://localhost";
        TrustedDeviceImpl trustedDevice = new TrustedDeviceImpl(privateKey, baseUrl);
        trustedDevice.setHttpClient(mockedClient);

        Device expectedDevice = new Device();
        TrustedDeviceImpl spyDevice = Mockito.spy(trustedDevice);
        Mockito.doReturn(Optional.of(expectedDevice)).when(spyDevice).parseDeviceFromAsyncResponse(responseBody);

        String picId = "your-pic-id";
        String meta = "your-meta-data";
        CompletableFuture<Optional<Device>> resultDeviceFuture = spyDevice.checkAsyncDevice(picId, meta);
        Optional<Device> resultDevice = resultDeviceFuture.join();

        assertTrue(resultDevice.isPresent());
        assertEquals(expectedDevice, resultDevice.get());
        Mockito.verify(mockedClient, Mockito.times(1)).sendAsync(any(), any());

    }

    @Test
    void enrollDevice_ValidInput_ReturnsDevice() {
        try {
            String responseBody = "{\n" +
                    "    \"status\": true,\n" +
                    "    \"code\": 200,\n" +
                    "    \"data\": {\n" +
                    "        \"meta\": \"YX/bW4mYEJaVhzJubb7l3PLqReUwUSmPaZLfzgmV7O3pCUt/z7bLCDFIyC0QBEQh7rI6rZXbuD5Uwe7meh3tlUJ/IotYE+nEwh5QhpGaqCmH53yhRGQpbF1Yz3FuJyWOfjMuzJVjLIVAPuKCr1NQHTROo8+pqePNcurI/vVT5knmNOcnup2iwb4T95pUtZ3KTNvtDPRFmeRiQ+fxbo8pZGQ9XFfYAb8tp2dvgsADFAQWaN7b4PSju2Rg7oxm1ByNfUNuY9tV99++AnBgXHpS7AqFgIM4LGr6wDCbDLo2/obG3fJEkVhHuJSm9J+ldMTr+j8gVu7UQh6/3jM8VS9QKiAuMR/rYaoTbEmHeDxQEiDpUl+l56TMypYucBtHUW5GBUXnVKBRtgh4RVojl5Q9fqZ7OMCs5B7Gv4ULriUoa4ibSm7CIq80fRSaNDRJ9abFPTBe0Hnem3Q+zTaNBe+DfCsNfgq8OTA0UbbuPSRtFolrWKnE457CWzIb/h5ZuIOOkMvCGgzt+DJrXBZAGAXrTL4CeVoIMYzJhlBr0YMFtP4braBG5hu/+B41cZp6RDHrDRkEzjJ1Fzjrgpj60OEs/bMT6Yr2NFhu2NXtBhmcPf/CdEG4IWX2040Lqx8f8RIZw/tEk0Y3ifRMhHXI/g8vlvuibNv3K11vx2pYnA5dRTI=\"\n" +
                    "    }\n" +
                    "}";
            HttpResponse<String> mockedResponse = mock(HttpResponse.class);
            when(mockedResponse.body()).thenReturn(responseBody);

            HttpClient mockedClient = mock(HttpClient.class);
            when(mockedClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockedResponse);
            String privateKey = "";
            try {
                privateKey = readKeyFromFile("./key.priv");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String baseUrl = "http://localhost";
            TrustedDeviceImpl trustedDevice = new TrustedDeviceImpl(privateKey, baseUrl);
            trustedDevice.setHttpClient(mockedClient);

            Device expectedDevice = new Device();
            TrustedDeviceImpl spyDevice = Mockito.spy(trustedDevice);
            Mockito.doReturn(expectedDevice).when(spyDevice).parseDeviceFromResponse(responseBody);

            String picId = "your-pic-id";
            String meta = "your-meta-data";
            Device resultDevice = spyDevice.enrollDevice(picId, meta);

            assertEquals(expectedDevice, resultDevice);
            Mockito.verify(mockedClient, Mockito.times(1)).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        } catch (FazpassException | IOException | InterruptedException ignored) {
        }
    }

    @Test
    void enrollAsyncDevice_ValidInput_ReturnsDevice() {
        String responseBody = "{\n" +
                "    \"status\": true,\n" +
                "    \"code\": 200,\n" +
                "    \"data\": {\n" +
                "        \"meta\": \"YX/bW4mYEJaVhzJubb7l3PLqReUwUSmPaZLfzgmV7O3pCUt/z7bLCDFIyC0QBEQh7rI6rZXbuD5Uwe7meh3tlUJ/IotYE+nEwh5QhpGaqCmH53yhRGQpbF1Yz3FuJyWOfjMuzJVjLIVAPuKCr1NQHTROo8+pqePNcurI/vVT5knmNOcnup2iwb4T95pUtZ3KTNvtDPRFmeRiQ+fxbo8pZGQ9XFfYAb8tp2dvgsADFAQWaN7b4PSju2Rg7oxm1ByNfUNuY9tV99++AnBgXHpS7AqFgIM4LGr6wDCbDLo2/obG3fJEkVhHuJSm9J+ldMTr+j8gVu7UQh6/3jM8VS9QKiAuMR/rYaoTbEmHeDxQEiDpUl+l56TMypYucBtHUW5GBUXnVKBRtgh4RVojl5Q9fqZ7OMCs5B7Gv4ULriUoa4ibSm7CIq80fRSaNDRJ9abFPTBe0Hnem3Q+zTaNBe+DfCsNfgq8OTA0UbbuPSRtFolrWKnE457CWzIb/h5ZuIOOkMvCGgzt+DJrXBZAGAXrTL4CeVoIMYzJhlBr0YMFtP4braBG5hu/+B41cZp6RDHrDRkEzjJ1Fzjrgpj60OEs/bMT6Yr2NFhu2NXtBhmcPf/CdEG4IWX2040Lqx8f8RIZw/tEk0Y3ifRMhHXI/g8vlvuibNv3K11vx2pYnA5dRTI=\"\n" +
                "    }\n" +
                "}";
        HttpResponse<String> mockedResponse = mock(HttpResponse.class);
        when(mockedResponse.body()).thenReturn(responseBody);

        HttpClient mockedClient = mock(HttpClient.class);
        when(mockedClient.sendAsync(any(), any()))
                .thenAnswer(invocation -> {
                    HttpRequest request = invocation.getArgument(0);
                    HttpResponse.BodyHandler<String> bodyHandler = invocation.getArgument(1);
                    CompletableFuture<HttpResponse<String>> future = CompletableFuture.completedFuture(mockedResponse);
                    return future;
                });

        String privateKey = "";
        try {
            privateKey = readKeyFromFile("./key.priv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String baseUrl = "http://localhost";
        TrustedDeviceImpl trustedDevice = new TrustedDeviceImpl(privateKey, baseUrl);
        trustedDevice.setHttpClient(mockedClient);

        Device expectedDevice = new Device();
        TrustedDeviceImpl spyDevice = Mockito.spy(trustedDevice);
        Mockito.doReturn(Optional.of(expectedDevice)).when(spyDevice).parseDeviceFromAsyncResponse(responseBody);

        String picId = "your-pic-id";
        String meta = "your-meta-data";
        CompletableFuture<Optional<Device>> resultDeviceFuture = spyDevice.enrollAsyncDevice(picId, meta);
        Optional<Device> resultDevice = resultDeviceFuture.join();

        assertTrue(resultDevice.isPresent());
        assertEquals(expectedDevice, resultDevice.get());
        Mockito.verify(mockedClient, Mockito.times(1)).sendAsync(any(), any());
    }

    @Test
    void validateDevice_ValidInput_ReturnsDevice() {
        try {
            String responseBody = "{\n" +
                    "    \"status\": true,\n" +
                    "    \"code\": 200,\n" +
                    "    \"data\": {\n" +
                    "        \"meta\": \"YX/bW4mYEJaVhzJubb7l3PLqReUwUSmPaZLfzgmV7O3pCUt/z7bLCDFIyC0QBEQh7rI6rZXbuD5Uwe7meh3tlUJ/IotYE+nEwh5QhpGaqCmH53yhRGQpbF1Yz3FuJyWOfjMuzJVjLIVAPuKCr1NQHTROo8+pqePNcurI/vVT5knmNOcnup2iwb4T95pUtZ3KTNvtDPRFmeRiQ+fxbo8pZGQ9XFfYAb8tp2dvgsADFAQWaN7b4PSju2Rg7oxm1ByNfUNuY9tV99++AnBgXHpS7AqFgIM4LGr6wDCbDLo2/obG3fJEkVhHuJSm9J+ldMTr+j8gVu7UQh6/3jM8VS9QKiAuMR/rYaoTbEmHeDxQEiDpUl+l56TMypYucBtHUW5GBUXnVKBRtgh4RVojl5Q9fqZ7OMCs5B7Gv4ULriUoa4ibSm7CIq80fRSaNDRJ9abFPTBe0Hnem3Q+zTaNBe+DfCsNfgq8OTA0UbbuPSRtFolrWKnE457CWzIb/h5ZuIOOkMvCGgzt+DJrXBZAGAXrTL4CeVoIMYzJhlBr0YMFtP4braBG5hu/+B41cZp6RDHrDRkEzjJ1Fzjrgpj60OEs/bMT6Yr2NFhu2NXtBhmcPf/CdEG4IWX2040Lqx8f8RIZw/tEk0Y3ifRMhHXI/g8vlvuibNv3K11vx2pYnA5dRTI=\"\n" +
                    "    }\n" +
                    "}";
            HttpResponse<String> mockedResponse = mock(HttpResponse.class);
            when(mockedResponse.body()).thenReturn(responseBody);

            HttpClient mockedClient = mock(HttpClient.class);
            when(mockedClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockedResponse);

            String privateKey = "";
            try {
                privateKey = readKeyFromFile("./key.priv");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String baseUrl = "http://localhost";
            TrustedDeviceImpl trustedDevice = new TrustedDeviceImpl(privateKey, baseUrl);
            trustedDevice.setHttpClient(mockedClient);

            Device expectedDevice = new Device();
            TrustedDeviceImpl spyDevice = Mockito.spy(trustedDevice);
            Mockito.doReturn(expectedDevice).when(spyDevice).parseDeviceFromResponse(responseBody);

            String picId = "your-pic-id";
            String meta = "your-meta-data";
            Device resultDevice = spyDevice.validateDevice(picId, meta);

            assertEquals(expectedDevice, resultDevice);
            Mockito.verify(mockedClient, Mockito.times(1)).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        } catch (FazpassException | IOException | InterruptedException ignored) {
        }
    }

    @Test
    void validateAsyncDevice_ValidInput_ReturnsDevice() {
        String responseBody = "{\n" +
                "    \"status\": true,\n" +
                "    \"code\": 200,\n" +
                "    \"data\": {\n" +
                "        \"meta\": \"YX/bW4mYEJaVhzJubb7l3PLqReUwUSmPaZLfzgmV7O3pCUt/z7bLCDFIyC0QBEQh7rI6rZXbuD5Uwe7meh3tlUJ/IotYE+nEwh5QhpGaqCmH53yhRGQpbF1Yz3FuJyWOfjMuzJVjLIVAPuKCr1NQHTROo8+pqePNcurI/vVT5knmNOcnup2iwb4T95pUtZ3KTNvtDPRFmeRiQ+fxbo8pZGQ9XFfYAb8tp2dvgsADFAQWaN7b4PSju2Rg7oxm1ByNfUNuY9tV99++AnBgXHpS7AqFgIM4LGr6wDCbDLo2/obG3fJEkVhHuJSm9J+ldMTr+j8gVu7UQh6/3jM8VS9QKiAuMR/rYaoTbEmHeDxQEiDpUl+l56TMypYucBtHUW5GBUXnVKBRtgh4RVojl5Q9fqZ7OMCs5B7Gv4ULriUoa4ibSm7CIq80fRSaNDRJ9abFPTBe0Hnem3Q+zTaNBe+DfCsNfgq8OTA0UbbuPSRtFolrWKnE457CWzIb/h5ZuIOOkMvCGgzt+DJrXBZAGAXrTL4CeVoIMYzJhlBr0YMFtP4braBG5hu/+B41cZp6RDHrDRkEzjJ1Fzjrgpj60OEs/bMT6Yr2NFhu2NXtBhmcPf/CdEG4IWX2040Lqx8f8RIZw/tEk0Y3ifRMhHXI/g8vlvuibNv3K11vx2pYnA5dRTI=\"\n" +
                "    }\n" +
                "}";
        HttpResponse<String> mockedResponse = mock(HttpResponse.class);
        when(mockedResponse.body()).thenReturn(responseBody);

        HttpClient mockedClient = mock(HttpClient.class);
        when(mockedClient.sendAsync(any(), any()))
                .thenAnswer(invocation -> {
                    HttpRequest request = invocation.getArgument(0);
                    HttpResponse.BodyHandler<String> bodyHandler = invocation.getArgument(1);
                    CompletableFuture<HttpResponse<String>> future = CompletableFuture.completedFuture(mockedResponse);
                    return future;
                });

        String privateKey = "";
        try {
            privateKey = readKeyFromFile("./key.priv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String baseUrl = "http://localhost";
        TrustedDeviceImpl trustedDevice = new TrustedDeviceImpl(privateKey, baseUrl);
        trustedDevice.setHttpClient(mockedClient);

        Device expectedDevice = new Device();
        TrustedDeviceImpl spyDevice = Mockito.spy(trustedDevice);
        Mockito.doReturn(Optional.of(expectedDevice)).when(spyDevice).parseDeviceFromAsyncResponse(responseBody);

        String picId = "your-pic-id";
        String meta = "your-meta-data";
        CompletableFuture<Optional<Device>> resultDeviceFuture = spyDevice.validateAsyncDevice(picId, meta);
        Optional<Device> resultDevice = resultDeviceFuture.join();

        assertTrue(resultDevice.isPresent());
        assertEquals(expectedDevice, resultDevice.get());
        Mockito.verify(mockedClient, Mockito.times(1)).sendAsync(any(), any());
    }

    @Test
    void removeDevice_ValidInput_ReturnsDevice() {
        try {
            String responseBody = "{\n" +
                    "    \"status\": true,\n" +
                    "    \"code\": 200,\n" +
                    "    \"data\": {\n" +
                    "        \"meta\": \"YX/bW4mYEJaVhzJubb7l3PLqReUwUSmPaZLfzgmV7O3pCUt/z7bLCDFIyC0QBEQh7rI6rZXbuD5Uwe7meh3tlUJ/IotYE+nEwh5QhpGaqCmH53yhRGQpbF1Yz3FuJyWOfjMuzJVjLIVAPuKCr1NQHTROo8+pqePNcurI/vVT5knmNOcnup2iwb4T95pUtZ3KTNvtDPRFmeRiQ+fxbo8pZGQ9XFfYAb8tp2dvgsADFAQWaN7b4PSju2Rg7oxm1ByNfUNuY9tV99++AnBgXHpS7AqFgIM4LGr6wDCbDLo2/obG3fJEkVhHuJSm9J+ldMTr+j8gVu7UQh6/3jM8VS9QKiAuMR/rYaoTbEmHeDxQEiDpUl+l56TMypYucBtHUW5GBUXnVKBRtgh4RVojl5Q9fqZ7OMCs5B7Gv4ULriUoa4ibSm7CIq80fRSaNDRJ9abFPTBe0Hnem3Q+zTaNBe+DfCsNfgq8OTA0UbbuPSRtFolrWKnE457CWzIb/h5ZuIOOkMvCGgzt+DJrXBZAGAXrTL4CeVoIMYzJhlBr0YMFtP4braBG5hu/+B41cZp6RDHrDRkEzjJ1Fzjrgpj60OEs/bMT6Yr2NFhu2NXtBhmcPf/CdEG4IWX2040Lqx8f8RIZw/tEk0Y3ifRMhHXI/g8vlvuibNv3K11vx2pYnA5dRTI=\"\n" +
                    "    }\n" +
                    "}";
            HttpResponse<String> mockedResponse = mock(HttpResponse.class);
            when(mockedResponse.body()).thenReturn(responseBody);

            HttpClient mockedClient = mock(HttpClient.class);
            when(mockedClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockedResponse);

            String privateKey = "";
            try {
                privateKey = readKeyFromFile("./key.priv");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String baseUrl = "http://localhost";
            TrustedDeviceImpl trustedDevice = new TrustedDeviceImpl(privateKey, baseUrl);
            trustedDevice.setHttpClient(mockedClient);

            Device expectedDevice = new Device();
            TrustedDeviceImpl spyDevice = Mockito.spy(trustedDevice);
            Mockito.doReturn(expectedDevice).when(spyDevice).parseDeviceFromResponse(responseBody);

            String picId = "your-pic-id";
            String meta = "your-meta-data";
            Device resultDevice = spyDevice.removeDevice(picId, meta);

            assertEquals(expectedDevice, resultDevice);
            Mockito.verify(mockedClient, Mockito.times(1)).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        } catch (FazpassException | IOException | InterruptedException ignored) {
        }
    }

    @Test
    void removeAsyncDevice_ValidInput_ReturnsDevice() {
        String responseBody = "{\n" +
                "    \"status\": true,\n" +
                "    \"code\": 200,\n" +
                "    \"data\": {\n" +
                "        \"meta\": \"YX/bW4mYEJaVhzJubb7l3PLqReUwUSmPaZLfzgmV7O3pCUt/z7bLCDFIyC0QBEQh7rI6rZXbuD5Uwe7meh3tlUJ/IotYE+nEwh5QhpGaqCmH53yhRGQpbF1Yz3FuJyWOfjMuzJVjLIVAPuKCr1NQHTROo8+pqePNcurI/vVT5knmNOcnup2iwb4T95pUtZ3KTNvtDPRFmeRiQ+fxbo8pZGQ9XFfYAb8tp2dvgsADFAQWaN7b4PSju2Rg7oxm1ByNfUNuY9tV99++AnBgXHpS7AqFgIM4LGr6wDCbDLo2/obG3fJEkVhHuJSm9J+ldMTr+j8gVu7UQh6/3jM8VS9QKiAuMR/rYaoTbEmHeDxQEiDpUl+l56TMypYucBtHUW5GBUXnVKBRtgh4RVojl5Q9fqZ7OMCs5B7Gv4ULriUoa4ibSm7CIq80fRSaNDRJ9abFPTBe0Hnem3Q+zTaNBe+DfCsNfgq8OTA0UbbuPSRtFolrWKnE457CWzIb/h5ZuIOOkMvCGgzt+DJrXBZAGAXrTL4CeVoIMYzJhlBr0YMFtP4braBG5hu/+B41cZp6RDHrDRkEzjJ1Fzjrgpj60OEs/bMT6Yr2NFhu2NXtBhmcPf/CdEG4IWX2040Lqx8f8RIZw/tEk0Y3ifRMhHXI/g8vlvuibNv3K11vx2pYnA5dRTI=\"\n" +
                "    }\n" +
                "}";
        HttpResponse<String> mockedResponse = mock(HttpResponse.class);
        when(mockedResponse.body()).thenReturn(responseBody);

        HttpClient mockedClient = mock(HttpClient.class);
        when(mockedClient.sendAsync(any(), any()))
                .thenAnswer(invocation -> {
                    HttpRequest request = invocation.getArgument(0);
                    HttpResponse.BodyHandler<String> bodyHandler = invocation.getArgument(1);
                    CompletableFuture<HttpResponse<String>> future = CompletableFuture.completedFuture(mockedResponse);
                    return future;
                });

        String privateKey = "";
        try {
            privateKey = readKeyFromFile("./key.priv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String baseUrl = "http://localhost";
        TrustedDeviceImpl trustedDevice = new TrustedDeviceImpl(privateKey, baseUrl);
        trustedDevice.setHttpClient(mockedClient);

        Device expectedDevice = new Device();
        TrustedDeviceImpl spyDevice = Mockito.spy(trustedDevice);
        Mockito.doReturn(Optional.of(expectedDevice)).when(spyDevice).parseDeviceFromAsyncResponse(responseBody);

        String picId = "your-pic-id";
        String meta = "your-meta-data";
        CompletableFuture<Optional<Device>> resultDeviceFuture = spyDevice.removeAsyncDevice(picId, meta);
        Optional<Device> resultDevice = resultDeviceFuture.join();

        assertTrue(resultDevice.isPresent());
        assertEquals(expectedDevice, resultDevice.get());
        Mockito.verify(mockedClient, Mockito.times(1)).sendAsync(any(), any());
    }
}

