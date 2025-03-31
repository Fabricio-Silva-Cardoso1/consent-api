package com.challenge.api.controller;

import com.challenge.api.dto.GetAllConsentsReponseDTO;
import com.challenge.api.dto.PostConsentRequestDTO;
import com.challenge.api.dto.PostConsentsResponseDTO;
import com.challenge.api.model.Consents;
import com.challenge.api.service.ConsentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/consents-api/v1")
public class ConsentController {

    @Autowired
    private ConsentsService consentsService;

    @PostMapping("/consents")
    public ResponseEntity<PostConsentsResponseDTO> createConsents (@RequestBody PostConsentRequestDTO postConsentRequestDTO){
        return new ResponseEntity<>(consentsService.postConsents(postConsentRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/consents")
    public ResponseEntity<List<GetAllConsentsReponseDTO>> getAllConsents (){
        return new ResponseEntity<>(consentsService.getAllConsentsResponseDTO(), HttpStatus.OK);
    }

}
