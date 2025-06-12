package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.mateus_lima.gestao_de_vagas_rh")
public class GestaoDeVagasRhApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestaoDeVagasRhApplication.class, args);
    }

}
