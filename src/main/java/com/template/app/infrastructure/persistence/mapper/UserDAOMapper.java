package com.template.app.infrastructure.persistence.mapper;


import com.template.app.domain.model.user.User;
import com.template.app.infrastructure.persistence.entities.UserDAO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDAOMapper {
    private final ModelMapper modelMapper;

    public UserDAO convertToDAO(User user) {
        return modelMapper.map(user, UserDAO.class);
    }

    public User convertToDomain(UserDAO userDAO) {
        return modelMapper.map(userDAO, User.class);
    }

    public List<User> convertToDomainList(List<UserDAO> userDAO) {
        return userDAO.stream().map(this::convertToDomain).toList();
    }

}
