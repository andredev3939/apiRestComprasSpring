package com.example.crud.domain.user;
/* Classe para as roles dos usuários (o atributo funcao vai ser instanciado dessa classe) */
public enum UserRole { // Enum representa um grupo de constantes (conjunto fixo de valores)
    ADMIN("admin"),
    USER("user");


    private String role; // Parâmetro do construtor

    UserRole(String role){ // Construtor que vai instanciar a role com new SimpleGrantedAuthority
        this.role = role;
    }
    public String getRole(){
        return role;
    }
}
