package com.example.crud.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/* Desabilitar configurações padrões (como aquela tela de login) e implementar as do sistema */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    /* Filtros aplicados (validações no usuário) numa requisição para garantir a segurança validando ou não */
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // Expressão lambda seguem o padrão (parâmetros) -> { corpo }- "Quando você me der o objeto csrf, eu vou chamar o metodo disable() nele."
                .csrf(csrf -> csrf.disable()) // a proteção de segurança CSRF tem que ser desativada em API REST, pois ela bloqueia requisições que não sejam GET e não têm token CSRF embutido
                /* Autenticação via token (statless - padrão é sem sessão, diferente da stateful) */
                .sessionManagement((session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)))
                /* Autorizar métodos HTTP e definir qual é a regra que cada um vai seguir */
                .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(HttpMethod.POST, "/product").hasRole("ADMIN") // Somente admins podem adicionar produtos
                    .anyRequest().authenticated() // Para todos os outros métodos, bastar estar autenticado (token) - valida no AuthenticationController
                )
                .build();
    }
    /* Permite chamar o gerenciador da autenticação como um bean em todo o código */
    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /* Criptografa com Hash! */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
