package com.duoc.backend;

import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static com.duoc.backend.Constants.SUPER_SECRET_KEY;
import static com.duoc.backend.Constants.getSigningKey;

import io.jsonwebtoken.Jwts;

@Component
public class JWTAuthenticationConfig {

    public String getJWTToken(UserDetails userDetails) {

        // Convertimos roles a String (ej: ROLE_ADMIN)
        String authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("authorities", authorities) 
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día
                .signWith((SecretKey) getSigningKey(SUPER_SECRET_KEY))
                .compact();
    }
}