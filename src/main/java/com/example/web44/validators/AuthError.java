package com.example.web44.validators;

public enum AuthError {
    INCORRECT_USERNAME_OR_PASSWORD("Некорректное имя или пароль"),
    INVALID_PASSWORD("Пароль должен быть не менее 1 символа и включает только буквы и цифры"),
    INVALID_LOGIN("Логин должен быть не менее 1 символа и включает только буквы и цифры"),
    USER_ALREADY_EXISTS("Пользователь уже существует, придумайте другое имя или измените почту"),
    SESSION_EXPIRED("Действие токена истекло");

    private final String errorMessage;

    AuthError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
