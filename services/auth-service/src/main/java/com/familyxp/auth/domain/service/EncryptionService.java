package com.familyxp.auth.domain.service;

/**
 * Port interface for security and encryption operations.
 * Allows the domain to request sensitive data handling without knowing the technical details.
 */
public interface EncryptionService {
    /**
     * Hashes a raw password for secure storage.
     */
    String encrypt(String rawPassword);

    /**
     * Verifies if a raw password matches a stored hash.
     */
    boolean matches(String rawPassword, String encryptedPassword);
}
