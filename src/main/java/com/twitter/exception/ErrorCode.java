package com.twitter.exception;

public enum ErrorCode {

    TWTR10000("TWTR-10000", "Service not working as expected"),
    TWTR10001("TWTR-10001", "Invalid input"),
    TWTR10002("TWTR-10002", "Invalid session"),
    TWTR10003("TWTR-10003", "Username or email already exists"),
    TWTR10004("TWTR-10004", "Username and password is required"),
    TWTR10005("TWTR-10005", "Session Id is required"),
    TWTR10006("TWTR-10006", "Invalid username or password"),
    TWTR10007("TWTR-10007", "Authentication is required to access this resource"),
    ;

    private final String errorCode;
    private final String errorMessage;

    ErrorCode(String errorCode,
                  String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
