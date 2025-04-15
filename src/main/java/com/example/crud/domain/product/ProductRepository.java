package com.example.crud.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/* Interface que vai manipular SQL dentro das entidades. Ela recebe o nome da Entidade e o tipo da primary key da mesma como parâmetro
Métodos default: buscar valor por id, delete por id, delete all. Para mais coisas, tem que criar outros métodos */
public interface ProductRepository extends JpaRepository<Product,String> {
    List<Product>findAllByActiveTrue(); // Padrão de escrita do JPA, que gera a query automaticamente
}

