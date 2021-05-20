package com.cristianbalta.kubeengine.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.security.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtUtil {

    private final KeyPair keyPair;

    public JwtUtil(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    private Claims getJwtClaims(String jwt) throws Exception {
        try {
            return Jwts.parserBuilder().setSigningKey(keyPair.getPrivate()).build().parseClaimsJws(jwt).getBody();
        } catch (ExpiredJwtException ex) {
            throw new Exception("BAD_TOKEN");
        }
    }

    public String generateToken(String username) {

        return Jwts.builder().setSubject(username)
                .signWith(SignatureAlgorithm.RS256, keyPair.getPrivate())
                .setExpiration(Date.from(Instant.now().plus(10, ChronoUnit.SECONDS)))
                .compact();
    }

    public String extractUsername(String jwt) throws Exception {
        return (String) getJwtClaims(jwt).get("preferred_username");
    }

    private boolean jwtIsExpired(String token) throws Exception {

        return getJwtClaims(token).getExpiration().before(new Date());

    }

    public void validateToken(String token) throws Exception {

        if (jwtIsExpired(token)) {
            throw new Exception("TOKEN_EXPIRED");
        }
    }

}