package com.template.app.application.ports.output;

import com.template.app.domain.model.user.Role;

import java.util.Optional;

public interface RoleOutputPort {
    Optional<Role> findByName(String name);
}
