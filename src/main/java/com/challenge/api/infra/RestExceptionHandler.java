package com.challenge.api.infra;

import com.challenge.api.dto.GetConsentByIdResponseDTO;
import com.challenge.api.exception.GetConsentByIdException;
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
        ConsentsErroMessage treatResponse = new ConsentsErroMessage(HttpStatus.BAD_REQUEST,exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(treatResponse);
    }

    @ExceptionHandler(PostConsentsDtoException.class)
    private ResponseEntity<ConsentsErroMessage> invalidType(PostConsentsDtoException exception){
        ConsentsErroMessage treatRequestDto = new ConsentsErroMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(treatRequestDto);
    }

    @ExceptionHandler(GetConsentByIdException.class)
    private ResponseEntity<ConsentsErroMessage> consentNotFound(GetConsentByIdException exception){
        ConsentsErroMessage treatGetConsentResponseDTO = new ConsentsErroMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(treatGetConsentResponseDTO);
    }
}
