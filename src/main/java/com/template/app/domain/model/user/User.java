package com.template.app.domain.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String reference;
    private String password;
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    private Plans plan;
    private List<Role> roles;
    //private List<Organization> organization;
    private boolean isAdmin;
    private boolean isActive;
    private ZonedDateTime updatedAt;
    private ZonedDateTime createdAt;
    private Integer version;
}
