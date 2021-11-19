package com.twitter.exception;

public class InvalidArgumentException extends TwitterException {

    public InvalidArgumentException(String errorCode,
                                    String errorMessage) {
        super(errorCode, errorMessage, null);
    }

    public InvalidArgumentException(ErrorCode eplErrorCode) {
        super(eplErrorCode.getErrorCode(), eplErrorCode.getErrorMessage(), null);
    }

}
