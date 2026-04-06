package com.familyxp.auth.domain.model;

/**
 * A sealed interface representing a user's unique identity.
 * Using seals ensures that identities can only be of specific types (Email or Username).
 */
public sealed interface UserIdentity permits EmailIdentity, UsernameIdentity {
    /**
     * Gets the raw string value of the identity.
     */
    String getValue();
}
