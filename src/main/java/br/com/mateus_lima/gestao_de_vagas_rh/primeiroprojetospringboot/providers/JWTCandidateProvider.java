package br.com.mateus_lima.gestao_de_vagas_rh.primeiroprojetospringboot.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTCandidateProvider {

    @Value("${security.algorithm-key-candidate}")
    private String securityKey;

    public DecodedJWT validateToken(String token) {
        token = token.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.HMAC256(securityKey);

        try {
            var tokenDeconded = JWT.require(algorithm).build().verify(token);
            return tokenDeconded;
        } catch (JWTVerificationException ex) {
            return null;
        }

    }

}
