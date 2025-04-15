package com.example.crud.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.crud.domain.user.AuthenticationDTO;

@RestController
@RequestMapping("auth") // Endpoint de autenticação
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager; // Responsável por autenticar os usuários, verificando se login e senha batem com os registros do sistema (já vem do Spring, mas tem de ser tratado)

    /* Validar o login de usuário e senha (usa padrão Hash - criptografa a senha) */
    /* Basicamente, recebe os dados da requisição, os torna em objetos Java e, com a criptografia, vê se bate com os dados do banco (são salvos com criptografia) */
    @PostMapping("/login")// fica /auth/login
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){ // Corpo é convertido em objeto da classe DTO
        /* Cria um token de autenticação com login e senha */
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        /* Valida as credenciais e retorna 401 se não forem válidas com authenticationManager */
        var auth = this.authenticationManager.authenticate((userNamePassword)); // Só é possível acessá-lo pois o mesmo foi programado como um bean em SecurityConfiguration e injetado aqui
        return ResponseEntity.ok().build();
    }
    }


