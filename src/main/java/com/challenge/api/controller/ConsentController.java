package com.challenge.api.controller;

import com.challenge.api.dto.*;
import com.challenge.api.service.ConsentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/consents-api/v1")
public class ConsentController {

    @Autowired
    private ConsentsService consentsService;

    @PostMapping("/consents")
    public ResponseEntity<PostConsentsResponseDTO> createConsents (@RequestBody PostConsentRequestDTO postConsentRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(consentsService.postConsents(postConsentRequestDTO));
    }

    @GetMapping("/consents")
    public ResponseEntity<List<GetAllConsentsResponseDTO>> getAllConsents (){
        return new ResponseEntity<>(consentsService.getAllConsents(), HttpStatus.OK);
    }

    @GetMapping("/consents/{consentId}")
    public ResponseEntity<GetConsentByIdResponseDTO> getConsentById(@PathVariable UUID consentId){
        return new ResponseEntity<>(consentsService.getConsentById(consentId), HttpStatus.OK);
    }

    @PutMapping("/consents/{consentId}")
    public ResponseEntity<PostConsentsResponseDTO> putConsentById(@PathVariable UUID consentId, @RequestBody PutConsentByIdRequestDTO putConsentByIdRequestDTO){
        return new ResponseEntity<>(consentsService.putConsent(consentId,putConsentByIdRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/consents/{consentId}")
    public ResponseEntity<Void> deleteConsentById(@PathVariable UUID consentId){
        consentsService.deleteConsent(consentId);
        return ResponseEntity.noContent().build();
    }

}
