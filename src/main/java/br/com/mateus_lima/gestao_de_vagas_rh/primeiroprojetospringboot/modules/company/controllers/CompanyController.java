package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.controllers;

import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.entities.CompanyEntity;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.useCases.CreateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase companyUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid  @RequestBody CompanyEntity request){

        try {
            var result = this.companyUseCase.execute(request);
            return  ResponseEntity.ok().body(result);

        }catch (Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
