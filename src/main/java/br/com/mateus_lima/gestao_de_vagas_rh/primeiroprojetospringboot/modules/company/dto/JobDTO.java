package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
    @Schema(example = "Plano de saúde, vale-refeição, bônus anual")
    private  String benifits;
    @Schema(example = "JAVA Spring Boot")
    private String description;
    @Schema(example = "JUNIOR")
    private  String level;
}
