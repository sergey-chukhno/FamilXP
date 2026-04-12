package com.familyxp.auth.application.dto;

import com.familyxp.auth.domain.model.Role;
import java.util.UUID;

/**
 * Input DTO for user registration.
 * 
 * @param identity The email (for parents) or username (for children).
 * @param password The raw password to be encrypted.
 * @param role The requested role (PARENT or CHILD).
 * @param familyId Optional. Required for CHILD registration, usually omitted for PARENT.
 */
public record RegisterUserCommand(
    String identity,
    String password,
    Role role,
    UUID familyId
) {}
