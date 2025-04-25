package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
}
