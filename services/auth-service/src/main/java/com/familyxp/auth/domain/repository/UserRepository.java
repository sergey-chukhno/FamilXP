package com.familyxp.auth.domain.repository;

import com.familyxp.auth.domain.model.User;
import com.familyxp.auth.domain.model.UserId;
import com.familyxp.auth.domain.model.UserIdentity;
import java.util.Optional;

/**
 * Port interface for User persistence operations.
 * Defined in the Domain layer to specify what storage capabilities are needed.
 */
public interface UserRepository {
    /**
     * Persists the given user aggregate.
     */
    void save(User user);

    /**
     * Finds a user by their unique record identifier.
     */
    Optional<User> findById(UserId id);

    /**
     * Finds a user by their identity (Email or Username).
     * Useful for login and duplicate check scenarios.
     */
    Optional<User> findByIdentity(UserIdentity identity);
}
