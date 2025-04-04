package com.challenge.api.model;

import com.challenge.api.constants.ConsentsStatus;
import lombok.*;
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
@AllArgsConstructor
@NoArgsConstructor

public class Consents {

    @Id
    private UUID id;

    @NotBlank(message = "Consentimento precisa de um status inicial!")
    @NotNull(message = "Consentimento precisa de um status inicial!")
    private ConsentsStatus status;

    @NotBlank (message = "Precisa de uma data de criação")
    @NotNull
    private LocalDateTime creationDateTime;

    @NotBlank(message = "Precisa de um tem de expiração")
    @NotNull
    private LocalDateTime expirationDateTime;

    @NotBlank(message = "CPF necessário para criar o consentimento")
    @NotNull(message = "CPF necessário para criar o consentimento")
    @Pattern(regexp = "([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})")
    private String cpf;

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
