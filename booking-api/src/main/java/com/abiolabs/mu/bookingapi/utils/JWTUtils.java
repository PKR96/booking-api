package com.abiolabs.mu.bookingapi.utils;


import com.abiolabs.mu.bookingapi.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JWTUtils {

    @Value("${jwt.session.time}")
    private long sessionTime;

    @Value("${jwt.session.password}")
    private String password;

    private Key key;


    @PostConstruct
    public void initVariables() {
        byte[] keyBytes = password.getBytes();
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateJWT(String username, Set<Role> roles) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + sessionTime))
                .signWith(key)
                .compact();
    }

    public boolean validateJWTToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            Date expiryDate = claimsJws.getBody().getExpiration();
            return expiryDate.after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            log.error("Error identifying parsed JTW with error: {}", e.getMessage());
            return false;
        }
    }

    public Authentication getAuthenticationFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException | IllegalArgumentException exception) {
            log.error("Error in authentication token: {}", exception.getMessage());
            return null;
        }
        String userName = claims.getSubject();
        List<Map<String,String>> roles = claims.get("roles", List.class);
        List<SimpleGrantedAuthority> authorityList = roles
                .stream()
                .map(roleMap -> roleMap.get("roleType"))
                .map(String::valueOf)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(userName, null, authorityList);
    }

}
