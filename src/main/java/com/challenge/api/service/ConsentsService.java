package com.challenge.api.service;

import com.challenge.api.constants.ConsentsStatus;
import com.challenge.api.dto.PostConsentRequestDTO;
import com.challenge.api.mapper.ConsentsMapper;
import com.challenge.api.model.Consents;
import com.challenge.api.repository.ConsentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConsentsService {

    @Autowired
    private ConsentsRepository consentsRepository;

    private ConsentsStatus consentsStatus;

    private ConsentsMapper consentsMapper;

    public Consents postConsents(PostConsentRequestDTO postConsentRequestDTO){

        LocalDateTime creationDateTime = LocalDateTime.now();

        Consents consents = Consents
                .builder()
                .status(ConsentsStatus.ACTIVE)
                .cpf(postConsentRequestDTO.cpf())
                .id(UUID.randomUUID())
                .creationDateTime(creationDateTime)
                .expirationDateTime(creationDateTime.plusMonths(Long.parseLong(postConsentRequestDTO.consentDurationTime())))
                .build();

        Consents consentsCreated = consentsRepository.save(consents);
        System.out.println(consentsCreated);

        return consents;
        //return consentsMapper.mapConsentsToPostConsentsResponseDto(consentsCreated);
    }
}
