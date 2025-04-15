package com.example.crud.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crud.domain.user.User; // Atenção para não importar o user do Spring Security, mas sim do domain
import org.springframework.security.core.userdetails.UserDetails;

/* Opera no banco a busca por autorização de acordo com o domain User e UserRole */
public interface UserRepository extends JpaRepository<User, String> {
    /* Pela assinatura padrão, o JPA implementa a query */
    UserDetails findByLogin(String login); // findBy busca um query com o próximo valor como parâmetro (tipo no parâmetro)
}
