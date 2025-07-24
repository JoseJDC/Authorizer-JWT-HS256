package com.hnk.auth.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final String secretKey;

    public JwtAuthenticationFilter(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwtToken = authHeader.substring(7);
            try {
                Claims claims = Jwts.parser()
                        .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                        .build()
                        .parseSignedClaims(jwtToken)
                        .getPayload();

                String username = claims.getSubject();
                Object rawAuthorities = claims.get("authorities");

                List<String> authorities = new ArrayList<>();
                if (rawAuthorities instanceof List<?>) {
                    for (Object item : (List<?>) rawAuthorities) {
                        if (item instanceof String) {
                            authorities.add((String) item);
                        }
                    }
                }

                List<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
                        .map(SimpleGrantedAuthority::new).toList();

                if (username != null) {
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (ExpiredJwtException e) {
                request.setAttribute("exception", "expiration");
            } catch (SignatureException e) {
                request.setAttribute("exception", "signature");
            } catch (JwtException e) {
                request.setAttribute("exception", "token");
            }
        }

        filterChain.doFilter(request, response);
    }
}
