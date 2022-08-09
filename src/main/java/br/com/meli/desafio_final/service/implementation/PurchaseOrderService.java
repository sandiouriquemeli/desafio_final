package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.dto.AdsenseDto;
import br.com.meli.desafio_final.exception.BadRequest;
import br.com.meli.desafio_final.exception.NotFound;
import br.com.meli.desafio_final.model.entity.*;
import br.com.meli.desafio_final.model.enums.Status;
import br.com.meli.desafio_final.repository.PurchaseOrderRepository;
import br.com.meli.desafio_final.service.IPurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseOrderService implements IPurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private AdsenseService adsenseService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private BatchService batchService;


    @Override
    public Double save(PurchaseOrder purchaseOrder) {
        Buyer buyer = buyerService.findById(purchaseOrder.getBuyer().getId());
        purchaseOrder.setBuyer(buyer);
        if (validationAdsense(purchaseOrder)) {
            purchaseOrderRepository.save(purchaseOrder);
            saveItemByPurchase(purchaseOrder);
            return totalPrice(purchaseOrder.getItemList());
        }else {
            throw new BadRequest("Pedido não cadastrado!");
        }
    }

    private boolean validationAdsense(PurchaseOrder purchaseOrder) {
        verifyExistAdsenses(purchaseOrder);
        verifyDateBatch(purchaseOrder);
        return true;
    }

    private void verifyDateBatch(PurchaseOrder purchaseOrder) {
        List<Item> itemList = purchaseOrder.getItemList();
        for (Item item : itemList) {
            List<Batch> batchList = batchService.findBatchByAdsenseId(item.getAdsense().getId());
            for (Batch batch : batchList) {
                if ((LocalDate.now().plusWeeks(3)).isBefore(batch.getDueDate())) {
                    batch.setCurrentQuantity(batch.getCurrentQuantity() - item.getQuantity());
                }else {
                    throw new BadRequest("Data de validade inferior a 3 semanas!");
                }
            }

        }
    }

    private void verifyExistAdsenses(PurchaseOrder purchaseOrder) {
        purchaseOrder.getItemList().forEach(item -> adsenseService.findById(item.getAdsense().getId()));
    }

    private void saveItemByPurchase(PurchaseOrder purchaseOrder) {
        purchaseOrder.getItemList().forEach(item -> {
            item.setPurchaseOrder(purchaseOrder);
            itemService.save(item);
        });
    }

    private Double totalPrice(List<Item> itemList) {
        return itemList.stream()
                .map(item -> item.getCurrentPrice() * item.getQuantity())
                .reduce(Double::sum).orElse(0.0);
    }

    @Override
    public PurchaseOrder findById(Long id) {
        return purchaseOrderRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFound("Pedido inexistente");
                });
    }

    @Override
    public PurchaseOrder updateToFinished(Long id) {
        PurchaseOrder purchaseOrder = findById(id);
        purchaseOrder.setStatus(Status.FINISHED);
        purchaseOrderRepository.save(purchaseOrder);
        return purchaseOrder;
    }

    @Override
    public List<AdsenseDto> findAdsensesByPurchaseOrderId(Long id) {
        List<Adsense> adsenseList = new ArrayList<>();
        itemService.findItemsByPurchaseOrderId(id).forEach(item -> {
            adsenseList.add(item.getAdsense());
        });
        return AdsenseDto.convertDto(adsenseList);
    }

}
