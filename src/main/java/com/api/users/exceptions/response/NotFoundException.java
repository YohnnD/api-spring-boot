package com.api.users.exceptions.response;

public class NotFoundException extends RuntimeException {

    private static final String DESCRIPTION = "Not Found Exception (404)";

    public NotFoundException(String detail) {
        super(!detail.isEmpty() ? detail : DESCRIPTION);
    }
}
