package com.template.app.application.business.user;

import com.template.app.application.ports.input.GetAllUseCase;
import com.template.app.application.ports.output.UserOutputPort;
import com.template.app.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUseCaseImpl implements GetAllUseCase {
    private final UserOutputPort userOutputPort;

    @Override
    @Transactional(readOnly = true)
    public List<User> execute() {
        return userOutputPort.findAllActive();
    }
}


