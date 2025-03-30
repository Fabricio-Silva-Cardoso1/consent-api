package com.challenge.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@NotBlank(message = "Verificar os campos da requisição")
@NotNull(message = "Verificar os campos da requisição")
@NotEmpty(message = "Verificar os campos da requisição")
public record PostConsentRequestDTO(String cpf, int consentDurationTime) {

    public boolean isConsentDurationTimeEmpty(){
        return this.consentDurationTime < 1;
    }
}