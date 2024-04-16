package com.aimaginarium.exception.handler;

import com.aimaginarium.exception.ErrorDetails;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

import static com.aimaginarium.exception.ErrorDetails.getResponseEntityErrorsMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleValidationErrors(HttpServletRequest request,
                                                               MethodArgumentNotValidException ex) {
        Map<String, String> errorsMap = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return getResponseEntityErrorsMap(request.getRequestURI(), HttpStatus.BAD_REQUEST, errorsMap);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEntityNotFoundException(HttpServletRequest request,
                                                                      EntityNotFoundException ex) {
        return getResponseEntityErrorsMap(request.getRequestURI(), HttpStatus.BAD_REQUEST, makeMapFromException(ex));
    }

    private Map<String, String> makeMapFromException(Exception exceptions) {
        return Map.of(exceptions.getClass().getSimpleName(), exceptions.getLocalizedMessage());
    }
}
