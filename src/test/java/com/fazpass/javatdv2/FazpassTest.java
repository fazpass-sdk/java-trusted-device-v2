package com.fazpass.javatdv2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import junit.framework.TestCase;

public class FazpassTest extends TestCase {
    @Test
    void initializeShouldThrowWhenPrivateKeyIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Fazpass.initialize(null, "http://localhost"));
    }

    @Test
    void initializeShouldThrowWhenPrivateKeyIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Fazpass.initialize("", "http://localhost"));
    }

    @Test
    void initializeShouldThrowWhenBaseUrlIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Fazpass.initialize("privateKey", null));
    }

    @Test
    void initializeShouldThrowWhenBaseUrlIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Fazpass.initialize("privateKey", ""));
    }

    @Test
    void initializeShouldReturnTrustedDeviceWhenParametersAreValid() {
        TrustedDevice trustedDevice = Fazpass.initialize("privateKey", "http://localhost");
        assertNotNull(trustedDevice);
        assertTrue(trustedDevice instanceof TrustedDeviceImpl);
    }
}
