package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {

    private String menssage;
    private String field;
}
