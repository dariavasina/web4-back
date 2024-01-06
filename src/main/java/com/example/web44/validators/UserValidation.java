package com.example.web44.validators;

public class UserValidation {
    private boolean isValidUsername(String username) {
        return username != null && username.matches("[a-zA-Z0-9]+") ;
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 1 && password.matches("[a-zA-Z0-9]+");
    }

    public AuthError validateUser(String username, String password, String email) {
        if (!isValidUsername(username)) {
            return AuthError.INVALID_LOGIN;
        }

        if (!isValidPassword(password)) {
            return AuthError.INVALID_PASSWORD;
        }

        return null;
    }
    public AuthError validateUser(String username, String password) {
        if (!isValidUsername(username)) {
            return AuthError.INVALID_LOGIN;
        }

        if (!isValidPassword(password)) {
            return AuthError.INVALID_PASSWORD;
        }
        return null;
    }
}
