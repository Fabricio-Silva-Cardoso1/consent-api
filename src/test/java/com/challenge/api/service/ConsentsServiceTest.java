package com.challenge.api.service;

import com.challenge.api.constants.ConsentsStatus;
import com.challenge.api.dto.PostConsentRequestDTO;
import com.challenge.api.exception.PostConsentsDtoException;
import com.challenge.api.exception.PostConsentsException;
import com.challenge.api.model.Consents;
import com.challenge.api.repository.ConsentsRepository;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Testcontainers
@DataMongoTest
class ConsentsServiceTest {

    @Mock
    private ConsentsService consentsService;

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");

    @Mock
    ConsentsRepository consentsRepository;

    @Test
    void connectionEstablished(){
        assertThat(mongoDBContainer.isCreated()).isTrue();
        assertThat(mongoDBContainer.isRunning()).isTrue();
    }

    @Test
    void postConsentsSuccess() {

        PostConsentRequestDTO postConsentRequestDTOTest = new PostConsentRequestDTO("111.111.111-11", 2);
        UUID consentId = UUID.randomUUID();
        Consents consents = new Consents(consentId, ConsentsStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now().plusMonths(2), "111.111.111-00");
        when(consentsRepository.save(consents)).thenReturn(consents);

    }

    @Test
    @DisplayName("Passar um CPF invalido! Deve retornar erro.")
    void postConsentsWrongCPF() throws PostConsentsException{

        PostConsentsException thrown = Assertions.assertThrows(PostConsentsException.class, () ->{
            PostConsentRequestDTO postConsentRequestDTOTest = new PostConsentRequestDTO("333.333-33", 2);
            consentsService.postConsents(postConsentRequestDTOTest);
        });

        Assertions.assertEquals("CPF inválido. O campo deve ser preenchido e no formato ###.###.###-##", thrown.getMessage());
    }

    @Test
    @DisplayName("Passar uma data de validade invalida! Deve retornar erro.")
    void postConsentsWrongDuration () throws PostConsentsDtoException{

        PostConsentsDtoException exception = Assertions.assertThrows(PostConsentsDtoException.class, () ->{
            PostConsentRequestDTO postConsentRequestDTOTest = new PostConsentRequestDTO("111.111.111-11", 0);
            consentsService.postConsents(postConsentRequestDTOTest);
        });

        Assertions.assertEquals("Duração do consentimento precisa ser preenchido com um valor maior que 0!", exception.getMessage());

    }
}