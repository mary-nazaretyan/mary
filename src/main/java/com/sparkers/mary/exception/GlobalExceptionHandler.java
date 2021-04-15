package com.sparkers.mary.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleExceptions(EntityNotFoundException e) {
        return new ResponseEntity<>(
            new ApiError(404, "Partner with id ... not found."),
            HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<ApiError> handleExceptions(InternalServerError e) {
        return new ResponseEntity<>(
            new ApiError(500, "... whatever internal error happened ..."),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<ApiError> handleExceptions(BadRequest e) {
        return new ResponseEntity<>(
            new ApiError(400, "... A string representing the validation error that occurred ..."),
            HttpStatus.BAD_REQUEST);
    }
}
