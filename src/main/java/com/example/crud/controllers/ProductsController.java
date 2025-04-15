package com.example.crud.controllers;

import com.example.crud.domain.product.Product;
import com.example.crud.repositories.ProductRepository;
import com.example.crud.domain.product.RequestProduct;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductsController {
    @Autowired // Injeta a dependência do repository que vai fazer a operação SQL na entidade do domain
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity getAllProducts(){ // Classe padrão de respostas
        var allProducts = repository.findAllByActiveTrue(); // Operação do repository
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data){ // Pega o conteúdo do body como parâmetro e valida segundo os tipos dos parâmetros do DTO
        Product newProduct = new Product(data); // Manipulando classe Product para POST
        repository.save(newProduct);
        return ResponseEntity.ok().build();
        /* return ResponseEntity.ok().build(); // Teste inicial com response vazio usando o JSON: {"name":"Camisa","price_in_cents":100} -> gera 200 OK e RequestProduct[name=Camisa, price_in_cents=100] no console */
    }

    @PutMapping
    @Transactional // Indica que é uma transação (mais de um comando SQL no banco)
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct data){
        Optional<Product> optionalProduct = repository.findById(data.id()); // Busca no banco de acordo com o ID (marcado como optional pois o dado pode não existir)
        if (optionalProduct.isPresent()){ // Retorna a entidade, da onde eu instancio product se houver o dado no banco
            Product product = optionalProduct.get();
            /* Atribuindo novos valores com os dados do body usando os getters e setters criados automaticamente pelo Lombock */
            product.setName(data.name());
            product.setPrice_in_cents(data.price_in_cents());
            return ResponseEntity.ok(product); // Teste com: {"name": "Pacote GPT anual","price_in_cents": 2000,"id": "eda65e06-1125-4d3d-834b-315df4d78f82"}
        } else {
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping("/{id}") // A melhor prática para DELETE em API Rest é passar o id como parâmetro na URL
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable String id){ // Indica que o id no path é o parâmetro da requisição no controller
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setActive(false); // O dado não é deletado na tabela, mas sim desativado (GET só retorna dados ativos)
            return ResponseEntity.ok(product);
        } else {
            throw new EntityNotFoundException();
        }
    }
}

