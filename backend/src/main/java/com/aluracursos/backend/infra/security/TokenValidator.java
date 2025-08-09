package com.aluracursos.backend.infra.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class TokenValidator {

    private final SecretKey secretKey;

    public TokenValidator(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public Claims validateToken(String token) {
        return Jwts.parserBuilder() // 👈 parserBuilder, no parser()
                .setSigningKey(secretKey) // 👈 key as SecretKey
                .build()
                .parseClaimsJws(token)
                .getBody(); // 👈 return Claims directly
    }

}
