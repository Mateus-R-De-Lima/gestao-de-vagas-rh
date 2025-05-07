package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    // Injeta o filtro personalizado que você criou para validar o token JWT
    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private SecurityCandidateFilter securityCandidateFilter;

    /**
     * Configura a cadeia de filtros de segurança do Spring Security.
     * Define o que precisa ou não de autenticação e adiciona o filtro JWT.
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desabilita proteção contra CSRF (útil em APIs REST, pois não usam cookies)
                .csrf(csrf -> csrf.disable())

                // Define as regras de autorização para cada endpoint
                .authorizeHttpRequests(auth -> {
                    // Libera o acesso público (sem autenticação) para as rotas de cadastro e autenticação
                    auth.requestMatchers("/candidate/**", "/company/**", "/auth/company", "/auth/candidate").permitAll();

                    // Qualquer outra requisição deve ser autenticada
                    auth.anyRequest().authenticated();
                })

                // Adiciona o filtro JWT antes do filtro padrão de autenticação (BasicAuthenticationFilter)
                .addFilterBefore(securityCandidateFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(securityFilter, BasicAuthenticationFilter.class);



        // Retorna a configuração finalizada
        return http.build();
    }

    /**
     * Define o algoritmo de codificação de senhas que será usado pelo Spring Security.
     * Neste caso, o BCrypt, que é uma opção segura e amplamente utilizada.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}