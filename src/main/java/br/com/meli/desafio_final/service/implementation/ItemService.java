package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.exception.NotFound;
import br.com.meli.desafio_final.model.entity.Item;
import br.com.meli.desafio_final.repository.ItemRepository;
import br.com.meli.desafio_final.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements IItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }

    /**
     * Nesse método estamos retornado intem através da  ordem de compra
     * @param purchaseOrderId
     * @return
     */
    @Override
    public List<Item> findItemsByPurchaseOrderId(Long purchaseOrderId) {
        List<Item> itemList = itemRepository.findItemsByPurchaseOrderId(purchaseOrderId);
        if(itemList.isEmpty()) throw new NotFound("Item não encontrado!");
        return itemList;
    }

}
