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
@OpenAPIDefinition(
        info = @Info(
                title = "Gestão de Vagas",
                description = "API responsável pela gestão de vagas",
                version = "1"
        )
)
@SecurityScheme(name = "jwt_auth", scheme = "bearer", bearerFormat = "JWT", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class GestaoDeVagasRhApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestaoDeVagasRhApplication.class, args);
    }

}
