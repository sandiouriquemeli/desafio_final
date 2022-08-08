package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Item;
import br.com.meli.desafio_final.repository.ItemRepository;
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

    @Override
    public List<Item> findItemsByPurchaseOrderId(Long id) {
        return itemRepository.findItemsByPurchaseOrderId(id);
    }

}
