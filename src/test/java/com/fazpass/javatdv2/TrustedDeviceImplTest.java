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
}
