package com.adam.user.infrastructure.token;

import com.adam.user.domain.Account;
import com.adam.user.domain.TokenProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Component public class JWTokenProvider implements TokenProvider {
    private final SecretKey secretKey;
    private final long expiration;

    public JWTokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration) {
        this.secretKey = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secret)
        );

        this.expiration = expiration;
    }


    @Override public String generateTokenFrom(Account account) {
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

    @Override public Map<String, Object> extractClaimsFrom(String jwt) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}
