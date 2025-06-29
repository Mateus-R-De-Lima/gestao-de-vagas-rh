package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.controllers;

import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.dto.JobDTO;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.entities.JobEntity;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.useCases.CreateJobUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
@Tag(name = "Vaga",description = "Descrição da Vaga")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;
    @PostMapping("/")
    @PreAuthorize("hasRole('company')")
    @Operation(summary = "Cadastro de vagas", description = "Essa função é responsável por cadastrar as vagas dentro da empresa")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200",content = {
                            @Content(array = @ArraySchema(
                                    schema = @Schema(implementation = JobEntity.class)
                            ))
                    })
            }
    )
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> create(@Valid @RequestBody JobDTO jobDTO, HttpServletRequest request) {
        try {
            // Recuperar o companyId
            var companyId = request.getAttribute("company_id").toString();

            var jobEntity = JobEntity.builder()
                    .benifits(jobDTO.getBenifits())
                    .level(jobDTO.getLevel())
                    .description(jobDTO.getDescription())
                    .companyId(UUID.fromString(companyId))
                    .build();



            var result = this.createJobUseCase.execute(jobEntity);
            return ResponseEntity.ok().body(result);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

}
