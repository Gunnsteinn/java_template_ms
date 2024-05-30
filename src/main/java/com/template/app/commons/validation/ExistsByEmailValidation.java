package com.template.app.commons.validation;

import com.template.app.application.ports.input.SaveUseCase;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExistsByEmailValidation implements ConstraintValidator<ExistsByEmail, String> {

    private final SaveUseCase saveUseCase;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        boolean userExists = saveUseCase.existsByEmail(email);

        if (userExists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("User with this email exists in the database: " + email)
                    .addConstraintViolation();
        }

        return !userExists;
    }
}
