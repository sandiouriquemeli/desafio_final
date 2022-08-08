package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Buyer;
import br.com.meli.desafio_final.model.entity.Item;
import br.com.meli.desafio_final.model.entity.PurchaseOrder;
import br.com.meli.desafio_final.repository.BuyerRepository;
import br.com.meli.desafio_final.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService implements IPurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private AdsenseService adsenseService;

    @Override
    public Double save(PurchaseOrder purchaseOrder) {
        Optional<Buyer> buyer = buyerRepository.findById(purchaseOrder.getBuyer().getId());
        if (buyer.isPresent()) {
            purchaseOrder.setBuyer(buyer.get());
            verifyExistAdsenses(purchaseOrder);
            purchaseOrderRepository.save(purchaseOrder);
            return totalPrice(purchaseOrder.getItemList());
        } else {
            throw new RuntimeException("Buyer inexistente, pedido não registrado!");
        }
    }

    private void verifyExistAdsenses(PurchaseOrder purchaseOrder) {
        purchaseOrder.getItemList().forEach(item -> adsenseService.findById(item.getAdsense().getId()));
    }

    private Double totalPrice(List<Item> itemList) {
        return itemList.stream()
                .map(item -> item.getCurrentPrice() * item.getQuantity())
                .reduce(Double::sum).orElse(0.0);
    }

}
