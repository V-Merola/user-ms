package com.vincenzomerola.user_ms.config;

import com.vincenzomerola.user_ms.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
*CSRF: Disabilitato perché l'API è stateless.
* Autorizzazioni delle Richieste: requestMatchers("/public/**").permitAll()
* permette l'accesso pubblico agli endpoint /public/**.
* Tutte le altre richieste richiedono autenticazione.

*Gestione delle Sessioni:
* sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
* configura Spring Security per non mantenere lo stato delle sessioni.

* Filtro JWT:
* addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
* aggiunge JwtAuthenticationFilter prima del filtro di autenticazione
* standard di Spring Security.
*/

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/public/**").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
