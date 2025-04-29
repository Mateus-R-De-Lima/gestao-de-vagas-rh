package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTProvider {

    // Injeta a chave secreta definida no application.properties (ou .yml) para assinar e verificar o token
    @Value("${security.algorithm-key}")
    private String securityKey;

    /**
     * Método responsável por validar um token JWT recebido.
     * Se o token for válido, retorna o "subject" (informação principal do token, como o ID ou email).
     * Se for inválido, retorna uma string vazia.
     */
    public String validateToken(String token) {
        // Remove o prefixo "Bearer " do token, caso esteja presente no cabeçalho Authorization
        token = token.replace("Bearer ", "");

        // Define o algoritmo e a chave usados para verificar a assinatura do token
        Algorithm algorithm = Algorithm.HMAC256(securityKey);

        try {
            // Verifica se o token é válido com base no algoritmo e na assinatura
            var subject = JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject(); // Recupera o "subject" que foi definido na criação do token (ex: company_id)

            return subject; // Se tudo ocorrer bem, retorna o subject (identificação do dono do token)
        } catch (JWTVerificationException ex) {
            // Caso o token seja inválido ou expirado, imprime o erro e retorna string vazia
            ex.printStackTrace(); // Em produção, o ideal é logar de forma segura e não exibir pilhas
            return "";
        }
    }
}