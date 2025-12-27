package com.organization.mgmt.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> methodNotSupported(HttpRequestMethodNotSupportedException ex){
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Method Not Allowed");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> MissingParam(MissingServletRequestParameterException ex){
        return ResponseEntity.ok("missing param ::"+ex.getParameterName());
    }


}
