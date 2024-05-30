package com.template.app.infrastructure.persistence;

import com.template.app.application.ports.output.RoleOutputPort;
import com.template.app.domain.model.user.Role;
import com.template.app.infrastructure.persistence.mapper.RoleDAOMapper;
import com.template.app.infrastructure.persistence.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class RolePersistenceAdapter implements RoleOutputPort {

    private final RoleRepository roleRepository;
    private final RoleDAOMapper roleDAOMapper;

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name)
                .map(roleDAOMapper::convertToDomain);
    }

}
