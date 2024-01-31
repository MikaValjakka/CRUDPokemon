package xyz.mikavee.CRUDDEmo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder("Validation error(s): ");
        ex.getBindingResult().getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
        return ResponseEntity.badRequest().body(errors.toString());
    }
}