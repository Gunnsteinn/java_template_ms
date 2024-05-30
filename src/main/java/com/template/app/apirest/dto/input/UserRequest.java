package com.template.app.apirest.dto.input;

import com.template.app.commons.validation.ExistsByEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRequest {

    @NotNull(message = "{firstName.notNull}")
    @NotBlank(message = "{firstName.notBlank}")
    @Schema(description = "{firstName.schema}", example = "{firstName.example}")
    private String firstName;

    @NotNull(message = "{lastName.notNull}")
    @NotBlank(message = "{lastName.notBlank}")
    @Schema(description = "{lastName.schema}", example = "{lastName.example}")
    private String lastName;

    @NotNull(message = "{password.notNull}")
    @NotBlank(message = "{password.notBlank}")
    @Pattern(regexp = "^(?=.*[a-zA-Z]{5,9})(?=.*\\d{1,5})[a-zA-Z\\d]+$", message = "{password.invalid}")
    @Schema(description = "{password.schema}", example = "{password.example}")
    private String password;

    @Schema(description = "{nickname.schema}", example = "{nickname.example}")
    private String nickname;

    @NotNull(message = "{email.notNull}")
    @NotBlank(message = "{email.notBlank}")
    @Email(message = "{email.verified}")
    @Schema(description = "{email.schema}", example = "{email.example}")
    @ExistsByEmail
    private String email;
}
