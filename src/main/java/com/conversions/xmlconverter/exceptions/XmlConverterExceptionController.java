package com.conversions.xmlconverter.exceptions;

import java.io.IOException;

import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.conversions.xmlconverter.constants.ErrorConstants;

@ControllerAdvice
public class XmlConverterExceptionController {

    @ExceptionHandler(value = NoFileIdentifierException.class)
    public ResponseEntity<Object> handleException(NoFileIdentifierException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(ErrorConstants.NOT_FOUND));
    }

    @ExceptionHandler(value = JsonParseException.class)
    public ResponseEntity<Object> handleException(JsonParseException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(ErrorConstants.PARSE_ERROR));
    }

    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<Object> handleException(IOException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(ErrorConstants.IO_ERROR));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(ErrorConstants.UNKNOWN_ERROR));
    }

}
