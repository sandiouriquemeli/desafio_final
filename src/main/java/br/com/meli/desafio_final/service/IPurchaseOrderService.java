package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.PurchaseOrder;

public interface IPurchaseOrderService {

    Double save(PurchaseOrder purchaseOrder);
    PurchaseOrder findById(Long id);
    PurchaseOrder updateToFinished(Long id);

}

