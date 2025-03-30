package com.challenge.api.service;

import com.challenge.api.dto.PostConsentRequestDTO;
import com.challenge.api.dto.PostConsentsResponseDTO;
import com.challenge.api.mapper.ConsentsMapper;
import com.challenge.api.model.Consents;
import com.challenge.api.repository.ConsentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.UUID;

public class ConsentService {

    @Autowired
    private ConsentRepository consentRepository;

    private ConsentsMapper consentsMapper;

    public PostConsentsResponseDTO postConsent(PostConsentRequestDTO postConsentRequestDTO){
        LocalDateTime creationDateTime = LocalDateTime.now();
        Consents consents = Consents
                .builder()
                .cpf(postConsentRequestDTO.cpf())
                .id(UUID.randomUUID())
                .creationDateTime(creationDateTime)
                .expirationDateTime(creationDateTime.plusMonths(Long.parseLong(postConsentRequestDTO.consentDurationTime())))
                .build();

        Consents consentsCreated = consentRepository.save(consents);

        return consentsMapper.mapConsentsToPostConsentsResponseDto(consentsCreated);
    }
}
