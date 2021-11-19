package com.twitter.exception;

public class ServerErrorException extends TwitterException {
    public ServerErrorException(String errorCode,
                                String errorMessage) {
        super(errorCode, errorMessage, null);
    }

    public ServerErrorException(ErrorCode eplErrorCode) {
        super(eplErrorCode.getErrorCode(), eplErrorCode.getErrorMessage(), null);
    }
}
