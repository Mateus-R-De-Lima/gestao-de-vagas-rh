package br.com.mateus_lima.gestao_de_vagas_rh.modules.integrationTests.controller;


import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.GestaoDeVagasRhApplication;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.exceptions.CompanyNotFoundException;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.exceptions.UserNotFoundException;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.dto.JobDTO;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.entities.CompanyEntity;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.modules.company.repositories.CompanyRepository;
import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.security.SecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static br.com.mateus_lima.gestao_de_vagas_rh.modules.utils.TestsUtils.objectToString;
import static br.com.mateus_lima.gestao_de_vagas_rh.modules.utils.TestsUtils.toToken;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = GestaoDeVagasRhApplication.class)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    CompanyRepository companyRepository;

    @Value("${security.algorithm-key}")
    private String securityKey;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void should_be_able_to_create_a_new_job() throws Exception {

        var company = CompanyEntity.builder()
                .email("company@testes.com.br")
                .name("Company de Testes")
                .description("Company para realizar testes de criação de job")
                .password("123456@teste")
                .build();

        companyRepository.saveAndFlush(company);




    }

    @Test
    public  void should_be_able_not_create_a_new_job_if_company_not_found() throws Exception {

        var createJobDTO = JobDTO.builder()
                .description("Descrição de Testes")
                .level("Testes")
                .benifits("Benificios de testes")
                .build();


        mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(createJobDTO))
                .header("Authorization", toToken(UUID.randomUUID(),securityKey))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


}
