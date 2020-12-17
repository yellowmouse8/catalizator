package letsCode.catalizator.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import letsCode.catalizator.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private String expirationTime;

    public String extractUserName(String authToken) {
        String key = Base64.getEncoder().encodeToString(secret.getBytes());
        return getClaimsForToken(authToken)
                .getSubject();

    }

    public Claims getClaimsForToken(String authToken) {
        String key = Base64.getEncoder().encodeToString(secret.getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(authToken)
                .getBody();
    }

    public boolean validateToken(String authToken) {
    return getClaimsForToken(authToken)
            .getExpiration()
            .before(new Date());
    }
    public String generateToken (User user){
        HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put("role", List.of(user.getRole()));
        long expirationSeconds = Long.parseLong(expirationTime);
        Date creationDate = new Date();
        Date expirationDate = new Date(creationDate.getTime() + expirationSeconds * 1000);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(creationDate)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
}
