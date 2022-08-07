package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.model.entity.PurchaseOrder;
import br.com.meli.desafio_final.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/fresh-products") //adicionando endpoint v2 -> requisito 2
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    //Método necessário POST requisito 2
    @PostMapping("/orders")
    public ResponseEntity<Double> save(@RequestBody PurchaseOrder purchaseOrder) {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseOrderService.save(purchaseOrder));
    }

}
