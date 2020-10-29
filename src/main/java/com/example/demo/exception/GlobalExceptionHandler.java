package com.example.demo.exception;

import com.example.demo.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ErrorMessage resourseNotFoundExceptionHandler(ResourceNotFoundException e) {
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(),e.getMessage());
    }
}
