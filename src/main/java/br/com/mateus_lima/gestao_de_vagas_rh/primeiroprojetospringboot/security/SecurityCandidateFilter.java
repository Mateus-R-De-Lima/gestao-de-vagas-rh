package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.security;

import br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.providers.JWTCandidateProvider;
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

@Component
public class SecurityCandidateFilter extends OncePerRequestFilter {

    @Autowired
    private JWTCandidateProvider jwtCandidateProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if(request.getRequestURI().startsWith("/candidate")){
            SecurityContextHolder.getContext().setAuthentication(null);
            if (header != null) {
                var token = this.jwtCandidateProvider.validateToken(header);

                if (token == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                request.setAttribute("candidate_id",token.getSubject());
                var roles = token.getClaim("roles").asList(Object.class);

              var grants = roles.stream()
                        .map( role -> new SimpleGrantedAuthority("ROLE_"+role.toString())).toList();

                // Cria um objeto de autenticação do Spring Security com o subject do token
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(token.getSubject(), null, grants);

                // Define a autenticação no contexto de segurança para esta requisição
                SecurityContextHolder.getContext().setAuthentication(auth);

            }
        }

        filterChain.doFilter(request, response);
    }
}
