package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.controller;

import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.exceptions.UserAlreadyExistsExeption;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.CandidateEntity;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.CandidateRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("cancidate")
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;
    @PostMapping("/")
    public CandidateEntity create(@Valid @RequestBody CandidateEntity request){
        candidateRepository
                .findByUsernameOrEmail(request.getUsername(),request.getEmail())
                .ifPresent((user) ->{
                throw new UserAlreadyExistsExeption();
        });

       return candidateRepository.save(request);
    }
}
