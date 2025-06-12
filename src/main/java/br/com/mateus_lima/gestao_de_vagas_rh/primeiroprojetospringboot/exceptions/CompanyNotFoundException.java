package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.exceptions;

public class CompanyNotFoundException extends RuntimeException{
    public  CompanyNotFoundException(String msg){
        super(msg);
    }
}
