package com.twitter.exception;

public enum ErrorCode {

    TWTR10000("EPL-10000", "Service not working as expected"),
    TWTR10001("EPL-10001", "Invalid input"),
    TWTR10002("EPL-10002", "Invalid session"),
    TWTR10003("EPL-10003", "Username or email already exists"),

    ;

    private String errorCode;
    private String errorMessage;

    ErrorCode(String errorCode,
                  String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
