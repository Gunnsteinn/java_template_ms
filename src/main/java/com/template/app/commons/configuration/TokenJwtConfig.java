package com.template.app.commons.configuration;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public class TokenJwtConfig {

    // Private constructor to hide the implicit public one
    private TokenJwtConfig() {
    }

    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
    public static final String TOKEN_INVALID_MESSAGE = "The JWT token is invalid!";
    public static final String ERROR_KEY = "error";
    public static final String MESSAGE_KEY = "message";
}
