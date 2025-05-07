package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateResponseDTO {
    private UUID id;
    private String name;
    private String username;
    private String email;
    private String description;

}
