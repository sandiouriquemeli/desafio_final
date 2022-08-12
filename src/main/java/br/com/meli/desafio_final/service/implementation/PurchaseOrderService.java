package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.dto.AdsenseDto;
import br.com.meli.desafio_final.dto.BatchDto;
import br.com.meli.desafio_final.exception.BadRequest;
import br.com.meli.desafio_final.exception.NotFound;
import br.com.meli.desafio_final.model.entity.*;
import br.com.meli.desafio_final.model.enums.Status;
import br.com.meli.desafio_final.repository.PurchaseOrderRepository;
import br.com.meli.desafio_final.service.implementation.BatchService;
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

    /**
     * Nesse método estamos salvando um pedido
     * @param purchaseOrder
     * @return
     */
    @Override
    public Double save(PurchaseOrder purchaseOrder) {
        Buyer buyer = buyerService.findById(purchaseOrder.getBuyer().getId());
        purchaseOrder.setBuyer(buyer);
        if (validationAdsense(purchaseOrder)) {
            purchaseOrderRepository.save(purchaseOrder);
            saveItemByPurchase(purchaseOrder);
            return totalPrice(purchaseOrder.getItemList());
        } else {
            throw new BadRequest("Pedido não cadastrado!");
        }
    }

    /**
     *
     * @param purchaseOrder
     * @return
     */
    private boolean validationAdsense(PurchaseOrder purchaseOrder) {
        List<Item> itemList = purchaseOrder.getItemList();
        for (Item item : itemList) {
            Adsense adsense = adsenseService.findById(item.getAdsense().getId());
            List<BatchDto> batchList = batchService.findAllByAdsenseId(adsense.getId());
            for (BatchDto batchDto : batchList) {
                if ((LocalDate.now().plusWeeks(3)).isBefore(batchDto.getDueDate())) {
                    if ((batchDto.getCurrentQuantity() - item.getQuantity()) > 0) {
                        batchDto.setCurrentQuantity(batchDto.getCurrentQuantity() - item.getQuantity());
                    } else {
                        throw new BadRequest("Estoque insuficiente!");
                    }
                } else {
                    throw new BadRequest("Data de validade inferior a 3 semanas!");
                }
            }
        }
        return true;
    }

    /**
     * Nesse método estamos salvando intem
     * @param purchaseOrder
     */
    private void saveItemByPurchase(PurchaseOrder purchaseOrder) {
        purchaseOrder.getItemList().forEach(item -> {
            item.setPurchaseOrder(purchaseOrder);
            itemService.save(item);
        });
    }

    /**
     * Nesse método estamos retornando preço ...
     * @param itemList
     * @return
     */
    private Double totalPrice(List<Item> itemList) {
        return itemList.stream()
                .map(item -> item.getCurrentPrice() * item.getQuantity())
                .reduce(Double::sum).orElse(0.0);
    }

    /**
     * Nesse ,metódo retornamos pedido por Id
     * @param id
     * @return
     */
    @Override
    public PurchaseOrder findById(Long id) {
        return purchaseOrderRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFound("Pedido inexistente");
                });
    }

    /**
     * Nesse método estamos atualizando o Lista de compra ...
     * @param purchaseOrderId
     * @return
     */
    @Override
    public PurchaseOrder updateToFinished(Long purchaseOrderId) {
        PurchaseOrder purchaseOrder = findById(purchaseOrderId);
        purchaseOrder.setStatus(Status.FINISHED);
        purchaseOrderRepository.save(purchaseOrder);
        return purchaseOrder;
    }

    /**
     * Nesse método estamos retornando uma lista de anúncio ...
     * @param purchaseOrderId
     * @return
     */
    @Override
    public List<AdsenseDto> findAdsensesByPurchaseOrderId(Long purchaseOrderId) {
        List<Adsense> adsenseList = new ArrayList<>();
        itemService.findItemsByPurchaseOrderId(purchaseOrderId).forEach(item -> {
            adsenseList.add(item.getAdsense());
        });
        return AdsenseDto.convertDto(adsenseList);
    }
}
