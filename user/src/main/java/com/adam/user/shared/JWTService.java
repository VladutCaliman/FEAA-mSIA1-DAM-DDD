package com.adam.user.shared;

import com.adam.user.domain.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Service public class JWTService {
    private final SecretKey secretKey;
    private final long expiration;

    public JWTService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration) {
        this.secretKey = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secret)
        );

        this.expiration = expiration;
    }

    public String generateTokenFrom(Account account) {
        Instant now = Instant.now();

        return Jwts.builder()
                .subject(account.getId().toString())
                .claim("username", account.getUsername())
                .claim("role", account.getRole().name())

                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(expiration)))
                .signWith(secretKey)
                .compact();
    }

    public Claims extractClaimsFrom(String jwt) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}
