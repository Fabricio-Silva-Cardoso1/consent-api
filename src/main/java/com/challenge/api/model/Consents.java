package com.challenge.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
public class Consent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private final UUID id;

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

    public Consent(UUID id, LocalDateTime expirationDateTime, LocalDateTime creationDateTime, String cpf) {
        this.id = id;
        this.cpf = cpf;
        this.expirationDateTime = expirationDateTime;
        this.creationDateTime = creationDateTime;
    }

    @Override
    public String toString(){
        return "Consent{" +
                "id=" + this.id +
                ", creation Date Time='" + this.creationDateTime + '\'' +
                ", expiration Date Time='" + this.expirationDateTime + '\'' +
                ", cpf do usuário= '" + this.cpf + '}';
    }


}
