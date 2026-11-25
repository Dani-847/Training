package org.drk.Reto6.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuracion de seguridad de la aplicacion web de turismo.
 *
 * <p>Define las reglas de acceso, el formulario de login y la gestion de sesion:</p>
 * <ul>
 *   <li>Las paginas de listado y detalle son publicas (cualquier visitante puede consultar hoteles).</li>
 *   <li>Las operaciones de escritura (crear, editar, eliminar) requieren rol {@code ADMIN}.</li>
 *   <li>La autenticacion se realiza mediante formulario de login con sesion HTTP.</li>
 * </ul>
 *
 * <p>Usuarios precargados en memoria:</p>
 * <ul>
 *   <li>{@code admin} / {@code admin123} - rol ADMIN (lectura y escritura)</li>
 *   <li>{@code usuario} / {@code user123} - rol USER (solo lectura)</li>
 * </ul>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {


    /**
     * Proveedor de autenticacion que conecta explicitamente el {@link UserDetailsService}
     * con el {@link PasswordEncoder}.
     *
     * <p>Es necesario configurarlo de forma explicita en Spring Security 6 para que
     * BCrypt se use correctamente al verificar la contrasena del formulario de login.</p>
     *
     * @return proveedor de autenticacion configurado
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authenticationProvider(authenticationProvider())
            .authorizeHttpRequests(auth -> auth
                // Recursos estaticos y paginas publicas
                .requestMatchers("/css/**", "/js/**", "/img/**", "/error").permitAll()
                .requestMatchers(HttpMethod.GET, "/", "/hoteles", "/hoteles/{id}").permitAll()
                .requestMatchers("/login").permitAll()
                // Operaciones de escritura: solo ADMIN
                .requestMatchers(HttpMethod.GET, "/hoteles/nuevo").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/hoteles/*/editar").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/hoteles/**").hasRole("ADMIN")
                // Todo lo demas requiere autenticacion
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/hoteles", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/hoteles")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }

    /**
     * Configura los usuarios en memoria para la autenticacion.
     *
     * <p>En produccion se sustituiria por un {@code UserDetailsService} con base de datos.</p>
     *
     * @return gestor de usuarios en memoria con los usuarios predefinidos
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();

        UserDetails usuario = User.withUsername("usuario")
                .password(passwordEncoder().encode("user123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, usuario);
    }

    /**
     * Bean del codificador de contrasenas BCrypt.
     *
     * <p>Se usa para cifrar las contrasenas de los usuarios en memoria y para
     * verificar las contrasenas durante el proceso de autenticacion.</p>
     *
     * @return instancia de {@link BCryptPasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
