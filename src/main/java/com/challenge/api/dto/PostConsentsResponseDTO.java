package com.challenge.api.dto;

import com.challenge.api.constants.ConsentsStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record PostConsentsResponseDTO(UUID id, LocalDateTime creationDateTime, LocalDateTime expirationDateTime, String cpf, ConsentsStatus status) {
}
