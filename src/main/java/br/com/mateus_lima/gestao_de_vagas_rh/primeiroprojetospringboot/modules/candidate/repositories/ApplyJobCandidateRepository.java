package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.repositories;

import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate.entities.ApplyJobCandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobCandidateRepository extends JpaRepository<ApplyJobCandidateEntity, UUID> {
}
