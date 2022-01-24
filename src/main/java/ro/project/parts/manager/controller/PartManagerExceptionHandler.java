package ro.project.parts.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ro.project.parts.manager.CustomExceptions.PartAlreadyExistException;
import ro.project.parts.manager.CustomExceptions.PartNotFoundException;
import ro.project.parts.manager.CustomExceptions.QuantityToBigException;
import ro.project.parts.manager.model.PartErrorResponse;

@ControllerAdvice
public class PartManagerExceptionHandler {


    @Autowired
    private PartErrorResponse partErrorResponse;

    @ExceptionHandler
    public ResponseEntity<PartErrorResponse> handlePartNotFondException(final PartNotFoundException exc) {
        partErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        partErrorResponse.setMessage(exc.getMessage());
        partErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(partErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<PartErrorResponse> handleHttpMessageNotReadableException(final HttpMessageNotReadableException exc) {
        partErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        partErrorResponse.setMessage("Value inserted is not valid");
        partErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(partErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<PartErrorResponse> handleNotNegativeValue(final MethodArgumentNotValidException exc) {
        partErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        partErrorResponse.setMessage(exc.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        partErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(partErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<PartErrorResponse> handlePartAlreadyExistException(final PartAlreadyExistException exc) {
        partErrorResponse.setStatus(HttpStatus.ALREADY_REPORTED.value());
        partErrorResponse.setMessage(exc.getMessage());
        partErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(partErrorResponse, HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler
    public ResponseEntity<PartErrorResponse> handleQuantityToBigException(final QuantityToBigException exc) {
        partErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        partErrorResponse.setMessage(exc.getMessage());
        partErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(partErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<PartErrorResponse> handleException(final Exception exc) {
        partErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        partErrorResponse.setMessage(exc.getMessage());
        partErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(partErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
