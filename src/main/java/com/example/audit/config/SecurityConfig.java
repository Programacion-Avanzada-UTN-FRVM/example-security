package com.example.audit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Esta clase configura Spring Security para que acepte peticiones HTTP con la
 * cabecera de "Authorization Basic" en los controladores.
 *
 * Esto es codigo repetitivo, boilerplate y poco util, no perder tanto tiempo
 * intentando entenderlo a menos que se quiera aprender mas sobre Spring
 * Security e implementar otra estrategia de autorizacion.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http)
                        throws Exception {
                http
                                // Deshabilitar CSRF.
                                .csrf().disable()
                                // Autorizar las peticiones HTTP, solo si estan autenticadas y solo si son a la
                                // ruta "/api/entities/**".
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/api/entities/**").authenticated())

                                // Configurar la autenticacion basica.
                                .httpBasic(httpBasic -> {
                                })
                                // Deshabilitar informacion de estado de las sesiones (todo stateless)
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                // Implementar entry point personalizado para agregar un mensaje de error.

                // Retornar la configuracion del filtro de seguridad.
                return http.build();
        }

        @Bean
        public UserDetailsService userDetailsService() {
                // Usuario de prueba 1.
                UserDetails user = User.withDefaultPasswordEncoder()
                                .username("pepito")
                                .password("pepito123")
                                .roles("usuario")
                                .build();

                // Usuario de prueba 2.
                UserDetails user2 = User.withDefaultPasswordEncoder()
                                .username("lmaza")
                                .password("maza123")
                                .roles("usuario")
                                .build();

                // Usuario de prueba 3.
                UserDetails user3 = User.withDefaultPasswordEncoder()
                                .username("jvanzetti")
                                .password("vanzetti")
                                .roles("usuario")
                                .build();

                return new InMemoryUserDetailsManager(user, user2, user3);
        }
}