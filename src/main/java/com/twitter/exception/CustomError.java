package com.twitter.exception;

public interface CustomError {

    String getErrorCode();

    String getErrorMessage();

    String getDetailedErrorMessage();

    String getErrorMessageSecLang();

}
