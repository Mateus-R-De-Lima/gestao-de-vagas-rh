package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.exceptions;

public class UserAlreadyExistsExeption extends  RuntimeException{
    public  UserAlreadyExistsExeption(){
        super("Usuário já existe");
    }
}
