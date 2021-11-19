package com.twitter.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ControllerAdvice
public class RestControllerErrorAdvice {

    @Value("1.0")
    private String apiVersion;

    @ExceptionHandler(ValidationFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMaster> handleValidationFailedException
            (ValidationFailedException exception) {
        final ErrorMaster error = new ErrorMaster(
                apiVersion,
                exception.getErrorCode(),
                exception.getMessage(),
                new Date());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMaster> handleInvalidArgumentException(InvalidArgumentException exception) {
        final ErrorMaster error = new ErrorMaster(
                apiVersion,
                exception.getErrorCode(),
                exception.getMessage(),
                new Date());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMaster> handleResourceNotFoundException(ResourceNotFoundException exception) {
        final ErrorMaster error = new ErrorMaster(
                apiVersion,
                exception.getErrorCode(),
                exception.getMessage(),
                new Date());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMaster> handleServerErrorException(ServerErrorException exception) {
        final ErrorMaster error = new ErrorMaster(
                apiVersion,
                exception.getErrorCode(),
                exception.getMessage(),
          new Date());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
