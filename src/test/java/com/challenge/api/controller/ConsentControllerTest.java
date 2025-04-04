package com.challenge.api.controller;

import com.challenge.api.dto.PostConsentRequestDTO;
import com.challenge.api.dto.PostConsentsResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConsentControllerTest {



    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");

    @Autowired
    TestRestTemplate testRestTemplate;


    @Test
    void createConsents() {
        PostConsentRequestDTO postConsentRequestDTOTest = new PostConsentRequestDTO("111.111.111-11", 2);
        ResponseEntity<PostConsentsResponseDTO> consentsResponseDTO;
        consentsResponseDTO = testRestTemplate.postForEntity("/consents-api/v1/consents", postConsentRequestDTOTest, PostConsentsResponseDTO.class);
        assertThat(consentsResponseDTO.getStatusCode().is2xxSuccessful()).isTrue();
    }

}