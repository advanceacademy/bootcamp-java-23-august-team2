package com.aacademy.moonlight.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handelDuplicateEntry(SQLIntegrityConstraintViolationException ex){
        ErrorMessage errorMessage = new ErrorMessage();
        int index = ex.getMessage().indexOf("for");
        errorMessage.setMessage(ex.getMessage().substring(0, index - 1));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ExceptionHandler(CreatedException.class)
    public ResponseEntity<ErrorMessage> handleCreatedException(CreatedException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CREATED).body(errorMessage);
    }
    @ResponseStatus(HttpStatus.FOUND)
    @ExceptionHandler(FoundException.class)
    public ResponseEntity<ErrorMessage> handleFoundException(FoundException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FOUND).body(errorMessage);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(AcceptedException.class)
    public ResponseEntity<ErrorMessage> handleAcceptedException(AcceptedException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(errorMessage);
    }


}
