package br.com.meli.desafio_final.repository;

import br.com.meli.desafio_final.model.entity.Warehouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {
}