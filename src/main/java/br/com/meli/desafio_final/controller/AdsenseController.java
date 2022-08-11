package br.com.meli.desafio_final.controller;


import br.com.meli.desafio_final.dto.AdsenseByWarehouseDto;
import br.com.meli.desafio_final.dto.AdsenseIdDto;
import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.model.enums.Category;
import br.com.meli.desafio_final.repository.BatchRepository;
import br.com.meli.desafio_final.service.implementation.AdsenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/adsenses")
public class AdsenseController {

    @Autowired
    private AdsenseService adsenseService;

    /**
     * Nesse método vamos retornar anúncio( Adsense) atráves do Id
     * @param id
     * @return
     */
    // TODO: colocar DTO
    @GetMapping("/{id}")
    public ResponseEntity<Adsense> findById(@PathVariable Long id) {
        return ResponseEntity.ok(adsenseService.findById(id));
    }

    /**
     * Nesse método retornamos uma lista de anúncios
     * @return
     */
    // TODO: colocar DTO
    @GetMapping
    public ResponseEntity<List<Adsense>> findAll() {
        return ResponseEntity.ok(adsenseService.findAll());
    }

    /**
     * Nesse método retornamos anúncio listado por categoria
     * @param querytype
     * @return
     */
    // TODO: colocar DTO
    @GetMapping("/list")
    public ResponseEntity<List<Adsense>> findByCategory(@RequestParam Category querytype) {
        return ResponseEntity.ok(adsenseService.findByCategory(querytype));
    }

    /**
     * Nesse método retornamos um lista de anúncio por armazém / ou a qual armazém esse anúncio pertece
     * @param adsenseId
     * @return
     */
    @GetMapping("/warehouse/{adsenseId}")
    public ResponseEntity <List<AdsenseByWarehouseDto>> getByAdsenseByWarehouse(@PathVariable Long adsenseId) {
        return ResponseEntity.status(HttpStatus.OK).body(adsenseService.findAdsenseByWarehouseAndQuantity(adsenseId));
    }
}
