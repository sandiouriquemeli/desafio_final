package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.model.entity.PurchaseOrder;
import br.com.meli.desafio_final.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/fresh-products")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    //Post Carrinho
    @PostMapping("/orders")
    public PurchaseOrder save(@RequestBody PurchaseOrder purchaseOrder){
        return purchaseOrderService.save(purchaseOrder);
    }
}
