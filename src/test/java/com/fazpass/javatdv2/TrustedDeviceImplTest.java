package com.fazpass.javatdv2;

import com.fazpass.javatdv2.exception.FazpassException;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.when;

public class TrustedDeviceImplTest extends TestCase {
    @Test
    public void testCheckDevice() throws FazpassException {
        // membuat mock
        TrustedDevice trustedDevice = Mockito.mock(TrustedDeviceImpl.class);

        // membuat dummy Device
        Device device = new Device();
        device.setFazpassId("fazpass_id");

        // menentukan perilaku mock
        when(trustedDevice.checkDevice("pic_id", "meta_data")).thenReturn(device);

        // memanggil metode yang diuji
        Device result = trustedDevice.checkDevice("pic_id", "meta_data");

        // verifikasi
        assertNotNull(result);
        assertEquals("fazpass_id", result.getFazpassId());
    }

    @Test
    public void testCheckAsyncDevice() throws ExecutionException, InterruptedException {
        // membuat mock
        TrustedDevice trustedDevice = Mockito.mock(TrustedDeviceImpl.class);

        // membuat dummy Device
        Device device = new Device();
        device.setFazpassId("fazpass_id");

        // menentukan perilaku mock
        when(trustedDevice.checkAsyncDevice("pic_id", "meta_data"))
                .thenReturn(CompletableFuture.completedFuture(Optional.of(device)));

        // memanggil metode yang diuji
        Optional<Device> result = trustedDevice.checkAsyncDevice("pic_id", "meta_data").get();

        // verifikasi
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals("fazpass_id", result.get().getFazpassId());
    }

    @Test
    public void testEnrollDevice() throws FazpassException {
        // membuat mock
        TrustedDevice trustedDevice = Mockito.mock(TrustedDeviceImpl.class);

        // membuat dummy Device
        Device device = new Device();
        device.setFazpassId("fazpass_id");

        // menentukan perilaku mock
        when(trustedDevice.enrollDevice("pic_id", "meta_data")).thenReturn(device);

        // memanggil metode yang diuji
        Device result = trustedDevice.enrollDevice("pic_id", "meta_data");

        // verifikasi
        assertNotNull(result);
        assertEquals("fazpass_id", result.getFazpassId());
    }

    @Test
    public void testEnrollAsyncDevice() throws ExecutionException, InterruptedException {
        // membuat mock
        TrustedDevice trustedDevice = Mockito.mock(TrustedDeviceImpl.class);

        // membuat dummy Device
        Device device = new Device();
        device.setFazpassId("fazpass_id");

        // menentukan perilaku mock
        when(trustedDevice.enrollAsyncDevice("pic_id", "meta_data"))
                .thenReturn(CompletableFuture.completedFuture(Optional.of(device)));

        // memanggil metode yang diuji
        Optional<Device> result = trustedDevice.enrollAsyncDevice("pic_id", "meta_data").get();

        // verifikasi
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals("fazpass_id", result.get().getFazpassId());
    }

    @Test
    public void testValidateDevice() throws FazpassException {
        // membuat mock
        TrustedDevice trustedDevice = Mockito.mock(TrustedDeviceImpl.class);

        // membuat dummy Device
        Device device = new Device();
        device.setFazpassId("fazpass_id");
        device.setScoring(90.0);

        // menentukan perilaku mock
        when(trustedDevice.validateDevice("fazpass_id", "meta_data")).thenReturn(device);

        // memanggil metode yang diuji
        Device result = trustedDevice.validateDevice("fazpass_id", "meta_data");

        // verifikasi
        assertNotNull(result);
        assertEquals(90.0, result.getScoring());
    }

    @Test
    public void testValidateAsyncDevice() throws ExecutionException, InterruptedException {
        // membuat mock
        TrustedDevice trustedDevice = Mockito.mock(TrustedDeviceImpl.class);

        // membuat dummy Device
        Device device = new Device();
        device.setFazpassId("fazpass_id");
        device.setScoring(90.0);
        // menentukan perilaku mock
        when(trustedDevice.validateAsyncDevice("fazpass_id", "meta_data"))
                .thenReturn(CompletableFuture.completedFuture(Optional.of(device)));

        // memanggil metode yang diuji
        Optional<Device> result = trustedDevice.validateAsyncDevice("fazpass_id", "meta_data").get();

        // verifikasi
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(90.0, result.get().getScoring());
    }

    @Test
    public void testRemoveDevice() throws FazpassException {
        // membuat mock
        TrustedDevice trustedDevice = Mockito.mock(TrustedDeviceImpl.class);

        // membuat dummy Device
        Device device = new Device();
        device.setFazpassId("fazpass_id");

        // menentukan perilaku mock
        when(trustedDevice.removeDevice("pic_id", "meta_data")).thenReturn(device);

        // memanggil metode yang diuji
        Device result = trustedDevice.removeDevice("pic_id", "meta_data");

        // verifikasi
        assertNotNull(result);
        assertEquals("fazpass_id", result.getFazpassId());
    }

    @Test
    public void testRemoveAsyncDevice() throws ExecutionException, InterruptedException {
        // membuat mock
        TrustedDevice trustedDevice = Mockito.mock(TrustedDeviceImpl.class);

        // membuat dummy Device
        Device device = new Device();
        device.setFazpassId("fazpass_id");

        // menentukan perilaku mock
        when(trustedDevice.removeAsyncDevice("pic_id", "meta_data"))
                .thenReturn(CompletableFuture.completedFuture(Optional.of(device)));

        // memanggil metode yang diuji
        Optional<Device> result = trustedDevice.removeAsyncDevice("pic_id", "meta_data").get();

        // verifikasi
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals("fazpass_id", result.get().getFazpassId());
    }

}
