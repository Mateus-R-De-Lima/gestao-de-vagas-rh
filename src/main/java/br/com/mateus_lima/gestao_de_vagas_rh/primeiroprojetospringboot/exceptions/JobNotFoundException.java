package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.exceptions;

public class JobNotFoundException extends RuntimeException{
    public  JobNotFoundException(String msg){
        super(msg);
    }
}
