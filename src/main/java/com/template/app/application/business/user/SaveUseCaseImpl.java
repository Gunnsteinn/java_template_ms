package com.template.app.application.business.user;

import com.template.app.application.ports.input.SaveUseCase;
import com.template.app.application.ports.output.RoleOutputPort;
import com.template.app.application.ports.output.UserOutputPort;
import com.template.app.domain.model.user.Role;
import com.template.app.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SaveUseCaseImpl implements SaveUseCase {

    private final UserOutputPort userOutputPort;
    private final RoleOutputPort roleOutputPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Optional<User> execute(User user) {
        Optional<Role> optionalRoleUser = roleOutputPort.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();

        optionalRoleUser.ifPresent(roles::add);

        if (user.isAdmin()) {
            Optional<Role> optionalRoleAdmin = roleOutputPort.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        User userToSave =
                User.builder()
                        .reference(UUID.randomUUID().toString())
                        .password(passwordEncoder.encode(user.getPassword()))
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .nickname(user.getNickname())
                        .email(user.getEmail())
                        .roles(roles)
                        .isActive(true)
                        .createdAt(ZonedDateTime.now())
                        .updatedAt(ZonedDateTime.now())
                        .build();
        return Optional.ofNullable(userOutputPort.save(userToSave));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userOutputPort.existsByEmail(email);
    }
}
