package com.darwinruiz.atlas_bank.shared.configurations;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(HttpMethod.POST, "/api/v1/accounts").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/accounts").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/accounts/{id}").hasAnyRole("USER", "ADMIN")

                                .requestMatchers(HttpMethod.POST, "/api/v1/transactions/transfer")
                                .hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/transactions{id}/transactions")
                                .hasAnyRole("USER", "ADMIN")

                                .requestMatchers("/h2-console/**").permitAll()
                                .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt
                        .jwtAuthenticationConverter(jwtAuthenticationConverter())))
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
            if (realmAccess == null || realmAccess.isEmpty() || realmAccess.get("roles") == null) {
                return List.of();
            }

            List<String> roles = (List<String>) realmAccess.get("roles");

            return roles.stream()
                    .map(role -> new SimpleGrantedAuthority(role))
                    .collect(Collectors.toList());
        });

        return converter;
    }

}
