package com.fazpass.javatdv2;

import java.net.http.HttpClient;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fazpass.javatdv2.exception.FazpassException;

class TrustedDeviceImpl implements TrustedDevice{
    private Utils u;
    public TrustedDeviceImpl(String privateKey, String baseUrl) {
        this.u = new Utils(HttpClient.newHttpClient(), new ObjectMapper(), privateKey, baseUrl);
    }

    @Override
    public Device checkDevice(String picId, String meta) throws FazpassException {
        return u.processDeviceRequest("/check", picId, meta);
    }

    @Override
    public CompletableFuture<Optional<Device>> checkAsyncDevice(String picId, String meta) {
        return u.processAsyncDeviceRequest("/check", picId, meta);
    }

    @Override
    public Device enrollDevice(String picId, String meta) throws FazpassException{
        return u.processDeviceRequest("/enroll", picId, meta);
    }

    @Override
    public CompletableFuture<Optional<Device>> enrollAsyncDevice(String picId, String meta) {
        return u.processAsyncDeviceRequest("/enroll", picId, meta);
    }

    @Override
    public Device validateDevice(String fazpassId, String meta) throws FazpassException{
        return u.processDeviceRequest("/validate", fazpassId, meta);
    }

    @Override
    public CompletableFuture<Optional<Device>> validateAsyncDevice(String fazpassId, String meta) {
        return u.processAsyncDeviceRequest("/validate", fazpassId, meta);
    }

    @Override
    public Device removeDevice(String fazpassId, String meta) throws FazpassException {
        return u.processDeviceRequest("/remove", fazpassId, meta);
    }

    @Override
    public CompletableFuture<Optional<Device>> removeAsyncDevice(String fazpassId, String meta) {
        return u.processAsyncDeviceRequest("/remove", fazpassId, meta);
    }
    // codecov ignore start
    protected void setHttpClient(HttpClient mockedClient) {
        this.u.setHttpClient(mockedClient);
    }

    protected Object getUtils() {
        return this.u;
    }

    public void setUtils(Utils spyUtils) {
        this.u = spyUtils;
    }
}

