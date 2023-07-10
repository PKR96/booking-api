package com.abiolabs.mu.bookingapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class JWTSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public JWTSecurityConfiguration(JWTAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(getCorsCustomizer())
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/signup")
                .permitAll()
                .antMatchers("/auth/login")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    private Customizer<CorsConfigurer<HttpSecurity>> getCorsCustomizer() {
        return customize -> {
            CorsConfigurationSource corsConfigurationSource = request -> {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
                corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
                return corsConfiguration;
            };

            customize.configurationSource(corsConfigurationSource);
        };
    }
}
