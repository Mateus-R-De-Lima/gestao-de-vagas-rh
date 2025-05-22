package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.security;

import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.providers.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de segurança que intercepta cada requisição HTTP uma única vez
 * e realiza a validação do token JWT contido no cabeçalho Authorization.
 */
@Component
public class SecurityCompanyFilter extends OncePerRequestFilter {

    // Injeta a dependência do provedor responsável pela validação dos tokens JWT
    @Autowired
    private JWTProvider jwtProvider;

    /**
     * Método que intercepta a requisição HTTP antes que ela chegue no controller.
     * Aqui ocorre a verificação da autenticação via JWT.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Recupera o valor do cabeçalho "Authorization" da requisição HTTP
        String header = request.getHeader("Authorization");

        // Garante que o contexto de segurança seja limpo antes de qualquer verificação
        //SecurityContextHolder.getContext().setAuthentication(null);

        if(request.getRequestURI().startsWith("/company")){
            // Se o cabeçalho Authorization estiver presente
            if(header != null){
                // Valida o token com o JWTProvider (retorna o "subject", ou seja, a identificação do usuário/empresa)
                var token = this.jwtProvider.validateToken(header);

                // Se o token for inválido (não contém subject), retorna erro 401 (Não autorizado)
                if(token == null){
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                // Se o token for válido, adiciona o ID da empresa como atributo na requisição
                request.setAttribute("company_id", token.getSubject());
                var roles = token.getClaim("roles").asList(Object.class);
                var grants = roles.stream().map( role -> new SimpleGrantedAuthority("ROLE_"+role.toString())).toList();

                // Cria um objeto de autenticação do Spring Security com o subject do token
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(token.getSubject(), null, grants);

                // Define a autenticação no contexto de segurança para esta requisição
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // Passa a requisição adiante na cadeia de filtros
        filterChain.doFilter(request, response);
    }
}