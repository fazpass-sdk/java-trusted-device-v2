package com.fazpass.javatdv2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DeviceTest {
    private Meta device;

    @BeforeEach
    void setUp() {
        Map<String, String> deviceId = new HashMap<>();
        deviceId.put("type", "testType");
        deviceId.put("value", "testValue");

        Map<String, String> geolocation = new HashMap<>();
        geolocation.put("latitude", "123");
        geolocation.put("longitude", "456");
        device = new Meta();
        device.setFazpassId("fazpassId");
        device.setScoring(1.0);
        device.setRiskLevel("riskLevel");
        device.setIsActive(true);
        device.setTimeStamp("timeStamp");
        device.setPlatform("platform");
        device.setIsRooted(true);
        device.setIsEmulator(true);
        device.setIsGpsSpoof(true);
        device.setIsAppTempering(true);
        device.setIsCloneApp(true);
        device.setIsDebug(true);
        device.setSimSerial(Arrays.asList("simSerial1", "simSerial2"));
        device.setSimOperator(Arrays.asList("operator 1", "operator 2"));
        device.setClientIp("127.0.0.1");
        device.setApplication("application");
        device.setGeolocation(geolocation);
        device.setDevice(new Device());
        device.setIsVpn(true);
        device.setIsScreenSharing(true);
    }

    @Test
    void TestConstructor(){
        Device device = new Device("exampleName", "exampleVersion", "exampleSeries", "exampleCPU", "exampleID");
        Map<String, String> a = new HashMap<>();
        Meta meta = new Meta(
                "fazpass_id", true, 1.0, "HIGH", "timestamp", "platform", true, true, true, true, true, 
                false, true, false, "application_id", device, Arrays.asList("a", "b"), 
                Arrays.asList("a", "b"), a, "", Arrays.asList(device), true
        );

        assertEquals("fazpass_id", meta.getFazpassId());
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
        assertTrue(device.getIsActive());
    }

    @Test
    void setActive() {
        device.setIsActive(false);
        assertFalse(device.getIsActive());
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
        assertTrue(device.getIsRooted());
    }

    @Test
    void setRooted() {
        device.setIsRooted(false);
        assertFalse(device.getIsRooted());
    }

    @Test
    void getEmulator() {
        assertTrue(device.getIsEmulator());
    }

    @Test
    void setEmulator() {
        device.setIsEmulator(false);
        assertFalse(device.getIsEmulator());
    }

    @Test
    void getGpsSpoof() {
        assertTrue(device.getIsGpsSpoof());
    }

    @Test
    void setGpsSpoof() {
        device.setIsGpsSpoof(false);
        assertFalse(device.getIsGpsSpoof());
    }

    @Test
    void getAppTempering() {
        assertTrue(device.getIsAppTempering());
    }

    @Test
    void setAppTempering() {
        device.setIsAppTempering(false);
        assertFalse(device.getIsAppTempering());
    }

    @Test
    void getVpn() {
        assertTrue(device.getIsVpn());
    }

    @Test
    void setVpn() {
        device.setIsVpn(false);
        assertFalse(device.getIsVpn());
    }

    @Test
    void getCloneApp() {
        assertTrue(device.getIsCloneApp());
    }

    @Test
    void setCloneApp() {
        device.setIsCloneApp(false);
        assertFalse(device.getIsCloneApp());
    }

    @Test
    void getScreenSharing() {
        assertTrue(device.getIsScreenSharing());
    }

    @Test
    void setScreenSharing() {
        device.setIsScreenSharing(false);
        assertFalse(device.getIsScreenSharing());
    }

    @Test
    void getDebug() {
        assertTrue(device.getIsDebug());
    }

    @Test
    void setDebug() {
        device.setIsDebug(false);
        assertFalse(device.getIsDebug());
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
        Device d = new Device();
        assertEquals(d, device.getDevice());
    }

    @Test
    void setDeviceId() {
        Device d = new Device();
        device.setDevice(new Device());
        assertEquals(d.getId(), device.getDevice().getId());
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

    @Test
    void getSimOperator() {
        List<String> simSerial = Arrays.asList("operator 1", "operator 2");
        assertEquals(simSerial, device.getSimOperator());
    }

    @Test
    void setSimOperator() {
        device.setSimOperator(Arrays.asList("operator 1", "operator 2"));
        assertEquals(Arrays.asList("operator 1", "operator 2"), device.getSimOperator());
    }

    @Test
    void getClientIp() {
        assertEquals("127.0.0.1", device.getClientIp());
    }

    @Test
    void setClientIp() {
        device.setApplication("127.0.0.1");
        assertEquals("127.0.0.1", device.getClientIp());
    }
}
