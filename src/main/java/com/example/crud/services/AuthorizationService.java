package com.example.crud.services;

import com.example.crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
/* Chamado autenticamente toda vez que o usuário precisar se autenticar (AuthorizationService) */
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository repository;
    @Override
    /* Consulta do usuário com o repositoryy */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
