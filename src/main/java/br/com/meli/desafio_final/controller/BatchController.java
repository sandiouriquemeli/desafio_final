package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.dto.AdsensByDueDateAndCategoryDto;
import br.com.meli.desafio_final.dto.AdsenseBySectionAndDueDateDto;
import br.com.meli.desafio_final.dto.BatchDto;
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
     * @return
     */
    @GetMapping("/{adsenseId}")
    public ResponseEntity<List<BatchDto>> findAllByAdsenseId(@PathVariable Long adsenseId) {
        return ResponseEntity.ok(service.findAllByAdsenseId(adsenseId));
    }

    @GetMapping("/due-date")
    public ResponseEntity<List<AdsenseBySectionAndDueDateDto>> findAdsenseBySectionAndDueDate(@RequestParam Long sectionId, @RequestParam int numberOfDays) {

        return ResponseEntity.status(HttpStatus.OK).body(service.findAdsenseBySectionAndDueDate(sectionId, numberOfDays));
    }

    @GetMapping("/due-date/list")
    public ResponseEntity<List<AdsensByDueDateAndCategoryDto>> findAdsenseByDueDateAndCategory(@RequestParam int numberOfDays, @RequestParam String category, @RequestParam String order) {

        return ResponseEntity.status(HttpStatus.OK).body(service.findAdsenseByDueDateAndCategory(numberOfDays, category, order));
    }
}
