package com.familyxp.auth.domain.model;

import java.util.Objects;
import java.util.UUID;

/**
 * A strongly-typed unique identifier for a Family.
 */
public record FamilyId(UUID value) {
    public FamilyId {
        Objects.requireNonNull(value, "Family identifier cannot be null");
    }

    /**
     * Factory method to generate a new unique identifier.
     */
    public static FamilyId generate() {
        return new FamilyId(UUID.randomUUID());
    }
}
