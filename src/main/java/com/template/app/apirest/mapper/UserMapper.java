package com.template.app.apirest.mapper;

import com.template.app.apirest.dto.input.UserRequest;
import com.template.app.apirest.dto.output.UserResponse;
import com.template.app.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public User convertToDomain(UserRequest userRequestDTO) {
        return modelMapper.map(userRequestDTO, User.class);
    }

    public UserResponse convertToResponseFromDomain(User user) {
        return this.modelMapper.map(user, UserResponse.class);
    }

    public List<UserResponse> convertToResponseListFromDomainList(List<User> users) {
        return users.stream().map(this::convertToResponseFromDomain).toList();
    }

}