package com.twitter.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import java.util.*;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(String uuid,
                                String sessionId) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        Map<String, Object> claims = new HashMap<>();
        List<String> role = new ArrayList<>();
        role.add("user");
        claims.put("roles", role);
        claims.put("sessionId", sessionId);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(uuid)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Object getRolesByToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("roles");
    }

    public Object getSessionIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("sessionId");
    }

    public String getUUIDFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

        public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch(SignatureException ex) {
            log.warn("Invalid JWT signature");
        } catch(MalformedJwtException ex) {
            log.warn("Invalid JWT token");
        } catch(ExpiredJwtException ex) {
            log.info("Expired JWT token");
        } catch(UnsupportedJwtException ex) {
            log.warn("Unsupported JWT token");
        } catch(IllegalArgumentException ex) {
            log.warn("JWT claims string is empty.");
        }
        return false;
    }
}
