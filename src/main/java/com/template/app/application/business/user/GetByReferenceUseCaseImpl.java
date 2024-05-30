package com.template.app.application.business.user;

import com.template.app.application.ports.input.GetByReferenceUseCase;
import com.template.app.application.ports.output.UserOutputPort;
import com.template.app.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetByReferenceUseCaseImpl implements GetByReferenceUseCase {
    private final UserOutputPort userOutputPort;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> execute(String reference) {
        return userOutputPort.findActiveByReference(reference);
    }
}
