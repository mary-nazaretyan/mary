package com.sparkers.mary.exception;

import java.util.NoSuchElementException;
import javax.persistence.EntityNotFoundException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoEntityFoundException.class, NoSuchElementException.class, EntityNotFoundException.class})
    @ResponseBody
    public ApiError handleNotFoundExceptions(Exception e) {
        log.error("handleNotFoundExceptions ", e);
        return new ApiError(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiError handleExceptions(Exception e) {
        log.error("handleExceptions", e);
        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "... whatever internal error happened ...");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiError handleExceptions(MethodArgumentNotValidException e) {
        log.error("handleExceptions", e);
        return new ApiError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
