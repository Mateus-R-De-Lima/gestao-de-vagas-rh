package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice // Define que esta classe será responsável por tratar exceções lançadas pelos controllers de forma global
public class ExeptionHandlerController {

    private MessageSource messageSource; // Objeto que fornece mensagens traduzidas, com suporte a internacionalização

    // Construtor com injeção de dependência do MessageSource
    public ExeptionHandlerController(MessageSource message){
        this.messageSource = message;
    }

    // Método que trata exceções do tipo MethodArgumentNotValidException, que ocorrem quando a validação de parâmetros falha
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentoNotValidExeption(MethodArgumentNotValidException e) {

        List<ErrorMessageDTO> errorMessageDTO = new ArrayList<>(); // Lista para armazenar mensagens de erro personalizadas

        // Percorre todos os erros de validação dos campos
        e.getBindingResult().getFieldErrors().forEach(err -> {
            // Busca a mensagem de erro traduzida com base na localidade atual
            String mensagem = messageSource.getMessage(err, LocaleContextHolder.getLocale());

            // Cria um objeto DTO contendo a mensagem e o nome do campo com erro
            ErrorMessageDTO error = new ErrorMessageDTO(mensagem, err.getField());

            // Adiciona o erro à lista
            errorMessageDTO.add(error);
        });

        // Retorna uma resposta HTTP 400 (Bad Request) com a lista de erros
        return new ResponseEntity<>(errorMessageDTO, HttpStatus.BAD_REQUEST);
    }
}
