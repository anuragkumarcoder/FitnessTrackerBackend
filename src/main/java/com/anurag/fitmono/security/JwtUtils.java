package com.anurag.fitmono.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {
    private String jwtSecret = "Zm9vYmFyYmF6cXV4cXV1eGNvcnRlZ2U0M3JlZ2VyZ2V3cmVncmVncmU=";
    private int jwtExpirationMs = 30600000; // 8 hours 30 minutes
    private final long CLOCK_SKEW_SECONDS = 60;

    public String generateToken(String userId, String role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);
        return Jwts.builder()
                .subject(userId)
                .claim("roles", List.of(role))
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key())
                .compact();
    }

    public String getJWTFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().verifyWith(key()).clockSkewSeconds(CLOCK_SKEW_SECONDS).build().parseSignedClaims(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) { return false; }
    }

    public String getUserIdFromJwtToken(String token) {
        return getAllClaims(token).getSubject();
    }

    public Claims getAllClaims(String token) {
        return Jwts.parser().verifyWith(key()).clockSkewSeconds(CLOCK_SKEW_SECONDS).build().parseSignedClaims(token).getPayload();
    }

    private SecretKey key() { return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)); }
}