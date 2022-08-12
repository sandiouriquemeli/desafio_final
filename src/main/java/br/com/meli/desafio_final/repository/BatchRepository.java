package br.com.meli.desafio_final.repository;

import br.com.meli.desafio_final.model.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {

    List<Batch> findAllByAdsenseId(Long adsenseId);

    List<Batch> findBatchesByAdsenseId(Long id);

    Optional<Batch> findBatchByBatchNumberAndInBoundOrderId(Long batchNumber, Long inboundOrder);

    Optional<Batch> findBatchByBatchNumberAndAdsenseId(Long batchNumber, Long adsenseId);

    /**
     * Essa query recebe como parâmetro do adsense_id e retorna uma lista com a quantidade total de
     * produtos (adsense) por armazém.
     * @param id (adsense_id)
     */
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

    /**
     * Essa query retorna uma lista de todos os lotes armazenados em um setor de um armazém,
     * filtrados por um período de vencimento
     * e ordenados por sua data de validade
     *
     * @param id (section_id)
     * @param initialDate
     * @param finalDate
     */
    @Query(value = "SELECT\n" +
            " batch_number, adsense_id, current_quantity, due_date\n" +
            " FROM batch\n" +
            " JOIN frescos.in_bound_order\n" +
            " WHERE frescos.in_bound_order.id = frescos.batch.in_bound_order_id\n" +
            " AND frescos.in_bound_order.section_id = ?1\n" +
            " AND due_date BETWEEN ?2 AND ?3" +
            " ORDER BY due_date", nativeQuery = true)
    List<Object[]> getAdsenseBySectionAndDate(long id, LocalDate initialDate, LocalDate finalDate);


    /**
     * Essa query retorna uma lista de lote dentro do prazo de validade solicitado,
     * que pertencem a uma determinada categoria de produto
     * ordenada de forma crescente pela quantidade
     *
     * @param initialDate
     * @param finalDate
     * @param category
     */
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

    /**
     * Essa query retorna uma lista de lote dentro do prazo de validade solicitado,
     * que pertencem a uma determinada categoria de produto
     * ordenada de forma decrescente pela quantidade
     *
     * @param initialDate
     * @param finalDate
     * @param category
     */
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