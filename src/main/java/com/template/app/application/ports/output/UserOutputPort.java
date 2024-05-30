package com.template.app.application.ports.output;

import com.template.app.domain.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserOutputPort {
    List<User> findAllActive();

    Optional<User> findActiveById(long id);

    Optional<User> findActiveByReference(String id);

    Optional<User> findById(long id);

    Optional<User> findActiveByEmail(String email);

    boolean existsByEmail(String email);

    User save(User user);
}
