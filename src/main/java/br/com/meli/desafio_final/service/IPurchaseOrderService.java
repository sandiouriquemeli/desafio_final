package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.model.entity.PurchaseOrder;

import java.util.List;

public interface IPurchaseOrderService {

    PurchaseOrder findById(Long id);

    Double save(PurchaseOrder purchaseOrder);

    List<Adsense> findAdsensesByPurchaseOrderId(Long id);

}

