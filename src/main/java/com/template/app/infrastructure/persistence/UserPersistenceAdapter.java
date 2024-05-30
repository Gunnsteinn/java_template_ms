package com.template.app.infrastructure.persistence;

import com.template.app.application.ports.output.UserOutputPort;
import com.template.app.domain.model.user.User;
import com.template.app.infrastructure.persistence.entities.UserDAO;
import com.template.app.infrastructure.persistence.mapper.UserDAOMapper;
import com.template.app.infrastructure.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Adapter class for user persistence operations, implementing the UserOutputPort interface.
 * Handles data conversion between domain model and persistence entities.
 */
@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserOutputPort {

    private final UserRepository userRepository;
    private final UserDAOMapper userDAOMapper;

    /**
     * Finds all active users.
     *
     * @return a list of active users in the domain model format.
     */
    @Override
    public List<User> findAllActive() {
        return userDAOMapper.convertToDomainList(userRepository.findAllActive());
    }

    /**
     * Finds an active user by their ID.
     *
     * @param id the ID of the user.
     * @return an Optional containing the active user in the domain model format, if found.
     */
    @Override
    public Optional<User> findActiveById(long id) {
        return userRepository.findActiveById(id)
                .map(userDAOMapper::convertToDomain);
    }

    /**
     * Finds an active user by their reference.
     *
     * @param reference the reference of the user.
     * @return an Optional containing the active user in the domain model format, if found.
     */
    @Override
    public Optional<User> findActiveByReference(String reference) {
        return userRepository.findActiveByReference(reference)
                .map(userDAOMapper::convertToDomain);
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id).map(userDAOMapper::convertToDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findActiveByEmail(String email) {
        return userRepository.findActiveByEmail(email).map(userDAOMapper::convertToDomain);
    }

    /**
     * Saves a user.
     *
     * @param user the user in the domain model format to be saved.
     * @return the saved user in the domain model format.
     */
    @Override
    public User save(User user) {
        UserDAO userToSave = userDAOMapper.convertToDAO(user);
        UserDAO savedUser = userRepository.save(userToSave);
        return userDAOMapper.convertToDomain(savedUser);
    }
}
