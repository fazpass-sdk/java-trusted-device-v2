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
    public Device checkDevice(String picId, String meta, String appId) throws FazpassException {
        return u.processDeviceRequest("/check", picId, meta, appId);
    }

    @Override
    public CompletableFuture<Optional<Device>> checkAsyncDevice(String picId, String meta, String appId) {
        return u.processAsyncDeviceRequest("/check", picId, meta, appId);
    }

    @Override
    public Device enrollDevice(String picId, String meta, String appId) throws FazpassException{
        return u.processDeviceRequest("/enroll", picId, meta, appId);
    }

    @Override
    public CompletableFuture<Optional<Device>> enrollAsyncDevice(String picId, String meta, String appId) {
        return u.processAsyncDeviceRequest("/enroll", picId, meta, appId);
    }

    @Override
    public Device validateDevice(String fazpassId, String meta, String appId) throws FazpassException{
        return u.processDeviceRequest("/validate", fazpassId, meta, appId);
    }

    @Override
    public CompletableFuture<Optional<Device>> validateAsyncDevice(String fazpassId, String meta, String appId) {
        return u.processAsyncDeviceRequest("/validate", fazpassId, meta, appId);
    }

    @Override
    public Device removeDevice(String fazpassId, String meta, String appId) throws FazpassException {
        return u.processDeviceRequest("/remove", fazpassId, meta, appId);
    }

    @Override
    public CompletableFuture<Optional<Device>> removeAsyncDevice(String fazpassId, String meta, String appId) {
        return u.processAsyncDeviceRequest("/remove", fazpassId, meta, appId);
    }
    // codecov ignore start
    protected void setHttpClient(HttpClient mockedClient) {
        this.u.setHttpClient(mockedClient);
    }

    public void setUtils(Utils spyUtils) {
        this.u = spyUtils;
    }
}

