package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidates")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  UUID id;
    @Schema(example = "Mateus Lima")
    private  String name;
    @Schema(example = "mateus")
    @Pattern(regexp = "\\S+", message = "O campo username não deve conter espaços")
    private  String username;
    @Schema(example = "testes@gmail.com")
    @Email(message = "Não foi possível validar o e-mail. Verifique se está completo e corretamente digitado.")
    private  String email;
    @Schema(example = "admin@123",minLength = 6, maxLength = 150,requiredMode = Schema.RequiredMode.REQUIRED,description = "Senha do Candidato")
    @Length(min = 6, max = 150, message = "Por favor, insira uma senha válida (entre 6 e 150 caracteres)")
    private  String password;
    private  String description;
    private  String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
