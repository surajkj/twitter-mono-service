package com.twitter.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

import static com.twitter.exception.ErrorCode.TWTR10001;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ErrorMaster errors = null;
        if(!ex.getBindingResult().getAllErrors().isEmpty()){
            ObjectError error =  ex.getBindingResult().getAllErrors().get(0);
            String fieldName = ((FieldError) error).getField();
            errors = new ErrorMaster("1.0", TWTR10001.getErrorCode(), error.getDefaultMessage(), new Date(), fieldName);
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
