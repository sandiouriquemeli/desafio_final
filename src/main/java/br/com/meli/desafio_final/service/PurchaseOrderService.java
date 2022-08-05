package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Buyer;
import br.com.meli.desafio_final.model.entity.PurchaseOrder;
import br.com.meli.desafio_final.repository.BuyerRepository;
import br.com.meli.desafio_final.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseOrderService implements IPurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    //Save Carrinho
    @Override
    public PurchaseOrder save(PurchaseOrder purchaseOrder) {
        Optional<Buyer> buyer = buyerRepository.findById(purchaseOrder.getBuyer().getId());
        if (buyer.isPresent()) {
            purchaseOrder.setBuyer(buyer.get());
        } else {
            throw new RuntimeException();
        }
        return purchaseOrderRepository.save(purchaseOrder);
    }
}
