package com.dept.firstAssigmentDept.controller;

import com.dept.firstAssigmentDept.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    private final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception) {
        return new ResponseEntity<>(createExceptionBody(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    private Map<String, Object> createExceptionBody(String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().format(DATE_TIME_FORMAT));
        body.put("message", message);
        return body;
    }
}
