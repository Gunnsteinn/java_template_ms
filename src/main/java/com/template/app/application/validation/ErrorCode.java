package com.template.app.application.validation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    USER_NOT_FOUND("User with id %d not found"),
    USER_BY_EMAIL_NOT_FOUND("No user found with email %s"),
    USER_BY_REFERENCE_NOT_FOUND("User with reference %s not found"),
    USER_COULD_NOT_BE_DELETED("User with id %d could not be deleted");

    private final String description;

    public String getCode() {
        return this.name();
    }

    public String getDescription(Object... params) {
        return format(description, params);
    }
}