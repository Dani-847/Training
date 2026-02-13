package org.drk.reto4_ad_turismo.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String API_PATTERN = "/api/**";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Permitir GET en todos los endpoints (lectura pública)
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        // Requiere autenticación para operaciones de escritura
                        .requestMatchers(HttpMethod.POST, API_PATTERN).authenticated()
                        .requestMatchers(HttpMethod.PUT, API_PATTERN).authenticated()
                        .requestMatchers(HttpMethod.DELETE, API_PATTERN).authenticated()
                        .requestMatchers(HttpMethod.PATCH, API_PATTERN).authenticated()
                        // Cualquier otra petición permitida
                        .anyRequest().permitAll()
                )
                .httpBasic(basic -> basic.authenticationEntryPoint(customAuthenticationEntryPoint()));
        return http.build();
    }

    @Bean
    public AuthenticationEntryPoint customAuthenticationEntryPoint() {
        return (request, response, e) -> {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            String jsonResponse = """
                    {
                        "error": "Unauthorized",
                        "message": "Se requiere autenticación para realizar esta operación. Use credenciales válidas (Basic Auth).",
                        "errorCode": 401
                    }
                    """;
            response.getWriter().write(jsonResponse);
        };
    }

}