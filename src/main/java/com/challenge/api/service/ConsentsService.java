package com.challenge.api.service;

import com.challenge.api.constants.ConsentsStatus;
import com.challenge.api.dto.GetAllConsentsReponseDTO;
import com.challenge.api.dto.PostConsentRequestDTO;
import com.challenge.api.dto.PostConsentsResponseDTO;
import com.challenge.api.exception.PostConsentsDtoException;
import com.challenge.api.exception.PostConsentsException;
import com.challenge.api.mapper.ConsentsMapper;
import com.challenge.api.model.Consents;
import com.challenge.api.repository.ConsentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public PostConsentsResponseDTO postConsents(PostConsentRequestDTO postConsentRequestDTO){

        if(postConsentRequestDTO.isConsentDurationTimeEmpty()) throw new PostConsentsDtoException("Duração do consentimento precisa ser preenchido com um valor maior que 0!");
        if(!postConsentRequestDTO.isCpfValid(postConsentRequestDTO.cpf())) throw new PostConsentsException("CPF inválido. O campo deve ser preenchido e no formato ###.###.###-##");

        LocalDateTime creationDateTime = LocalDateTime.now().withNano(0);

        Consents consents = Consents
                .builder()
                .status(ConsentsStatus.ACTIVE)
                .cpf(postConsentRequestDTO.cpf())
                .id(UUID.randomUUID())
                .creationDateTime(creationDateTime)
                .expirationDateTime(creationDateTime.plusMonths(Long.valueOf(postConsentRequestDTO.consentDurationTime())))
                .build();

        Consents consentsCreated = consentsRepository.save(consents);

        return consentsMapper.mapConsentsToPostConsentsResponseDto(consentsCreated);
    }

    public List<GetAllConsentsReponseDTO> getAllConsentsResponseDTO(){
        List<Consents> getAllConsents = consentsRepository.findAll();

        return consentsMapper.mapConsentsToGetAllConsentsResponseDTO(getAllConsents);
    }
}
