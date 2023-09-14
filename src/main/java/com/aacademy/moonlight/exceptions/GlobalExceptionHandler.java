package com.aacademy.moonlight.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGeneralException(Exception ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        fieldErrors.put("exceptionDetails", ex.getMessage());

        ErrorMessage errorMessage = new ErrorMessage("Internal Server Error", null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handelDuplicateEntry(SQLIntegrityConstraintViolationException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        int index = ex.getMessage().indexOf("for");
        errorMessage.setMessage(ex.getMessage().substring(0, index - 1));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationException(MethodArgumentNotValidException ex) {

        MethodArgumentNotValidException exception = ex;

        Map<String, String> fieldsErrors = new HashMap<>();
        for (FieldError error : ex.getFieldErrors()) {
            fieldsErrors.put(error.getField(), error.getDefaultMessage());
        }
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage("There are some violations");
        errorMessage.setFieldsViolated(fieldsErrors);

        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        fieldErrors.put("exceptionDetails", ex.getMessage());

        ErrorMessage errorMessage = new ErrorMessage("Resource Not Found", fieldErrors);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException ex) {

        Map<String, String> fieldErrors = new HashMap<>();
        fieldErrors.put("exceptionDetails", ex.getMessage());

        ErrorMessage errorMessage = new ErrorMessage("Unauthorized", fieldErrors);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErrorMessage> handleAuthorizationException(AuthorizationException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        fieldErrors.put("exceptionDetails", ex.getMessage());

        ErrorMessage errorMessage = new ErrorMessage("Forbidden", fieldErrors);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
    }
}
