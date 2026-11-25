package org.drk.ad_examen_18_03_26.security;

import jakarta.servlet.http.HttpServletResponse;
import org.drk.ad_examen_18_03_26.dto.ErrorResponseDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import tools.jackson.databind.ObjectMapper;

import java.util.Base64;

/**
 * Configuración de seguridad de la aplicación.
 * Protege las operaciones de escritura (POST, PUT, DELETE) con autenticación básica
 * y permite las operaciones de lectura (GET) sin autenticación.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configura la cadena de filtros de seguridad.
     * - GET: acceso público.
     * - POST, PUT, DELETE: requieren rol ADMIN.
     *
     * @param http configuración de seguridad HTTP
     * @return la cadena de filtros configurada
     * @throws Exception si ocurre un error en la configuración
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth -> auth
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                ).httpBasic(basic ->
                        basic.authenticationEntryPoint(customAuthenticationEntryPoint())
                );
        return http.build();
    }


    /**
     * Define un único usuario de administración en memoria.
     *
     * @return servicio de usuarios con el usuario admin configurado
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password("{noop}admin123")
                        .roles("ADMIN")
                        .build()
        );
    }

    /**
     * Punto de entrada personalizado para errores de autenticación.
     * Devuelve un JSON con el detalle del error 401.
     *
     * @return el AuthenticationEntryPoint personalizado
     */
    @Bean
    public AuthenticationEntryPoint customAuthenticationEntryPoint() {
        return (request, response, e) -> {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ErrorResponseDTO error = new ErrorResponseDTO(
                    "Usuario no autorizado",
                    "El usuario y contraseña no coinciden",
                    401
            );
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(error));
        };
    }

}