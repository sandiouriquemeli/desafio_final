package br.com.meli.desafio_final.repository;

import br.com.meli.desafio_final.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findItemsByPurchaseOrderId(Long purchaseOrderId);

}