package com.challenge.api.mapper;

import com.challenge.api.dto.PostConsentRequestDTO;
import com.challenge.api.dto.PostConsentsResponseDTO;
import com.challenge.api.model.Consents;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsentsMapper {

    Consents mapCreateConsentDtoToConsent(PostConsentRequestDTO postConsentRequestDto);

    PostConsentsResponseDTO mapConsentsToPostConsentsResponseDto(Consents consents);
}
