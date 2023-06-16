package com.fazpass.javatdv2;

import com.fazpass.javatdv2.exception.DecryptionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DecryptionExceptionTest {

    @Test
    void constructor_ShouldSetMessage() {
        // Arrange
        String message = "Decryption error occurred";

        // Act
        DecryptionException exception = new DecryptionException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}
