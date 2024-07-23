package com.hiep.blog.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatusCode status, WebRequest request
    ) {
        var message = "Trai đẹp vô cùng tận có lỗi rồi kìa...";
        var errors = new LinkedHashMap<String, String>();
        for (var error : exception.getFieldErrors()) {
            var key = error.getField();
            var value = error.getDefaultMessage();
            errors.put(key, value);
        }
        var response = new Error(message, errors);
        return new ResponseEntity<>(response, headers, status);
    }

    // custom exception
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException exception
    ) {
        var message = "Sorry, invalid form";
        var errors = new LinkedHashMap<String, String>();
        for (var constraint : exception.getConstraintViolations()) {
            var key = constraint.getPropertyPath().toString();
            var value = constraint.getMessage();
            errors.put(key, value);
        }
        var response = new Error(message, errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
