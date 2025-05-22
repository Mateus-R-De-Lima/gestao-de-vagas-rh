package br.com.mateus_lima.gestao_de_vagas_rh.modules.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TestsUtils {

    public static String objectToString(Object object) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toToken(UUID idCompany, String securityKey) {
        Algorithm algorithm = Algorithm.HMAC256(securityKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        return JWT.create()
                .withIssuer("java-vagas")
                .withClaim("roles", List.of("company"))
                .withExpiresAt(expiresIn)
                .withSubject(idCompany.toString())
                .sign(algorithm);

    }
}
