package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.dto.BatchDto;
import br.com.meli.desafio_final.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/batch")
public class BatchController {
    @Autowired
    BatchService service;

    @GetMapping("/{adsenseId}")
    public ResponseEntity<List<BatchDto>> findAllByAdsenseId(@PathVariable Long adsenseId) {
        return ResponseEntity.ok(service.findAllByAdsenseId(adsenseId));
    }
}
