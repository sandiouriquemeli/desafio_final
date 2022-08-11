package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.dto.AdsenseDto;
import br.com.meli.desafio_final.dto.PurchaseOrderDto;
import br.com.meli.desafio_final.model.entity.PurchaseOrder;

import java.util.List;

public interface IPurchaseOrderService {

    Double save(PurchaseOrder purchaseOrder);
    PurchaseOrder findById(Long id);
    PurchaseOrder updateToFinished(Long purchaseOrderId);
    List<AdsenseDto> findAdsensesByPurchaseOrderId(Long id);

}

