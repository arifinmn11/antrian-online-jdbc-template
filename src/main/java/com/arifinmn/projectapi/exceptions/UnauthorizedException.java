package com.arifinmn.projectapi.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApplicationExceptions {
    public UnauthorizedException(String message) {
        super( HttpStatus.UNAUTHORIZED, "error." + HttpStatus.UNAUTHORIZED.value());
    }
}