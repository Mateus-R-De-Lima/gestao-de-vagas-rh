package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.entities;

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

@Entity(name = "Companies")
@Data
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private  String name;
    @Pattern(regexp = "\\S+", message = "O campo username não deve conter espaços")
    private  String username;
    @Email(message = "Não foi possível validar o e-mail. Verifique se está completo e corretamente digitado.")
    private  String email;
    @Length(min = 6, max = 50, message = "Por favor, insira uma senha válida (entre 6 e 50 caracteres)")
    private  String password;
    private String website;
    private  String description;
    @CreationTimestamp
    private LocalDateTime createAt;

}
