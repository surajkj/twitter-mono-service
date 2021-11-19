package com.twitter.exception;

public abstract class TwitterException extends RuntimeException implements CustomError {

    String errorCode;
    String errorMessage;
    String errorMessageSecLang;

    public TwitterException(String errorCode,
                            String errorMessage,
                            String errorMessageSecLang) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorMessageSecLang = errorMessageSecLang;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String getDetailedErrorMessage() {
        return getErrorMessage();
    }

    public String getErrorMessageSecLang() {
        return errorMessageSecLang;
    }
}
