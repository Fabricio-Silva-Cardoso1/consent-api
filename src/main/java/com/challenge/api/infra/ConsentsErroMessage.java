package com.challenge.api.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ConsentsErroMessage {
     private HttpStatus status;
     private String message;

}
