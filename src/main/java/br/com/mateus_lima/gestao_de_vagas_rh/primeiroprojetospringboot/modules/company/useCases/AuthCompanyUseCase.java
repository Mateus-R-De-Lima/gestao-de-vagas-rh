package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.useCases;

import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.dto.AuthCompanyDTO;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.dto.AuthCompanyResponseDTO;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyUseCase {

    @Value("${security.algorithm-key}")
    private String securityKey;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CompanyRepository companyRepository;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        });
        //Validar senha

        var passwordMaches = passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        // Caso a senha não for igual lançar uma exception
        if (!passwordMaches) {
            throw new AuthenticationException();
        }

        //Retornar o JWT
        Algorithm algorithm = Algorithm.HMAC256(securityKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create()
                .withIssuer("java-vagas")
                .withClaim("roles", Arrays.asList("company"))
                .withExpiresAt(expiresIn)
                .withSubject(company.getId().toString())
                .sign(algorithm);

        return AuthCompanyResponseDTO
                .builder()
                .expires_in(expiresIn.toEpochMilli())
                .acess_token(token)
                .build();
    }

}
