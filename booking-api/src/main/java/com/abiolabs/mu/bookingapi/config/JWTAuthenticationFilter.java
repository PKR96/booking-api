package com.abiolabs.mu.bookingapi.config;

import com.abiolabs.mu.bookingapi.utils.JWTUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {


    private static final String BEARER = "Bearer ";

    private final JWTUtils jwtUtils;

    public JWTAuthenticationFilter(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = getTokenFromRequest(request);

        if (jwtToken != null && jwtUtils.validateJWTToken(jwtToken)) {
            Authentication authenticationToken = jwtUtils.getAuthenticationFromToken(jwtToken);
            if (authenticationToken != null) {
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);

    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            return authorizationHeader.replace(BEARER, "").trim();
        }

        return null;
    }
}
