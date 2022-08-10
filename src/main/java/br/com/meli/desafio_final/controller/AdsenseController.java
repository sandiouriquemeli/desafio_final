package br.com.meli.desafio_final.controller;


import br.com.meli.desafio_final.dto.AdsenseIdDto;
import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.model.enums.Category;
import br.com.meli.desafio_final.service.implementation.AdsenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/adsenses")
public class AdsenseController {

    @Autowired
    private AdsenseService service;

    @GetMapping("/{id}")
    public ResponseEntity<Adsense> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Adsense>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/list")
    public ResponseEntity<List<Adsense>> findByCategory(@RequestParam Category querytype) {
        return ResponseEntity.ok(service.findByCategory(querytype));
    }

    @GetMapping("/test/{productId}")
    public ResponseEntity<List<AdsenseIdDto>> findByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(service.findByProductId(productId));
    }
}
