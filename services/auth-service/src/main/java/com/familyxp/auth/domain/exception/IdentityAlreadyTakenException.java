package com.familyxp.auth.domain.exception;

/**
 * Thrown when a user tries to register with an email or username that is already in use.
 */
public class IdentityAlreadyTakenException extends DomainException {
    public IdentityAlreadyTakenException(String identity) {
        super(String.format("The identity '%s' is already in use.", identity));
    }
}
