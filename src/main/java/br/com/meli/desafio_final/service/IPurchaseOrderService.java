package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.PurchaseOrder;
import br.com.meli.desafio_final.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface IPurchaseOrderService {

    //Save Carrinho
    PurchaseOrder save(PurchaseOrder purchaseOrder);

}

