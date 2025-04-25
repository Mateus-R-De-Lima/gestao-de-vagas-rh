package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.controller;

import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.exceptions.ErrorMessageDTO;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.CandidateEntity;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.useCase.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("cancidate")
public class CandidateController {
    @Autowired
    CreateCandidateUseCase createCandidateUseCase;


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
}
