package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.model.entity.Product;
import br.com.meli.desafio_final.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/fresh-products") //adicionando endpoint v2 -> requisito 2
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAllProducts());
    }
}
