package br.com.meli.desafio_final.repository;

import br.com.meli.desafio_final.dto.AdsenseByWarehouseDto;
import br.com.meli.desafio_final.model.entity.Batch;
import br.com.meli.desafio_final.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

    @Query(value = "SELECT\n" +
            " batch_number, adsense_id, current_quantity, due_date\n" +
            " FROM batch\n" +
            " JOIN frescos.in_bound_order\n" +
            " WHERE frescos.in_bound_order.id = frescos.batch.in_bound_order_id\n" +
            " AND frescos.in_bound_order.section_id = ?1\n" +
            " AND due_date BETWEEN ?2 AND ?3", nativeQuery = true)
    List<Object[]> getAdsenseBySectionAndDate(long id, LocalDate initialDate, LocalDate finalDate);


    @Query(value = "SELECT\n" +
            " batch_number, \n" +
            " adsense_id, \n" +
            " current_quantity, \n" +
            " due_date,\n" +
            " frescos.product.category AS productType,\n" +
            " frescos.in_bound_order.section_id AS section_id\n" +
            " FROM batch\n" +
            " JOIN frescos.in_bound_order\n" +
            " JOIN frescos.adsense as adsense\n" +
            " JOIN frescos.product as product\n" +
            " WHERE frescos.in_bound_order.id = frescos.batch.in_bound_order_id\n" +
            " AND due_date BETWEEN ?1 AND ?2\n" +
            " AND batch.adsense_id = adsense.id\n" +
            " AND product.id = adsense.product_id\n" +
            " AND product.category = ?3\n" +
            " ORDER BY current_quantity ASC", nativeQuery = true)
    List<Object[]> getAdsenseByDueDateAndCategoryAsc(LocalDate initialDate, LocalDate finalDate, String category);


    @Query(value = "SELECT\n" +
            " batch_number, \n" +
            " adsense_id, \n" +
            " current_quantity, \n" +
            " due_date,\n" +
            " frescos.product.category AS productType,\n" +
            " frescos.in_bound_order.section_id AS section_id\n" +
            " FROM batch\n" +
            " JOIN frescos.in_bound_order\n" +
            " JOIN frescos.adsense as adsense\n" +
            " JOIN frescos.product as product\n" +
            " WHERE frescos.in_bound_order.id = frescos.batch.in_bound_order_id\n" +
            " AND due_date BETWEEN ?1 AND ?2\n" +
            " AND batch.adsense_id = adsense.id\n" +
            " AND product.id = adsense.product_id\n" +
            " AND product.category = ?3\n" +
            " ORDER BY current_quantity DESC", nativeQuery = true)
    List<Object[]> getAdsenseByDueDateAndCategoryDesc(LocalDate initialDate, LocalDate finalDate, String category);
}