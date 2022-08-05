package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.PurchaseOrder;
import br.com.meli.desafio_final.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderService implements IPurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    //Save Carrinho
    @Override
    public PurchaseOrder save(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }
}
