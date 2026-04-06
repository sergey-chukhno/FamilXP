package com.familyxp.auth.domain.model;

import java.util.Objects;

/**
 * The User Aggregate Root.
 * Represents a person in the FamilyXP ecosystem, owning their identity and role.
 */
public class User {
    private final UserId id;
    private final FamilyId familyId;
    private final UserIdentity identity;
    private final String passwordHash;
    private final Role role;

    /**
     * Private constructor to enforce creation through static factory methods.
     */
    private User(UserId id, FamilyId familyId, UserIdentity identity, String passwordHash, Role role) {
        this.id = Objects.requireNonNull(id);
        this.familyId = Objects.requireNonNull(familyId);
        this.identity = Objects.requireNonNull(identity);
        this.passwordHash = Objects.requireNonNull(passwordHash);
        this.role = Objects.requireNonNull(role);
    }

    /**
     * Factory method for creating a Parent user.
     */
    public static User createParent(UserId id, FamilyId familyId, EmailIdentity email, String passwordHash) {
        return new User(id, familyId, email, passwordHash, Role.PARENT);
    }

    /**
     * Factory method for creating a Child user.
     */
    public static User createChild(UserId id, FamilyId familyId, UsernameIdentity username, String passwordHash) {
        return new User(id, familyId, username, passwordHash, Role.CHILD);
    }

    // Getters (Standard Clean Architecture approach: domain objects are immutable where possible)
    public UserId getId() { return id; }
    public FamilyId getFamilyId() { return familyId; }
    public UserIdentity getIdentity() { return identity; }
    public Role getRole() { return role; }
    public String getPasswordHash() { return passwordHash; }
}
