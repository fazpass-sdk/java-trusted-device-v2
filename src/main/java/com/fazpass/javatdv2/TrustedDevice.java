package com.fazpass.javatdv2;

import com.fazpass.javatdv2.exception.FazpassException;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface TrustedDevice {

    /**
     * Check the existence of correlation among device, pic id and package of application
     *
     * @param picId id of the end user that can possible using email or phone
     * @param meta  encrypted data that collected from device sdk
     * @param appId id that can be seen in the dashboard when client registering their app
     * @return {@link Device}
     */
    Device checkDevice(String picId, String meta, String appId) throws FazpassException;


    /**
     * Async version of the check device method
     *
     * @param picId id of the end user that can possible using email or phone
     * @param meta  encrypted data that collected from device sdk
     * @param appId id that can be seen in the dashboard when client registering their app
     * @return {@link CompletableFuture<Optional<Device>>}
     */
    CompletableFuture<Optional<Device>> checkAsyncDevice(String picId, String meta, String appId);

    /**
     * Create fazpass id that should be store inside the device
     *
     * @param picId id of the end user that can possible using email or phone
     * @param meta  encrypted data that collected from device sdk
     * @param appId id that can be seen in the dashboard when client registering their app
     * @return {@link Device}
     */
    Device enrollDevice(String picId, String meta, String appId) throws FazpassException;

    /**
     * Async version of the check enroll method
     *
     * @param picId id of the end user that can possible using email or phone
     * @param meta  encrypted data that collected from device sdk
     * @param appId
     * @return {@link CompletableFuture<Optional<Device>>}
     */
    CompletableFuture<Optional<Device>> enrollAsyncDevice(String picId, String meta, String appId);

    /**
     * validating all possibility variable that acquired from sdk client
     *
     * @param fazpassId id that created when device is enrolled
     * @param meta encrypted data that collected from device sdk
     * @param appId id that can be seen in the dashboard when client registering their app
     * @return {@link Device}
     */
    Device validateDevice(String fazpassId, String meta, String appId) throws FazpassException;

    /**
     * Async version of validate method
     *
     * @param fazpassId id that created when device is enrolled
     * @param meta      encrypted data that collected from device sdk
     * @param appId
     * @return {@link CompletableFuture<Optional<Device>>}
     */
    CompletableFuture<Optional<Device>> validateAsyncDevice(String fazpassId, String meta, String appId);

    /**
     * remove trusted device status
     *
     * @param fazpassId id that created when device is enrolled
     * @param meta      encrypted data that collected from device sdk
     * @param appId
     * @return {@link Device}
     */
    Device removeDevice(String fazpassId, String meta, String appId) throws FazpassException;

    /**
     * Async version of remove method
     *
     * @param fazpassId id that created when device is enrolled
     * @param meta      encrypted data that collected from device sdk
     * @param appId
     * @return {@link CompletableFuture<Optional<Device>>}
     */
    CompletableFuture<Optional<Device>> removeAsyncDevice(String fazpassId, String meta, String appId);

}
