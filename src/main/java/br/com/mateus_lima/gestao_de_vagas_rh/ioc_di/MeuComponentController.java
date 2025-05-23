package br.com.mateus_lima.gestao_de_vagas_rh.ioc_di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/component")
public class MeuComponentController {

    @Autowired
    MeuComponent meuComponent;

    @GetMapping("/")
    public String chamandoComponent(){
        var resultado = meuComponent.chamarMeuComponent();
        return resultado;
    }
}
