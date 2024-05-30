package com.template.app.application.ports.input;

import com.template.app.apirest.dto.input.UserRequest;
import com.template.app.domain.model.user.User;

import java.util.Optional;

public interface UpdateUseCase {
    Optional<User> execute(long id ,UserRequest input);
}
