package com.template.app.application.business.user;

import com.template.app.application.ports.input.GetByIdUseCase;
import com.template.app.application.ports.output.UserOutputPort;
import com.template.app.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetByIdUseCaseImpl implements GetByIdUseCase {

    private final UserOutputPort userOutputPort;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> execute(long id) {
        return userOutputPort.findActiveById(id);
    }
}
