package com.familyxp.auth.application.dto;

import com.familyxp.auth.domain.model.Role;
import java.util.UUID;

/**
 * Output DTO representing the successfully registered user.
 */
public record UserResponse(
    UUID id,
    String identity,
    Role role,
    UUID familyId
) {}
