package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.useCase;

import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.exceptions.UserAlreadyExistsExeption;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.exceptions.UserNotFoundException;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.CandidateRepository;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.dto.ProfileCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO  execute(UUID idCandidate){

        var candidate = this.candidateRepository.findById(idCandidate).orElseThrow(() ->{
           throw new UserNotFoundException("Candidato não encontrado.");
        });

        return ProfileCandidateResponseDTO.builder()
                .email(candidate.getEmail())
                .name(candidate.getName())
                .description(candidate.getDescription())
                .id(candidate.getId())
                .username(candidate.getUsername())
                .build();
    }
}
