package com.twitter.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import java.util.*;

@Component
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

}
