package com.template.app.application.business.user;

import com.template.app.apirest.dto.input.UserRequest;
import com.template.app.application.ports.input.UpdateUseCase;
import com.template.app.application.ports.output.UserOutputPort;
import com.template.app.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateUseCaseImpl implements UpdateUseCase {

    private final UserOutputPort userOutputPort;

    @Override
    @Transactional
    public Optional<User> execute(long id, UserRequest userRequest) {
        Optional<User> currentUserOptional = userOutputPort.findById(id);

        currentUserOptional.ifPresent(currentUser -> {
            currentUser.setFirstName(userRequest.getFirstName());
            currentUser.setLastName(userRequest.getLastName());
            currentUser.setEmail(userRequest.getEmail());
            currentUser.setNickname(userRequest.getNickname());
            currentUser.setUpdatedAt(ZonedDateTime.now());
            userOutputPort.save(currentUser);
        });

        return currentUserOptional;
    }
}
