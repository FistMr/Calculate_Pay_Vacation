package com.pavelpuchkow.calculate.calculate_pay_vacation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<IncorrectValue> handleNullException(
            IncorrectValueException exception) {
        IncorrectValue incorrectValue = new IncorrectValue();
        incorrectValue.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorrectValue, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectValue> handleDateException(
            DateTimeParseException exception) {
        IncorrectValue incorrectValue = new IncorrectValue();
        incorrectValue.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorrectValue, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<IncorrectValue> handleNullException(
            ExternalServiceException exception) {
        IncorrectValue incorrectValue = new IncorrectValue();
        incorrectValue.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorrectValue, HttpStatus.BAD_GATEWAY);
    }

}
