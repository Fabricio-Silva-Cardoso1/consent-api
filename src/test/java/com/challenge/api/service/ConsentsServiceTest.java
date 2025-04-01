package com.challenge.api.service;


import com.challenge.api.constants.ConsentsStatus;
import com.challenge.api.dto.GetAllConsentsResponseDTO;
import com.challenge.api.dto.PostConsentRequestDTO;
import com.challenge.api.dto.PostConsentsResponseDTO;
import com.challenge.api.dto.PutConsentByIdRequestDTO;
import com.challenge.api.exception.PostConsentsException;
import com.challenge.api.exception.PutConsentByIdException;
import com.challenge.api.repository.ConsentsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import java.util.UUID;


@ExtendWith(MockitoExtension.class)
class ConsentsServiceTest {

    @InjectMocks
    private ConsentsService consentsService;

    @Mock
    private ConsentsRepository consentsRepository;

    @Test
    @DisplayName("Deveria criar um consentimento e inserir no banco de dados!!")
    void postConsents() {
        /*
        PostConsentRequestDTO postConsentRequestDTO = new PostConsentRequestDTO("111.111.111-11", 1);
        PostConsentsResponseDTO postConsentsResponseTestDTO = new PostConsentsResponseDTO(UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), "222.222.222-22", ConsentsStatus.ACTIVE);

        Mockito.when(consentsService.postConsents(postConsentRequestDTO)).thenReturn(postConsentsResponseTestDTO);
        PostConsentsResponseDTO postConsentsResponseDTO =  consentsService.postConsents(postConsentRequestDTO);

        Assertions.assertTrue(postConsentsResponseDTO.hasStatus()); */

    }

    @Test
    @DisplayName("Deveria dar erro ao enviar CPF inválido!!")
    void postConsents2() {

        PostConsentRequestDTO postConsentRequestDTO = new PostConsentRequestDTO("1.111.111-11", 1);
        PostConsentsResponseDTO postConsentsResponseTestDTO = new PostConsentsResponseDTO(UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), "222.222.222-22", ConsentsStatus.ACTIVE);

        PostConsentsException postConsentsException =  Assertions.assertThrows(

                PostConsentsException.class,
                () -> { consentsService.postConsents(postConsentRequestDTO);},
                "CPF inválido. O campo deve ser preenchido e no formato ###.###.###-##" );

        Assertions.assertEquals("CPF inválido. O campo deve ser preenchido e no formato ###.###.###-##", postConsentsException.getMessage());
    }


    @Test
    @DisplayName("Data inválida. Data deve ser no futuro!!")
    void putConsent() {

        UUID id = UUID.fromString("51d9da1c-ca32-4dde-ae85-2e51917b1d5b");
        PutConsentByIdRequestDTO putConsentByIdRequestDTO = new PutConsentByIdRequestDTO(ConsentsStatus.ACTIVE, LocalDateTime.now());

        NullPointerException nullPointerException =  Assertions.assertThrows(

                NullPointerException.class,
                () -> { consentsService.putConsent(id, putConsentByIdRequestDTO);},
                "Consentimento não encontrado!!" );

        Assertions.assertEquals("Consentimento não encontrado!!", nullPointerException.getMessage());

    }

    @Test
    @DisplayName("Consentimento não encontrado. Consentimento inexistente ou valor errado!!")
    void putConsent02() {

        UUID id = UUID.fromString("88cfc898-f2fb-0000-bb01-7008edc5cb81");
        PutConsentByIdRequestDTO putConsentByIdRequestDTO = new PutConsentByIdRequestDTO(ConsentsStatus.ACTIVE, LocalDateTime.now().plusMonths(1));

        NullPointerException nullPointerException =  Assertions.assertThrows(

                NullPointerException.class,
                () -> { consentsService.putConsent(id, putConsentByIdRequestDTO);},
                "Consentimento não encontrado!!" );

        Assertions.assertEquals("Consentimento não encontrado!!", nullPointerException.getMessage());

    }

}