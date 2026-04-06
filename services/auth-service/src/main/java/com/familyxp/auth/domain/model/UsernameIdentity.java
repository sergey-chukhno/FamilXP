package com.familyxp.auth.domain.model;

import java.util.Objects;

/**
 * Represents an identity based on a username (typically for Children).
 */
public record UsernameIdentity(String value) implements UserIdentity {
    public UsernameIdentity {
        Objects.requireNonNull(value, "Username cannot be null");
        if (value.isBlank() || value.length() < 3) {
            throw new IllegalArgumentException("Username is too short");
        }
    }

    @Override
    public String getValue() {
        return value;
    }
}
