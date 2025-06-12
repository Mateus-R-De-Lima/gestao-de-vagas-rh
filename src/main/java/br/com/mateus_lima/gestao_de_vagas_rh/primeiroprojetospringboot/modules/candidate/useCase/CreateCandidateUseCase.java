package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.useCase;

import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.exceptions.UserAlreadyExistsExeption;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.CandidateEntity;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity request){
        candidateRepository
                .findByUsernameOrEmail(request.getUsername(),request.getEmail())
                .ifPresent((user) ->{
                    throw new UserAlreadyExistsExeption("Candidato já foi cadastrado");
                });
        // Codificar a Senha
        var password = passwordEncoder.encode(request.getPassword());
        request.setPassword(password);

        return candidateRepository.save(request);
    }

}
