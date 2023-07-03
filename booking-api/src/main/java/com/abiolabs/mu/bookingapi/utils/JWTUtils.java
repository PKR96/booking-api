package com.abiolabs.mu.bookingapi.utils;


import com.abiolabs.mu.bookingapi.entity.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class JWTUtils {

    @Value("${jwt.session.time")
    private static long sessionTime;

    public static String generateJWT(String username, Set<Role> roles) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        SecureRandom secureRandom = new SecureRandom();
        keyGenerator.init(secureRandom);
        return Jwts.builder()
                .setSubject(username)
                .claim("roles",roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + sessionTime))
                .signWith(keyGenerator.generateKey())
                .compact();
    }
}
