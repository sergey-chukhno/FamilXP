package com.familyxp.auth.domain.model;

import java.util.Objects;
import java.util.UUID;

/**
 * A strongly-typed unique identifier for a User.
 */
public record UserId(UUID value) {
    public UserId {
        Objects.requireNonNull(value, "User identifier cannot be null");
    }

    /**
     * Factory method to generate a new unique identifier.
     */
    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }
}
