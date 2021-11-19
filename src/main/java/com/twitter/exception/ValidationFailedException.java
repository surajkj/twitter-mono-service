package com.twitter.exception;

import lombok.Builder;

@Builder
public class ValidationFailedException extends RuntimeException implements CustomError {

    private String errorList;

    public ValidationFailedException(String errorList) {
        this.errorList = errorList;
    }

    @Override
    public String getErrorCode() {
        return ErrorCode.TWTR10001.getErrorCode();
    }

    @Override
    public String getErrorMessage() {
        return ErrorCode.TWTR10001.getErrorMessage();
    }

}
