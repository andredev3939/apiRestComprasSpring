package com.example.crud.domain.product;

import jakarta.persistence.*;
import lombok.*;

/* Representa a tabela no banco de dados*/
@Table(name="product")
@Entity(name="product")
/* Anotações do Lombock */
@Getter
@Setter
@AllArgsConstructor // Gera um construtor com todos os parâmetros para os campos da classe.
@NoArgsConstructor // Construtor que não recebe nenhum parâmetro e não seta nada (Esse e o próximo são úteis no CRUD)
@EqualsAndHashCode(of = "id") // Indica o tipo de primary key
public class Product {
    /* Representa as colunas no banco de dados */
    @Id @GeneratedValue(strategy = GenerationType.UUID) // Gera o id automaticamente
    private String id;
    private String name;
    private Integer price_in_cents;
    private Boolean active; // Recurso de tornar ativo ou inativo pro verbo DELETE

    public Product(RequestProduct requestProduct){ // Graças a anotation @AllArgsContruct, não é necessário colocar todas as colunas aqui como parâmetro
        this.name = requestProduct.name();
        this.price_in_cents = requestProduct.price_in_cents();
        this.active = true; // Seta por padrão como true, não necessitando informar no JSON
    }
}

