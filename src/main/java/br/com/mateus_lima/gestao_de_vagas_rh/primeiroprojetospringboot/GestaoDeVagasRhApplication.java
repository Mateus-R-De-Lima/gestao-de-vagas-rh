package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.mateus_lima.gestao_de_vagas_rh")
@OpenAPIDefinition(
		info = @Info(
				title = "Gestão de Vagas",
				description = "API responsável pela gestão de vagas"
		)
)
public class GestaoDeVagasRhApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoDeVagasRhApplication.class, args);
	}

}
