package com.template.app.application.ports.input;

import com.template.app.domain.model.user.User;

import java.util.Optional;

public interface DeleteUseCase {
    Optional<User> execute(long id);
}
