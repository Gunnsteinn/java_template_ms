package com.template.app.application.business.user;

import com.template.app.application.ports.input.DeleteUseCase;
import com.template.app.application.ports.output.UserOutputPort;
import com.template.app.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteUseCaseImpl implements DeleteUseCase {

    private final UserOutputPort userOutputPort;

    @Override
    @Transactional
    public Optional<User> execute(long id) {
        Optional<User> user = userOutputPort.findActiveById(id);
        if (user.isEmpty()) {
            return user;
        }

        User userToDelete = user.get();
        userToDelete.setActive(false);
        userToDelete.setUpdatedAt(ZonedDateTime.now());

        return Optional.ofNullable(userOutputPort.save(userToDelete));
    }
}
