package com.challenge.api.dto;

import com.challenge.api.constants.ConsentsStatus;

import java.time.LocalDateTime;

public record PutConsentByIdRequestDTO(ConsentsStatus status, LocalDateTime expirationDateTime) {
}
