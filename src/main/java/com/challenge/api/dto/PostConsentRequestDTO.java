package com.challenge.api.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public record ConsentDTO(String cpf, String consentDurationTime) {

}