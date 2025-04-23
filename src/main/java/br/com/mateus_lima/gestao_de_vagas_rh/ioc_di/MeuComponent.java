package br.com.mateus_lima.gestao_de_vagas_rh.ioc_di;

import org.springframework.stereotype.Component;

@Component
public class MeuComponent {

    public String chamarMeuComponent(){
        return "Chamando meu component";
    }
}
