package com.abiolabs.mu.bookingapi.utils;

import com.abiolabs.mu.bookingapi.data.RoleData;
import com.abiolabs.mu.bookingapi.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.TestPropertySource;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class JWTUtilsTest {

    @Autowired
    private JWTUtils jwtUtils;

    private Set<Role> roleSet;
    private String jwt;

    private static final String USERNAME = "pawan";
    private static final String INVALID_TOKEN = "NotAValidToken";

    @BeforeEach
    void init() {
        roleSet = Collections.singleton(RoleData.getRole());
        jwt = jwtUtils.generateJWT(USERNAME, roleSet);
    }

    @Test
    void testGenerateJWT() {
        jwtUtils.generateJWT(USERNAME, roleSet);
        assertNotNull(jwt);
        assertTrue(jwt.contains("ey"));
    }

    @Test
    void testValidateJWTToken_returnTrue() {
        assertTrue(jwtUtils.validateJWTToken(jwt));
    }

    @Test
    void testValidateJWTToken_returnFalse() {
        assertFalse(jwtUtils.validateJWTToken(INVALID_TOKEN));
    }

    @Test
    void testGetAuthenticationFromToken() {
        Authentication authentication = this.jwtUtils.getAuthenticationFromToken(jwt);
        assertNotNull(authentication);
        assertEquals(USERNAME, authentication.getPrincipal());
    }

    @Test
    void testGetAuthenticationFromToken_returnNull() {
        Authentication authentication = this.jwtUtils.getAuthenticationFromToken(INVALID_TOKEN);
        assertNull(authentication);
    }
}
