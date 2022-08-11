package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.dto.AdsenseDto;
import br.com.meli.desafio_final.dto.PurchaseOrderDto;
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

    /**
     * Nesse método salvamos a ordem de pedido e retornamos o status de criado (CREATED)
     * @param purchaseOrder
     * @return
     */
    @PostMapping("/orders")
    public ResponseEntity<Double> save(@RequestBody PurchaseOrder purchaseOrder) {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseOrderService.save(purchaseOrder));
    }

    /**
     * Nesse método retornamos produto anunciando
     * @param purchaseOrderId
     * @return
     */

    @GetMapping("/orders/{purchaseOrderId}")
    public ResponseEntity<List<AdsenseDto>> findAdsensesByPurchaseOrderId(@PathVariable Long purchaseOrderId) {
        return ResponseEntity.status(HttpStatus.OK).body(purchaseOrderService.findAdsensesByPurchaseOrderId(purchaseOrderId));
    }

    /**
     * Nesse método atualizamos o status de compra
     * @param purchaseOrderId
     * @return
     */
    //TODO: fazer DTO
    @PutMapping("/orders/")
    public ResponseEntity<PurchaseOrderDto> update(@RequestParam Long purchaseOrderId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PurchaseOrderDto(purchaseOrderService.updateToFinished(purchaseOrderId)));
    }
}
