package com.twitter.exception;

public class ResourceNotFoundException extends TwitterException {

    public ResourceNotFoundException(ErrorCode eplErrorCode) {
        super(eplErrorCode.getErrorCode(), eplErrorCode.getErrorMessage(), null);
    }

    // New constructor is added because we want to dynamic error message that contains id.
    public ResourceNotFoundException(String errorCode,
                                     String errorMessage) {
        super(errorCode, errorMessage, null);
    }
}
