package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.useCases;

import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.exceptions.UserAlreadyExistsExeption;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.entities.CompanyEntity;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;


    public CompanyEntity execute(CompanyEntity request){

        this.companyRepository
                .findByUsernameOrEmail(request.getUsername(),request.getEmail())
                .ifPresent((user) ->{
                    throw new UserAlreadyExistsExeption();
                });

        return  this.companyRepository.save(request);

    }

}
