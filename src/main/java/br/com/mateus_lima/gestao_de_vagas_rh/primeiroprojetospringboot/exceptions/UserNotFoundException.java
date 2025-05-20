package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.exceptions;

public class UserNotFoundException extends  RuntimeException{
    public  UserNotFoundException(String msg){
        super(msg);
    }
}
