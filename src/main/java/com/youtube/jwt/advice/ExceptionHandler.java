package com.youtube.jwt.advice;

import com.youtube.jwt.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

public class ExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(ProductNotFoundException.class)
    public Map<String, String> handleBusinessExceptions(
            ProductNotFoundException px) {

        Map<String, String> errors = new HashMap<>();
        errors.put("Error:", px.getMessage());
        return  errors;
    }
}
