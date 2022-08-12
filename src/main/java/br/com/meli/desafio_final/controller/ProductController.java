package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.dto.BatchesByProductDto;
import br.com.meli.desafio_final.model.entity.Product;
import br.com.meli.desafio_final.model.enums.Category;
import br.com.meli.desafio_final.service.implementation.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/fresh-products")
public class ProductController {


    @Autowired
    private ProductService service;

    /**
     * Nesse método retornamos uma lista com todos os produtos
     * @return
     */

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.findAllProducts());
    }

    /**
     * Nesse método retornamos uma lista de produtos por categoria
     * @param querytype
     * @return
     */

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getByCategory(@RequestParam Category querytype) {
        return ResponseEntity.ok(service.findByCategory(querytype));
    }

    /**
     * Nesse método retornamos produto por lote (batch product )
     * @param productId
     * @param s
     * @return
     */
    @GetMapping("/sortlist")
    public ResponseEntity<BatchesByProductDto> findBatchByProduct(@RequestParam Long productId, String s) {
        return ResponseEntity.ok(service.findBatchByProduct(productId, s));
    }
}
