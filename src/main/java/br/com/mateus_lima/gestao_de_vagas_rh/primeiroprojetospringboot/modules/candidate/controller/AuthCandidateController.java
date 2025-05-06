package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.controller;

import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.dto.AuthCandidateResquestDTO;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.useCase.AuthCandidateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/candidate")
    public ResponseEntity<Object> create( @RequestBody AuthCandidateResquestDTO request){
        try {

            var token = authCandidateUseCase.execute(request);

            return  ResponseEntity.ok().body(token);

        }catch (Exception ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
    }
}
