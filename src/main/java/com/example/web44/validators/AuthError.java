package com.example.web44.validators;

public enum AuthError {
    INCORRECT_USERNAME_OR_PASSWORD("Wrong username or password"),
    INVALID_PASSWORD("Password must be longer than 1 symbol and contain only letters and numbers"),
    INVALID_LOGIN("Username must be longer than 1 symbol and contain only letters and numbers"),
    USER_ALREADY_EXISTS("User with such login already exists"),
    SESSION_EXPIRED("Token has expired");

    private final String errorMessage;

    AuthError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
