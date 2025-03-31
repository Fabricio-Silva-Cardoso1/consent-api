package com.challenge.api.infra;

import com.challenge.api.dto.GetConsentByIdResponseDTO;
import com.challenge.api.exception.GetConsentByIdException;
import com.challenge.api.exception.PostConsentsDtoException;
import com.challenge.api.exception.PostConsentsException;
import com.challenge.api.exception.PutConsentByIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PostConsentsException.class)
    private ResponseEntity<ConsentsErroMessage> invalidCpf(PostConsentsException exception){
        ConsentsErroMessage handlePostBadRequest = new ConsentsErroMessage(HttpStatus.BAD_REQUEST,exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(handlePostBadRequest);
    }

    @ExceptionHandler(PostConsentsDtoException.class)
    private ResponseEntity<ConsentsErroMessage> invalidType(PostConsentsDtoException exception){
        ConsentsErroMessage handlePostBadRequest = new ConsentsErroMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(handlePostBadRequest);
    }

    @ExceptionHandler(GetConsentByIdException.class)
    private ResponseEntity<ConsentsErroMessage> consentNotFound(GetConsentByIdException exception){
        ConsentsErroMessage handleGetConsentIdNotFound = new ConsentsErroMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(handleGetConsentIdNotFound);
    }

    @ExceptionHandler(PutConsentByIdException.class)
    private ResponseEntity<ConsentsErroMessage> consentExpirationDateTimeInvalid(PutConsentByIdException exception){
        ConsentsErroMessage handlePutExpirationDateTimeInvalid = new ConsentsErroMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(handlePutExpirationDateTimeInvalid);
    }
}
