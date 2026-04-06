package com.familyxp.auth.domain.exception;

/**
 * Base exception for all business rule violations in the Auth Domain.
 */
public abstract class DomainException extends RuntimeException {
    protected DomainException(String message) {
        super(message);
    }
}
