package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.dto.AdsensByDueDateAndCategoryDto;
import br.com.meli.desafio_final.dto.AdsenseBySectionAndDueDateDto;
import br.com.meli.desafio_final.dto.BatchDto;
import br.com.meli.desafio_final.repository.BatchRepository;
import br.com.meli.desafio_final.service.implementation.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batch")
public class BatchController {
    @Autowired
    BatchService service;

    /**
     * Nesse método retornamos uma lista de anúncio por Id
     * @param adsenseId
     */
    @GetMapping("/{adsenseId}")
    public ResponseEntity<List<BatchDto>> findAllByAdsenseId(@PathVariable Long adsenseId) {
        return ResponseEntity.ok(service.findAllByAdsenseId(adsenseId));
    }

    /**
     * Esse método retorna uma lista de todos os lotes armazenados em um setor de um armazém,
     * filtrados por um período de vencimento
     * e ordenados por sua data de validade
     * @param sectionId
     * @param numberOfDays
     */
    @GetMapping("/due-date")
    public ResponseEntity<List<AdsenseBySectionAndDueDateDto>> findAdsenseBySectionAndDueDate(@RequestParam long sectionId, @RequestParam int numberOfDays) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAdsenseBySectionAndDueDate(sectionId, numberOfDays));
    }

    /**
     * Esse método retorna uma lista de lote dentro do prazo de validade solicitado,
     * que pertencem a uma determinada categoria de produto
     * ordenada de forma crescente ou decrescente pela quantidade
     *
     * @param numberOfDays
     * @param category
     * @param order
     */
    @GetMapping("/due-date/list")
    public ResponseEntity<List<AdsensByDueDateAndCategoryDto>> findAdsenseByDueDateAndCategory(@RequestParam int numberOfDays, @RequestParam String category, @RequestParam String order) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAdsenseByDueDateAndCategory(numberOfDays, category, order));
    }

}
