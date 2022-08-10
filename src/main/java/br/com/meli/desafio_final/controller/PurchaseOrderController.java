package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.dto.AdsenseDto;
import br.com.meli.desafio_final.model.entity.PurchaseOrder;
import br.com.meli.desafio_final.service.implementation.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/fresh-products")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @PostMapping("/orders")
    public ResponseEntity<Double> save(@RequestBody PurchaseOrder purchaseOrder) {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseOrderService.save(purchaseOrder));
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<List<AdsenseDto>> findAdsensesByPurchaseOrderId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(purchaseOrderService.findAdsensesByPurchaseOrderId(id));
    }

    @PutMapping("/orders/")
    public ResponseEntity<PurchaseOrder> update(@RequestParam Long queryParam) {
        return ResponseEntity.status(HttpStatus.OK).body(purchaseOrderService.updateToFinished(queryParam));
    }
}
