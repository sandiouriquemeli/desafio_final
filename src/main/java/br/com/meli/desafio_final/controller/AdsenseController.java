package br.com.meli.desafio_final.controller;


import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.service.AdsenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/adsenses")
public class AdsenseController {

    @Autowired
    private AdsenseService adsenseService;

    //Método necessário POST requisito 2
    @GetMapping("/{id}")
    public ResponseEntity<Adsense> findById(@PathVariable Long id) {
        return ResponseEntity.ok(adsenseService.findById(id));
    }

    //Método necessário POST requisito 2
    @GetMapping
    public ResponseEntity<List<Adsense>> findAll() {
        return ResponseEntity.ok(adsenseService.findAll());
    }
}
