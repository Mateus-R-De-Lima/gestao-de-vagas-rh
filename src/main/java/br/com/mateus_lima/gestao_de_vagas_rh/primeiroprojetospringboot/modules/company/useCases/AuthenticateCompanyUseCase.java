package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.useCases;

import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.dto.AuthCompanyDTO;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AuthenticateCompanyUseCase  {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CompanyRepository companyRepository;

    public void execute(AuthCompanyDTO authCompanyDTO) throws  AuthenticationException {
        var company = this.companyRepository
                .findByUsername(authCompanyDTO.getUsername())
                .orElseThrow( () ->{
                    throw  new  UsernameNotFoundException("Usuário não encontrado.");
                });
        //Validar senha

       var passwordMaches =  passwordEncoder.matches(authCompanyDTO.getPassword(),company.getPassword());

        // Caso a senha não for igual lançar uma exception
        if(!passwordMaches){
            throw new AuthenticationException();
        }

        //Retornar
    }

}
