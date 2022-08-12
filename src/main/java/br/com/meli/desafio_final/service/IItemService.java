package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Item;

import java.util.List;

public interface IItemService {

    void save(Item item);

    List<Item> findItemsByPurchaseOrderId(Long id);

}
