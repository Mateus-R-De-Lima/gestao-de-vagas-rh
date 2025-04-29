package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.controllers;

import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.dto.AuthCompanyDTO;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.useCases.AuthCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/company")
    public ResponseEntity<Object> create(@RequestBody AuthCompanyDTO request){
        try {
            var token = authCompanyUseCase.execute(request);
            return ResponseEntity.ok().body(token);

        }catch (Exception ex){
                return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }


    }
}
