package com.anshu.springboot.musicDemo.controller.exceptionController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(value = {ResourceNotFoundException.class, ConversionFailedException.class, NumberFormatException.class})
    protected ResponseEntity<Object> handleNotFoundType(RuntimeException ex) {
        Map<String, Boolean> body = new HashMap<>();
        body.put("success", false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
