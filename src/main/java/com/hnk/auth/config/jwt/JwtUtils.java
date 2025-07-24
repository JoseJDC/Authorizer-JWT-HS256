package com.hnk.auth.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtUtils {

    @Value("${security.issuer}")
    private String issuerName;

    @Value("${security.key}")
    private String secretKey;

    @Value("${security.expiration}")
    private long expirationMinutes;

    public String generateToken(Authentication authentication) {
        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        long now = System.currentTimeMillis();
        long expiration = now + expirationMinutes * 60_000L;

        return Jwts.builder().issuer(issuerName)
                .subject(authentication.getName())
                .claim("authorities", authorities)
                .issuedAt(new Date(now))
                .expiration(new Date(expiration))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), Jwts.SIG.HS256)
                .compact();
    }
}
