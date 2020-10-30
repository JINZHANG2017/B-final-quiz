package com.example.demo.exception;

import com.example.demo.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ErrorMessage resourseNotFoundExceptionHandler(ResourceNotFoundException e) {
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ErrorMessage methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // TODO GTB-知识点: - 校验错误的异常处理应该提取校验注解上的message
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
