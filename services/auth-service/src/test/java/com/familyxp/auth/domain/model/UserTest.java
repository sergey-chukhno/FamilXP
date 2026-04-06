package com.familyxp.auth.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for the User Aggregate and its Value Objects.
 * Verifies that business rules are correctly enforced at construction time.
 */
class UserTest {

    @Test
    @DisplayName("Should create a valid Parent user when provided with correct data")
    void shouldCreateValidParent() {
        // Given
        var userId = UserId.generate();
        var familyId = FamilyId.generate();
        var email = new EmailIdentity("parent@familyxp.com");
        var passwordHash = "encoded_hash";

        // When
        User user = User.createParent(userId, familyId, email, passwordHash);

        // Then
        assertThat(user.getRole()).isEqualTo(Role.PARENT);
        assertThat(user.getIdentity().getValue()).isEqualTo("parent@familyxp.com");
        assertThat(user.getFamilyId()).isEqualTo(familyId);
        assertThat(user.getId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("Should create a valid Child user when provided with correct data")
    void shouldCreateValidChild() {
        // Given
        var userId = UserId.generate();
        var familyId = FamilyId.generate();
        var username = new UsernameIdentity("little_hero");
        var passwordHash = "encoded_hash";

        // When
        User user = User.createChild(userId, familyId, username, passwordHash);

        // Then
        assertThat(user.getRole()).isEqualTo(Role.CHILD);
        assertThat(user.getIdentity().getValue()).isEqualTo("little_hero");
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when creating EmailIdentity with invalid format")
    void shouldFailWhenEmailIsInvalid() {
        assertThatThrownBy(() -> new EmailIdentity("invalidemail.com"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Invalid email format");
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when creating UsernameIdentity with short username")
    void shouldFailWhenUsernameIsTooShort() {
        assertThatThrownBy(() -> new UsernameIdentity("ab"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Username is too short");
    }
}
