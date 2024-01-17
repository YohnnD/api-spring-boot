package com.api.users.exceptions;

public class BadRequestException extends RuntimeException {
    private static final String DESCRIPTION = "Bad Request Exception (400)";

    public BadRequestException(String detail) {
        super(!detail.isEmpty() ? detail : DESCRIPTION );
    }
}