package com.challenge.api.model;

import com.challenge.api.constants.ConsentsStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.UUID;

@Document
@Getter
@Setter
@Builder

public class Consents {

    @Id
    private final UUID id;

    @NotBlank
    @NotNull
    private ConsentsStatus status;

    @NotBlank (message = "Precisa de uma data de criação")
    @NotNull
    private final LocalDateTime creationDateTime;

    @NotBlank (message = "Precisa de um tem de expiração")
    @NotNull
    private final LocalDateTime expirationDateTime;

    @NotBlank(message = "CPF necessário para criar o consentimento")
    @NotNull
    @Pattern(regexp = "([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})")
    private final String cpf;

    public Consents(UUID id, ConsentsStatus status, LocalDateTime expirationDateTime, LocalDateTime creationDateTime, String cpf) {
        this.id = id;
        this.cpf = cpf;
        this.expirationDateTime = expirationDateTime;
        this.creationDateTime = creationDateTime;
        this.status = status;
    }

    @Override
    public String toString(){
        return "Consent{" +
                "id=" + this.id +
                ", creation Date Time='" + this.creationDateTime + '\'' +
                ", expiration Date Time='" + this.expirationDateTime + '\'' +
                ", cpf do usuário= '" + this.cpf + '\'' +
                ", status do consentimento'" + this.status +
                '}';
    }


}
