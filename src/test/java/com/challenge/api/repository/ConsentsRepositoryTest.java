package com.challenge.api.repository;

import com.challenge.api.constants.ConsentsStatus;
import com.challenge.api.model.Consents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

@Testcontainers
@DataMongoTest
class ConsentsRepositoryTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");

    @Autowired
    ConsentsRepository consentsRepository;

    @Test
    void connectionEstablished(){
        assertThat(mongoDBContainer.isCreated()).isTrue();
        assertThat(mongoDBContainer.isRunning()).isTrue();
    }

    @Test
    void inserData(){
        UUID consentId = UUID.randomUUID();
        Consents consents = new Consents(consentId, ConsentsStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now().plusMonths(2), "111.111.111-00");
        consentsRepository.save(consents);

        assertThat(consentsRepository.existsById(consentId)).isTrue();
    }
}