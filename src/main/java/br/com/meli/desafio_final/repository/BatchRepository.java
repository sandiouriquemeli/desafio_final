package br.com.meli.desafio_final.repository;

import br.com.meli.desafio_final.dto.AdsenseByWarehouseDto;
import br.com.meli.desafio_final.model.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    List<Batch> findAllByAdsenseId(Long adsenseId);

    List<Batch> findBatchesByAdsenseId(Long id);

    @Query(value = "SELECT \n" +
            "    SUM(frescos.batch.current_quantity) AS quantity,\n" +
            "    frescos.section.warehouse_id AS warehouse_id\n" +
            "FROM frescos.batch AS batch \n" +
            "JOIN frescos.in_bound_order AS inbound\n" +
            "JOIN frescos.section AS section\n" +
            "WHERE frescos.batch.adsense_id = ?1\n" +
            "AND batch.in_bound_order_id = inbound.id \n" +
            "AND inbound.section_id = section.id\n" +
            "GROUP BY warehouse_id, adsense_id;", nativeQuery = true)
    List<Object[]> getAdsenseByWarehouse(long id);

}