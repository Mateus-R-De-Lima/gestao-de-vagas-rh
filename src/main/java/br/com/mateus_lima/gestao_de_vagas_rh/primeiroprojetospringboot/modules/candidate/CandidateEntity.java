package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
public class CandidateEntity {

    private  UUID id;
    private  String name;
    @Pattern(regexp = "^(?!\\s*$).+", message = "O campo username não deve conter espaços")
    private  String username;
    @Email(message = "Não foi possível validar o e-mail. Verifique se está completo e corretamente digitado.")
    private  String email;
    @Length(min = 6, max = 50, message = "Por favor, insira uma senha válida (entre 6 e 50 caracteres)")
    private  String password;
    private  String description;
    private  String curriculum;
}
