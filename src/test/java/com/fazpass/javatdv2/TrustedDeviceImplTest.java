package com.fazpass.javatdv2;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrustedDeviceImplTest {

    @Test
    public void testExtract() throws IOException {
        TrustedDeviceImpl trustedDevice = new TrustedDeviceImpl("key.priv");
        String meta = "encrypted_meta";
        Device device = trustedDevice.extract(meta);
        assertEquals("decrypted_meta", device.getFazpassId());
    }

    @Test
    public void testExtractWithInvalidPrivateKey() throws IOException {
        TrustedDeviceImpl trustedDevice = new TrustedDeviceImpl("invalid/path/to/private_key");
        String meta = "encrypted_meta";
        try {
            trustedDevice.extract(meta);
        } catch (Exception e) {
            assertEquals("Invalid private key", e.getMessage());
        }
    }
}