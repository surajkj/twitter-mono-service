package com.twitter.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorMaster {

    private final String apiVersion;
    private final ErrorBlock error;
    private final Date date;

    public ErrorMaster(final String apiVersion,
                       final String code,
                       final String message,
                       final Date date,
                       final String fieldName) {
        this.apiVersion = apiVersion;
        this.error = new ErrorBlock(code, message, fieldName);
        this.date = date;
    }


    @Getter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private static final class ErrorBlock {

        private final String fieldName;
        private final String code;
        private final String message;

        public ErrorBlock(final String code,
                          final String message,
                          final String fieldName) {
            this.code = code;
            this.message = message;
            this.fieldName = fieldName;
        }

        public static ErrorBlock copyWithMessage(final ErrorBlock s,
                                                 final String message,
                                                 final String fieldName) {
            return new ErrorBlock(s.code, message, fieldName);
        }


    }


}
