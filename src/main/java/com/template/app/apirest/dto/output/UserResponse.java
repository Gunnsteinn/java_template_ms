package com.template.app.apirest.dto.output;

import com.template.app.domain.model.user.Plans;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class UserResponse {
    private Long id;
    private String reference;
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    private Plans plan;
    private List<RoleResponse> roles;
    private Boolean active;
    private ZonedDateTime created;
    private ZonedDateTime updated;
}
