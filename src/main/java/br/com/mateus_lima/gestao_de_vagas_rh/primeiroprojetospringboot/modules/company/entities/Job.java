package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "jobs")
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;
    private  String benifits;
    private String description;
    private  String level;

    @ManyToOne() // n : 1, outra palavras, vou ter varios trabalhos para uma empresa
    @JoinColumn(name="company_id", insertable = false, updatable = false)
    private CompanyEntity companyEntity;

    @Column(name = "company_id")
    private UUID companyId;

    @CreationTimestamp
    private LocalDateTime createAt;

}
