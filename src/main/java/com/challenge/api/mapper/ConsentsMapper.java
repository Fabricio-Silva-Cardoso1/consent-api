package com.challenge.api.mapper;

import com.challenge.api.dto.PostConsentRequestDTO;
import com.challenge.api.dto.PostConsentsResponseDTO;
import com.challenge.api.model.Consents;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ConsentsMapper {

    Consents mapCreateConsentDtoToConsent(PostConsentRequestDTO postConsentRequestDto);

    @Mapping(target = "id", source = "id")
    PostConsentsResponseDTO mapConsentsToPostConsentsResponseDto(Consents consents);
}
