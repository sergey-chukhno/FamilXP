package com.familyxp.auth.domain.model;

import java.util.Objects;

/**
 * Represents an identity based on a verified email address (typically for Parents).
 */
public record EmailIdentity(String value) implements UserIdentity {
    public EmailIdentity {
        Objects.requireNonNull(value, "Email cannot be null");
        if (value.isBlank() || !value.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    @Override
    public String getValue() {
        return value;
    }
}
