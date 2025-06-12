package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.useCase;

import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.CandidateRepository;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.dto.AuthCandidateResquestDTO;
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
public class AuthCandidateUseCase {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${security.algorithm-key-candidate}")
    private String securityKey;

    public AuthCandidateResponseDTO execute(AuthCandidateResquestDTO authCandidateResquestDTO) throws AuthenticationException {

        var candidate = this.candidateRepository.findByUsername(authCandidateResquestDTO.username()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        });

        var passwordMatches = passwordEncoder.matches(authCandidateResquestDTO.password(), candidate.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(securityKey);
        var expiresIn = Instant.now().plus(Duration.ofMinutes(10));
        var token = JWT.create().withIssuer("Java").withSubject(candidate.getId().toString()).withClaim("roles", Arrays.asList("candidate")).withExpiresAt(expiresIn).sign(algorithm);


        var authCandidateResponse = AuthCandidateResponseDTO
                .builder()
                .acess_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authCandidateResponse;


    }
}
