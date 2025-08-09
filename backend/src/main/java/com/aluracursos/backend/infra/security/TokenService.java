package com.aluracursos.backend.infra.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class TokenService {

    private final SecretKey secretKey;

    public TokenService(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000)) // 1 hora
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Correct return type for validateToken
    public Jws<Claims> validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
    }

    // ✅ Correct method name for extracting username from token
    public String extraerCorreo(String token) {
        return validateToken(token).getBody().getSubject();
    }
}
