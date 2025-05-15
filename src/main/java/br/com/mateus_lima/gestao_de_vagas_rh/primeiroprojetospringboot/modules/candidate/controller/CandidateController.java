package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.controller;

import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.exceptions.ErrorMessageDTO;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.CandidateEntity;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.useCase.CreateCandidateUseCase;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.useCase.ListAllJobsByFilterUseCase;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.useCase.ProfileCandidateUseCase;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.entities.JobEntity;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity request){
        try {
            var result = this.createCandidateUseCase.execute(request);
            return  ResponseEntity.ok().body(result);
        }catch (Exception e){
            List<ErrorMessageDTO> errorMessageDTO = new ArrayList<>(); // Lista para armazenar mensagens de erro personalizadas

            String field = "Status : " + 400;
            ErrorMessageDTO error = new ErrorMessageDTO(e.getMessage(), field);
            errorMessageDTO.add(error);

            return ResponseEntity.badRequest().body(errorMessageDTO);
        }
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('candidate')")
    @Tag(name = "Candidato",description = "Descrição do Candidato")
    @Operation(summary = "Dados do candidato", description = "Informações referente ao candidato")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200",content = {
                            @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
                    }),
                    @ApiResponse(responseCode = "400",description = "Candidato não encontrado.")
            }
    )

    @SecurityRequirement(name = "jwt_auth")
    public  ResponseEntity<Object> get(HttpServletRequest request){
        var idCandidate =  request.getAttribute("candidate_id").toString();
        try {
            var profile = this.profileCandidateUseCase.execute(UUID.fromString(idCandidate));
            return ResponseEntity.ok().body(profile);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('candidate')")
    @Tag(name = "Candidato",description = "Descrição da Vaga")
    @Operation(summary = "Listagem de vagas disponíveis para o candidato.", description = "Listagem de vagas")
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
    public List<JobEntity> findJobByFilter(@RequestParam String filter){
        return this.listAllJobsByFilterUseCase.execute(filter);
    }
}
