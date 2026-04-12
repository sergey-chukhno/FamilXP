package com.familyxp.auth.application.usecase;

import com.familyxp.auth.application.dto.RegisterUserCommand;
import com.familyxp.auth.application.dto.UserResponse;
import com.familyxp.auth.domain.exception.IdentityAlreadyTakenException;
import com.familyxp.auth.domain.model.*;
import com.familyxp.auth.domain.repository.UserRepository;
import com.familyxp.auth.domain.service.EncryptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EncryptionService encryptionService;

    @InjectMocks
    private RegisterUserService registerUserService;

    private RegisterUserCommand parentCommand;

    @BeforeEach
    void setUp() {
        parentCommand = new RegisterUserCommand(
            "parent@familyxp.com",
            "secure_pw",
            Role.PARENT,
            null
        );
    }

    @Test
    @DisplayName("Should successfully register a new parent when identity is unique")
    void shouldRegisterParentSuccessfully() {
        // Given
        when(userRepository.findByIdentity(any())).thenReturn(Optional.empty());
        when(encryptionService.encrypt(anyString())).thenReturn("hashed_pw");

        // When
        UserResponse response = registerUserService.register(parentCommand);

        // Then
        assertThat(response.identity()).isEqualTo(parentCommand.identity());
        assertThat(response.role()).isEqualTo(Role.PARENT);
        assertThat(response.id()).isNotNull();
        assertThat(response.familyId()).isNotNull(); // New family generated

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("Should throw IdentityAlreadyTakenException when identity already exists")
    void shouldThrowExceptionWhenIdentityAlreadyTaken() {
        // Given
        // We use a real User instance instead of a mock to avoid ByteBuddy/Java 24 issues
        User existingUser = User.createParent(
            UserId.generate(),
            FamilyId.generate(),
            new EmailIdentity(parentCommand.identity()),
            "hashed_pw"
        );
        when(userRepository.findByIdentity(any())).thenReturn(Optional.of(existingUser));

        // When / Then
        assertThatThrownBy(() -> registerUserService.register(parentCommand))
            .isInstanceOf(IdentityAlreadyTakenException.class)
            .hasMessageContaining(parentCommand.identity());

        verify(userRepository, never()).save(any(User.class));
    }
}
