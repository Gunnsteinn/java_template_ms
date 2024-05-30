package com.template.app.application.ports.input;

import com.template.app.domain.model.user.User;

import java.util.List;

public interface GetAllUseCase {
    List<User> execute();
}
