package br.com.meli.desafio_final.controller;


import br.com.meli.desafio_final.dto.AdsenseByWarehouseDto;
import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.model.enums.Category;
import br.com.meli.desafio_final.repository.BatchRepository;
import br.com.meli.desafio_final.service.implementation.AdsenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/adsenses")
public class AdsenseController {

    @Autowired
    private AdsenseService adsenseService;

    @Autowired
    private BatchRepository batchRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Adsense> findById(@PathVariable Long id) {
        return ResponseEntity.ok(adsenseService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Adsense>> findAll() {
        return ResponseEntity.ok(adsenseService.findAll());
    }

    @GetMapping("/list")
    public ResponseEntity<List<Adsense>> findByCategory(@RequestParam Category querytype) {
        return ResponseEntity.ok(adsenseService.findByCategory(querytype));
    }

    @GetMapping("/warehouse/{adsenseId}")
    public ResponseEntity <List<AdsenseByWarehouseDto>> getByAdsenseByWarehouse(@PathVariable Long adsenseId) {
        return ResponseEntity.status(HttpStatus.OK).body(batchRepository.getAdsenseByWarehouse(adsenseId));
    }
}
