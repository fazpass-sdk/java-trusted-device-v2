package com.fazpass.javatdv2;

import static org.junit.jupiter.api.Assertions.*;
import junit.framework.TestCase;

import java.io.IOException;

public class FazpassTest extends TestCase {
    public void testInitializeShouldThrowWhenPrivateKeyIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Fazpass.initialize(null));
    }

    public void testInitializeShouldThrowWhenPrivateKeyIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Fazpass.initialize(""));
    }

    public void testInitializeShouldReturnTrustedDeviceWhenParametersAreValid() throws IOException {
        TrustedDevice trustedDevice = Fazpass.initialize("./private_2.key");
        assertNotNull(trustedDevice);
        assertTrue(trustedDevice instanceof TrustedDeviceImpl);
    }
}
