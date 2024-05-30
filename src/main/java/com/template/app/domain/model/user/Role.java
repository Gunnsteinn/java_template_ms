package com.template.app.domain.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private long id;
    private String reference;
    private String name;
    private ZonedDateTime updatedAt;
    private ZonedDateTime createdAt;
    private Integer version;
}