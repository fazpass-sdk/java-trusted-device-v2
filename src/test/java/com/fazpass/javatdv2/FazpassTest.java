package com.fazpass.javatdv2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import junit.framework.TestCase;

public class FazpassTest extends TestCase {
    @Test
    void initializeShouldThrowWhenPrivateKeyIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Fazpass.initialize(null));
    }

    @Test
    void initializeShouldThrowWhenPrivateKeyIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Fazpass.initialize(""));
    }

    @Test
    void initializeShouldReturnTrustedDeviceWhenParametersAreValid() {
        TrustedDevice trustedDevice = Fazpass.initialize("privateKey");
        assertNotNull(trustedDevice);
        assertTrue(trustedDevice instanceof TrustedDeviceImpl);
    }
}
