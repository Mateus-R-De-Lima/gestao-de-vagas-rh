package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.useCase;

import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.exceptions.JobNotFoundException;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.exceptions.UserNotFoundException;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.CandidateRepository;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    // ID do candidato
    // ID da vaga

    public  void execute(UUID idCandidate, UUID idJob){
        this.candidateRepository.findById(idCandidate)
                .orElseThrow( () ->{
                   throw  new UserNotFoundException("Candidato não encontrado.");
                });

        this.jobRepository.findById(idJob)
                .orElseThrow( () ->{
                   throw new JobNotFoundException("Trabalho não encontrado.");
                });


    }
}
