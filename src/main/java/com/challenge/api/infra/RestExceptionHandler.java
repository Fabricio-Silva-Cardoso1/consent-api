package com.challenge.api.infra;

import com.challenge.api.exception.PostConsentsDtoException;
import com.challenge.api.exception.PostConsentsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PostConsentsException.class)
    private ResponseEntity<ConsentsErroMessage> invalidCpf(PostConsentsException exception){
        ConsentsErroMessage threatResponse = new ConsentsErroMessage(HttpStatus.BAD_REQUEST,exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

    @ExceptionHandler(PostConsentsDtoException.class)
    private ResponseEntity<ConsentsErroMessage> invalidType(PostConsentsDtoException exception){
        ConsentsErroMessage threatRequestDto = new ConsentsErroMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatRequestDto);
    }
}
