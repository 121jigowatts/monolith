package com.jigowatts.monolith.api.controller;

import com.jigowatts.monolith.api.exception.BadRequestException;
import com.jigowatts.monolith.api.exception.NotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleMyException(NotFoundException ex, WebRequest request) {
        return this.handleExceptionInternal(ex, null, null, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleMyException(BadRequestException ex, WebRequest request) {
        return this.handleExceptionInternal(ex, null, null, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        ApiError apiError = ApiError.builder()
        .status(status.value())
        .error(status.getReasonPhrase())
        .message(ex.getMessage()).build();
        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }

}
