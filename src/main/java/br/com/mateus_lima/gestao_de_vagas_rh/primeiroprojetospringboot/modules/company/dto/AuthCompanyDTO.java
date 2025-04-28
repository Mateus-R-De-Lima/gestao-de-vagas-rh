package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDTO {
    private String username;
    private String password;

}
