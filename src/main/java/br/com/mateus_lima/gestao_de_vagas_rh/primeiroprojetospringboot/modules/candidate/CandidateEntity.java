package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.candidate;

import lombok.Data;

import java.util.UUID;

@Data
public class CandidateEntity {

    private  UUID id;
    private  String name;
    private  String username;
    private  String email;
    private  String password;
    private  String description;
    private  String curriculum;
}
