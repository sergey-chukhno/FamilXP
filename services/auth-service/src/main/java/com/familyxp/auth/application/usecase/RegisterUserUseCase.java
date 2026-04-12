package com.familyxp.auth.application.usecase;

import com.familyxp.auth.application.dto.RegisterUserCommand;
import com.familyxp.auth.application.dto.UserResponse;

/**
 * Inbound port for the Register User use case.
 */
public interface RegisterUserUseCase {
    /**
     * Executes the registration of a new user.
     * 
     * @param command The validated registration data.
     * @return UserResponse summary of the created user.
     */
    UserResponse register(RegisterUserCommand command);
}
