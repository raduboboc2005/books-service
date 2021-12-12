package com.rb.books.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(BookException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage processException(BookException exception) {
        return new ErrorMessage(INTERNAL_SERVER_ERROR.value(), String.valueOf(INTERNAL_SERVER_ERROR.value()), exception.getMessage());
    }
}
