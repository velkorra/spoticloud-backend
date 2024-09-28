package com.example.spoticloudbackend.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

@Component
public class CustomJwtDecoder implements JwtDecoder {

    private final JwtService jwtService;

    public CustomJwtDecoder(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Jwt decode(String token) throws JwtException {
        try {

        if (!jwtService.validateToken(token)) {
            throw new JwtException("Invalid JWT token");
        }
        }
        catch (Exception e){
            throw new JwtException("Invalid JWT token");
        }

        Claims claims = jwtService.getJwtPayload(token);

        return Jwt.withTokenValue(token)
                .header("alg", "HS256")
                .claims(claimsSet -> {
                    claimsSet.putAll(claims);
                    if (claims.getSubject() != null) {
                        claimsSet.put("sub", claims.getSubject());
                    }
                    if (claims.getIssuedAt() != null) {
                        claimsSet.put("iat", claims.getIssuedAt().toInstant());
                    }
                    if (claims.getExpiration() != null) {
                        claimsSet.put("exp", claims.getExpiration().toInstant());
                    }
                })
                .build();

    }
}