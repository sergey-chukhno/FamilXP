package com.familyxp.auth.application.usecase;

import com.familyxp.auth.application.dto.RegisterUserCommand;
import com.familyxp.auth.application.dto.UserResponse;
import com.familyxp.auth.domain.exception.IdentityAlreadyTakenException;
import com.familyxp.auth.domain.model.*;
import com.familyxp.auth.domain.repository.UserRepository;
import com.familyxp.auth.domain.service.EncryptionService;
import java.util.UUID;

/**
 * Application service implementing the Register User use case.
 * Orchestrates domain objects and ports to fulfill the business request.
 */
public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final EncryptionService encryptionService;

    public RegisterUserService(UserRepository userRepository, EncryptionService encryptionService) {
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
    }

    @Override
    public UserResponse register(RegisterUserCommand command) {
        // 1. Resolve identity type based on role
        UserIdentity identity = resolveIdentity(command);

        // 2. Check for duplicate identity
        if (userRepository.findByIdentity(identity).isPresent()) {
            throw new IdentityAlreadyTakenException(identity.getValue());
        }

        // 3. Encrypt password
        String passwordHash = encryptionService.encrypt(command.password());

        // 4. Create User Aggregate
        User user;
        UserId userId = UserId.generate();
        
        // Handle FamilyId (Generate new for Parent if null, use existing for Child)
        FamilyId familyId = (command.familyId() != null) 
            ? new FamilyId(command.familyId()) 
            : FamilyId.generate();

        if (command.role() == Role.PARENT) {
            user = User.createParent(userId, familyId, (EmailIdentity) identity, passwordHash);
        } else {
            user = User.createChild(userId, familyId, (UsernameIdentity) identity, passwordHash);
        }

        // 5. Persist
        userRepository.save(user);

        // 6. Return response DTO
        return new UserResponse(
            user.getId().value(),
            user.getIdentity().getValue(),
            user.getRole(),
            user.getFamilyId().value()
        );
    }

    private UserIdentity resolveIdentity(RegisterUserCommand command) {
        return switch (command.role()) {
            case PARENT -> new EmailIdentity(command.identity());
            case CHILD -> new UsernameIdentity(command.identity());
        };
    }
}
