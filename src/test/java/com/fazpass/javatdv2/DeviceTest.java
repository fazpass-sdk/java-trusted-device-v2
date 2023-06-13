package com.fazpass.javatdv2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DeviceTest {
    private Device device;

    @BeforeEach
    void setUp() {
        Map<String, String> deviceId = new HashMap<>();
        deviceId.put("type", "testType");
        deviceId.put("value", "testValue");

        Map<String, String> geolocation = new HashMap<>();
        geolocation.put("latitude", "123");
        geolocation.put("longitude", "456");

        device = new Device(
                "fazpassId",
                true,
                "timeStamp",
                "platform",
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                "application",
                deviceId,
                Arrays.asList("simSerial1", "simSerial2"),
                geolocation,
                1.0,
                "riskLevel"
        );
    }

    @Test
    void getFazpassId() {
        assertEquals("fazpassId", device.getFazpassId());
    }

    @Test
    void setFazpassId() {
        device.setFazpassId("newFazpassId");
        assertEquals("newFazpassId", device.getFazpassId());
    }

    @Test
    void getScoring() {
        assertEquals(1.0, device.getScoring());
    }

    @Test
    void setScoring() {
        device.setScoring(2.0);
        assertEquals(2.0, device.getScoring());
    }

    @Test
    void getRiskLevel() {
        assertEquals("riskLevel", device.getRiskLevel());
    }

    @Test
    void setRiskLevel() {
        device.setRiskLevel("newRiskLevel");
        assertEquals("newRiskLevel", device.getRiskLevel());
    }

    @Test
    void getActive() {
        assertTrue(device.getActive());
    }

    @Test
    void setActive() {
        device.setActive(false);
        assertFalse(device.getActive());
    }

    @Test
    void getTimeStamp() {
        assertEquals("timeStamp", device.getTimeStamp());
    }

    @Test
    void setTimeStamp() {
        device.setTimeStamp("newTimeStamp");
        assertEquals("newTimeStamp", device.getTimeStamp());
    }

    @Test
    void getPlatform() {
        assertEquals("platform", device.getPlatform());
    }

    @Test
    void setPlatform() {
        device.setPlatform("newPlatform");
        assertEquals("newPlatform", device.getPlatform());
    }

    @Test
    void getRooted() {
        assertTrue(device.getRooted());
    }

    @Test
    void setRooted() {
        device.setRooted(false);
        assertFalse(device.getRooted());
    }

    @Test
    void getEmulator() {
        assertTrue(device.getEmulator());
    }

    @Test
    void setEmulator() {
        device.setEmulator(false);
        assertFalse(device.getEmulator());
    }

    @Test
    void getGpsSpoof() {
        assertTrue(device.getGpsSpoof());
    }

    @Test
    void setGpsSpoof() {
        device.setGpsSpoof(false);
        assertFalse(device.getGpsSpoof());
    }

    @Test
    void getAppTempering() {
        assertTrue(device.getAppTempering());
    }

    @Test
    void setAppTempering() {
        device.setAppTempering(false);
        assertFalse(device.getAppTempering());
    }

    @Test
    void getVpn() {
        assertTrue(device.getVpn());
    }

    @Test
    void setVpn() {
        device.setVpn(false);
        assertFalse(device.getVpn());
    }

    @Test
    void getCloneApp() {
        assertTrue(device.getCloneApp());
    }

    @Test
    void setCloneApp() {
        device.setCloneApp(false);
        assertFalse(device.getCloneApp());
    }

    @Test
    void getScreenSharing() {
        assertTrue(device.getScreenSharing());
    }

    @Test
    void setScreenSharing() {
        device.setScreenSharing(false);
        assertFalse(device.getScreenSharing());
    }

    @Test
    void getDebug() {
        assertTrue(device.getDebug());
    }

    @Test
    void setDebug() {
        device.setDebug(false);
        assertFalse(device.getDebug());
    }

    @Test
    void getApplication() {
        assertEquals("application", device.getApplication());
    }

    @Test
    void setApplication() {
        device.setApplication("newApplication");
        assertEquals("newApplication", device.getApplication());
    }

    @Test
    void getDeviceId() {
        Map<String, String> deviceId = new HashMap<>();
        deviceId.put("type", "testType");
        deviceId.put("value", "testValue");
        assertEquals(deviceId, device.getDeviceId());
    }

    @Test
    void setDeviceId() {
        Map<String, String> newDeviceId = new HashMap<>();
        newDeviceId.put("type", "newTestType");
        newDeviceId.put("value", "newTestValue");
        device.setDeviceId(newDeviceId);
        assertEquals(newDeviceId, device.getDeviceId());
    }

    @Test
    void getSimSerial() {
        List<String> simSerial = Arrays.asList("simSerial1", "simSerial2");
        assertEquals(simSerial, device.getSimSerial());
    }

    @Test
    void setSimSerial() {
        List<String> newSimSerial = Arrays.asList("newSimSerial1", "newSimSerial2");
        device.setSimSerial(newSimSerial);
        assertEquals(newSimSerial, device.getSimSerial());
    }

    @Test
    void getGeolocation() {
        Map<String, String> geolocation = new HashMap<>();
        geolocation.put("latitude", "123");
        geolocation.put("longitude", "456");
        assertEquals(geolocation, device.getGeolocation());
    }

    @Test
    void setGeolocation() {
        Map<String, String> newGeolocation = new HashMap<>();
        newGeolocation.put("latitude", "789");
        newGeolocation.put("longitude", "012");
        device.setGeolocation(newGeolocation);
        assertEquals(newGeolocation, device.getGeolocation());
    }
}
