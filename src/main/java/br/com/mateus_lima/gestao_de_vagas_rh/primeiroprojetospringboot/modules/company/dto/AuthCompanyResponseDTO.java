package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthCompanyResponseDTO {
    private String acess_token;
    private Long expires_in;
}
