package com.twitter.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Getter
public class ErrorMaster {

    private final String apiVersion;
    private final ErrorBlock error;
    private final Date date;

    public ErrorMaster(final String apiVersion,
                       final String code,
                       final String message,
                       final Date date) {
        this.apiVersion = apiVersion;
        this.error = new ErrorBlock(code, message);
        this.date = date;
    }

    public static ErrorMaster fromDefaultAttributeMap(final String apiVersion,
                                                      final Map<String, Object> defaultErrorAttributes,
                                                      final String sendReportBaseUri,
                                                      final Date date) {
        // original attribute values are documented in org.springframework.boot.web.servlet.error.DefaultErrorAttributes
        return new ErrorMaster(
                apiVersion,
                ((Integer) defaultErrorAttributes.get("status")).toString(),
                sendReportBaseUri, date);
    }

    // utility method to return a map of serialized root attributes,
    // see the last part of the guide for more details
    public Map<String, Object> toAttributeMap() {

        Map map = new HashMap();
        map.put("apiVersion", apiVersion);
        //map.put("requestId", requestId);
        map.put("error", error);
        map.put("date", date);

        return map;

    }


    @Getter
    private static final class ErrorBlock {


        private final String code;
        private final String message;

        public ErrorBlock(final String code,
                          final String message) {
            this.code = code;
            this.message = message;
        }

        public static ErrorBlock copyWithMessage(final ErrorBlock s,
                                                 final String message) {
            return new ErrorBlock(s.code, message);
        }


    }

    @Data
    @AllArgsConstructor
    private static final class Error {
        private final String domain;
        private final String reason;
        private final String message;
        private final String secLangMessage;
        private final String sendReport;
    }

}
