package com.challenge.api.mapper;

import com.challenge.api.dto.GetAllConsentsResponseDTO;
import com.challenge.api.dto.GetConsentByIdResponseDTO;
import com.challenge.api.dto.PostConsentRequestDTO;
import com.challenge.api.dto.PostConsentsResponseDTO;
import com.challenge.api.model.Consents;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ConsentsMapper {

    Consents mapCreateConsentDtoToConsent(PostConsentRequestDTO postConsentRequestDto);

    @Mapping(source = "id", target = "id")
    PostConsentsResponseDTO mapConsentsToPostConsentsResponseDto(Consents consents);

    List<GetAllConsentsResponseDTO> mapConsentsToGetAllConsentsResponseDTO(List<Consents> consents);


    GetConsentByIdResponseDTO mapConsentsToGetConsentByIdResponseDTO(Consents consents);
}
