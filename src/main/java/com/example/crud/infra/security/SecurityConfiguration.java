package com.example.crud.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/* Desabilitar configurações padrões (como aquela tela de login) e implementar as do sistema */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    /* Filtros aplicados (validações no usuário) numa requisição para garantir a segurança validando ou não */
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // a proteção de segurança CSRF tem que ser desativada em API REST, pois ela bloqueia requisições que não sejam GET e não têm token CSRF embutido
                /* Autenticação via token (statless - padrão é sem sessão, diferente da stateful) */
                .sessionManagement((session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)))
                .build();
    }

}
