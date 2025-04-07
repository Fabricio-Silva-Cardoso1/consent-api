package com.challenge.api.service;

import com.challenge.api.constants.ConsentsStatus;
import com.challenge.api.dto.*;
import com.challenge.api.exception.PostConsentsDtoException;
import com.challenge.api.exception.PostConsentsException;
import com.challenge.api.exception.PutConsentByIdException;
import com.challenge.api.mapper.ConsentsMapper;
import com.challenge.api.model.Consents;
import com.challenge.api.repository.ConsentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ConsentsService {

    @Autowired
    private ConsentsRepository consentsRepository;

    private ConsentsStatus consentsStatus;

    @Autowired
    private ConsentsMapper consentsMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    public PostConsentsResponseDTO postConsents(PostConsentRequestDTO postConsentRequestDTO){

        if(postConsentRequestDTO.isConsentDurationTimeEmpty()) throw new PostConsentsDtoException("Duração do consentimento precisa ser preenchido com um valor maior que 0!");
        if(!postConsentRequestDTO.isCpfValid(postConsentRequestDTO.cpf())) throw new PostConsentsException("CPF inválido. O campo deve ser preenchido e no formato ###.###.###-##");

        Consents consents = Consents
                .builder()
                .cpf(postConsentRequestDTO.cpf())
                .id(UUID.randomUUID())
                .expirationDateTime(LocalDateTime.now().plusMonths(Long.valueOf(postConsentRequestDTO.consentDurationTime())))
                .build();

        Consents consentsCreated = this.consentsRepository.save(consents);

        return consentsMapper.mapConsentsToPostConsentsResponseDto(consentsCreated);
    }

    public List<GetAllConsentsResponseDTO> getAllConsents(){

        List<Consents> getAllConsents = this.consentsRepository.findAll();

        return consentsMapper.mapConsentsToGetAllConsentsResponseDTO(getAllConsents);
    }

    public GetConsentByIdResponseDTO getConsentById(UUID consentId){

        Consents getConsetById = this.consentsRepository.findById(consentId).orElseThrow(() -> new NullPointerException("Usuario não encontrado!!"));

        return consentsMapper.mapConsentsToGetConsentByIdResponseDTO(getConsetById);
    }

    public PostConsentsResponseDTO putConsent (UUID consentId, PutConsentByIdRequestDTO putConsentByIdRequestDTO){

        Consents userConsent = this.consentsRepository.findById(consentId).orElseThrow(() -> new NullPointerException("Consentimento não encontrado!!"));

        if(putConsentByIdRequestDTO.expirationDateTime().isBefore(LocalDateTime.now().plusNanos(0))) throw new PutConsentByIdException("Data inválida! Valor deve ser no futuro.");

        userConsent.setStatus(putConsentByIdRequestDTO.status());
        userConsent.setExpirationDateTime(putConsentByIdRequestDTO.expirationDateTime());

        Consents consentsUpdated = this.consentsRepository.save(userConsent);

        return consentsMapper.mapConsentsToPostConsentsResponseDto(consentsUpdated);

    }

    public String deleteConsent(UUID consentId){

        this.consentsRepository.updateConsent(consentId, ConsentsStatus.REVOKED);

        return "Consentimento atualizado";
    }
}
