package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.dto.BatchDto;
import br.com.meli.desafio_final.dto.BatchesByProductDto;
import br.com.meli.desafio_final.model.entity.Product;
import br.com.meli.desafio_final.model.enums.Category;
import br.com.meli.desafio_final.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/fresh-products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.findAllProducts());
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getByCategory(@RequestParam Category querytype) {
        return ResponseEntity.ok(service.findByCategory(querytype));
    }

    @GetMapping("/test")
    public ResponseEntity<BatchesByProductDto> findBatchByProduct(@RequestParam Long productId, String s) {
        return ResponseEntity.ok(service.findBatchByProduct(productId, s));
    }
}
