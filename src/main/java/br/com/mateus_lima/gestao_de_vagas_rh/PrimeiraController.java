package br.com.mateus_lima.gestao_de_vagas_rh;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //utilizamos a annotation @RestController para indicar que a classe é um Controller e que estamos construindo uma API REST.
@RequestMapping("/primeiraController") //utilizamos a annotation @RequestMapping para definir o recurso da rota
public class PrimeiraController {

    @GetMapping("/primeiroMetodo")
    public String primeiroMetodo(){
        return "Meu primeiro endpoit";
    }
}
