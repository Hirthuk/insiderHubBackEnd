package com.insider.backend.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    private final long EXPIRATION_MILLS = 1000*60*60*10; // 10 hours

    public String generateToken(Long sapid, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);

        return Jwts.builder()
                .subject(sapid.toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLS))
                .claims(claims)
                .signWith(SECRET_KEY, Jwts.SIG.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    
    public String extractUsername(String token) {
    	return extractAllClaims(token).getSubject();
    }
    
    public String extractRole(String token) {
    	return extractAllClaims(token).get("role", String.class);
    }
    
    public boolean isTokenExpired(String token) {
    	return extractAllClaims(token).getExpiration().before(new Date());
    }
}
