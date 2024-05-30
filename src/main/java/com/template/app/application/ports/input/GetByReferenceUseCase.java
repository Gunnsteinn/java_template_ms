package com.template.app.application.ports.input;

import com.template.app.domain.model.user.User;

import java.util.Optional;

public interface GetByReferenceUseCase {
    Optional<User> execute(String reference);
}
