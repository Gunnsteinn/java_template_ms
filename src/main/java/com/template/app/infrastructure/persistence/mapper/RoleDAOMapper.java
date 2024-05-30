package com.template.app.infrastructure.persistence.mapper;

import com.template.app.domain.model.user.Role;
import com.template.app.domain.model.user.User;
import com.template.app.infrastructure.persistence.entities.RoleDAO;
import com.template.app.infrastructure.persistence.entities.UserDAO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleDAOMapper {

    private final ModelMapper modelMapper;

    public RoleDAO convertToDAO(Role role) {
        return modelMapper.map(role, RoleDAO.class);
    }

    public Role convertToDomain(RoleDAO roleDAO) {
        return modelMapper.map(roleDAO, Role.class);
    }

    public List<Role> convertToDomainList(List<RoleDAO> roleDAO) {
        return roleDAO.stream().map(this::convertToDomain).toList();
    }
}
