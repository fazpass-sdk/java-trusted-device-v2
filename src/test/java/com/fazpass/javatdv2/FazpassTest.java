package com.fazpass.javatdv2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FazpassTest {
    @Test
    void testInitializeShouldThrowWhenPrivateKeyIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Fazpass.initialize(null));
    }
    @Test
    void testInitializeShouldThrowWhenPrivateKeyIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Fazpass.initialize(""));
    }

    @Test
    public void testInitializeShouldReturnTrustedDeviceWhenParametersAreValid() throws IOException {
        TrustedDevice trustedDevice = Fazpass.initialize("./private_2.key");
        assertNotNull(trustedDevice);
        assertTrue(trustedDevice instanceof TrustedDeviceImpl);
    }
}
