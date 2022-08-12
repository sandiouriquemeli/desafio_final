package br.com.meli.desafio_final.service.implementation;
import br.com.meli.desafio_final.dto.*;
import br.com.meli.desafio_final.exception.NotFound;
import br.com.meli.desafio_final.exception.BadRequest;
import br.com.meli.desafio_final.model.entity.Batch;
import br.com.meli.desafio_final.repository.BatchRepository;
import br.com.meli.desafio_final.service.IBatchService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BatchService implements IBatchService {

    @Autowired
    private BatchRepository batchRepository;

    /**
     * Nesse método salvamos um lote e retornamos
     * @param batch
     * @return
     */
    @Override
    public Batch saveBatch(Batch batch) {
       return batchRepository.save(batch);
    }
    /**
     * Nesse método estamos consultando uma lista de anúncio por lote e retornando um status
     * @param id
     * @return
     */
    @Override
    public List<Batch> findBatchByAdsenseId(Long id) {
        List<Batch> batchList = batchRepository.findBatchesByAdsenseId(id);
        if(batchList.size() == 0) throw new NotFound("Lote do anúncio não encontrado!");
        return batchList;
    }

    /**
     * Nesse método estamos retornado um Lote (Batch DTO)
     * @param adsenseId
     * @return
     */
    @Override
    public List<BatchDto> findAllByAdsenseId(Long adsenseId) {
        List<Batch> batchList = batchRepository.findAllByAdsenseId(adsenseId);
        List<Batch> newListBacthValid = validateBatch(batchList);
        return BatchDto.convertDto(newListBacthValid);
    }

    /**
     * Nesse método estamos consultando uma lista e retornando produto em estoque
     * @param adsenseList
     * @param s
     * @return
     */
    @Override
    public List<BatchDto> returnBatchStock(List<AdsenseIdDto> adsenseList, String s) {
        List<BatchDto> result = new ArrayList<>();
        List<List<BatchDto>> batchDtoList = adsenseList.stream().map(adsenseId -> findAllByAdsenseId(adsenseId.getId()))
                .collect(Collectors.toList());
        batchDtoList.forEach(result::addAll);
        if (s != null) sortBatchList(result, s);
        return result;
    }

    /**
     * Nesse método estamos comparando ...
     * @param batchDtos
     * @param s
     */
    private void sortBatchList(List<BatchDto> batchDtos, String s) {
        if (s.equals("L")) {
            batchDtos.sort(Comparator.comparing(BatchDto::getBatchNumber));
        }
        if (s.equals("Q")) {
            batchDtos.sort(Comparator.comparing(BatchDto::getCurrentQuantity));
        }
        if (s.equals("V")) {
            batchDtos.sort(Comparator.comparing(BatchDto::getDueDate));
        }
    }

    /**
     * Esse metodo recebe uma lista de lotes e retorna uma lista de lotes filtrada
     * por lotes que tem em estoque e não estão pra vencer nas próximas 3 semanas.
     * @param batchList
     * @return
     */
    private List<Batch> validateBatch(List<Batch> batchList) {
        List<Batch> newListBatch = new ArrayList<>();
        for (Batch batch : batchList) {
            if (batch.getCurrentQuantity() > 0){
                if ((LocalDate.now().plusWeeks(3)).isBefore(batch.getDueDate())) {
                    newListBatch.add(batch);
                }
            }
        }
//        if (newListBatch.isEmpty()) throw new NotFound("Data de validade ou estoque");
        return newListBatch;
    }

    /**
     * Nesse método estamos retornando lote (Batch) através do Id
     * @param batchNumber
     * @param adsenseId
     */
    public void findBatchByBatchNumberAndAdsenseId(Long batchNumber, Long adsenseId) {
        Optional<Batch> batch = batchRepository.findBatchByBatchNumberAndAdsenseId(batchNumber, adsenseId);
        if(batch.isPresent())
            throw new NotFound("Produto deste usuário já está cadastrado.");
    }

    /**
     * Esse metodo busca um lote pelo seu número e pelo seu inboundOrderId
     * @param batchNumber
     * @param inboundOrderId
     * @return
     */
    @Override
    public Batch findByBatchNumberAndInboundOrderId(Long batchNumber, Long inboundOrderId){
        return batchRepository.findBatchByBatchNumberAndInBoundOrderId(batchNumber, inboundOrderId)
                .orElseThrow(() -> {throw new NotFound("Lote não encontrado");});
    }

    /**
     * Nesse método estamos retornando um anúncio, seu armazém e quantidade
     * @param adsenseId
     * @return
     */
    @Override
    public List<AdsenseByWarehouseDto> getAdsenseByWarehouseAndQuantity(long adsenseId) {
        return batchRepository.getAdsenseByWarehouse(adsenseId).stream().map(
                (obj) -> new AdsenseByWarehouseDto(obj[0], obj[1])).collect(Collectors.toList());
    }

    /**
     * Esse método retorna uma lista de todos os lotes armazenados em um setor de um armazém,
     * filtrados por um período de vencimento
     * e ordenados por sua data de validade
     * @param sectionId
     * @param numberOfDays
     */
    @Override
    public List<AdsenseBySectionAndDueDateDto> findAdsenseBySectionAndDueDate(long sectionId, int numberOfDays) {
        LocalDate initialDate = LocalDate.now();
        LocalDate finalDate = initialDate.plusDays(numberOfDays);

        return batchRepository.getAdsenseBySectionAndDate(sectionId, initialDate, finalDate).stream().map(
                (obj) -> new AdsenseBySectionAndDueDateDto(obj[0], obj[1], obj[2], obj[3])
        ).collect(Collectors.toList());
    }

    /**
     * Esse método retorna uma lista de lote dentro do prazo de validade solicitado,
     * que pertencem a uma determinada categoria de produto
     * ordenada de forma crescente ou decrescente pela quantidade
     * @param numberOfDays
     * @param category
     * @param order
     */
    @Override
    public List<AdsensByDueDateAndCategoryDto> findAdsenseByDueDateAndCategory(int numberOfDays, String category, String order) {
        LocalDate initialDate = LocalDate.now();
        LocalDate finalDate = initialDate.plusDays(numberOfDays);

        return order.equalsIgnoreCase("asc")
            ? batchRepository.getAdsenseByDueDateAndCategoryAsc(initialDate, finalDate, category).stream()
                .map((obj) -> new AdsensByDueDateAndCategoryDto(obj[0], obj[1], obj[2], obj[3], obj[4], obj[5]))
                .collect(Collectors.toList())
            : batchRepository.getAdsenseByDueDateAndCategoryDesc(initialDate, finalDate, category).stream()
                .map((obj) -> new AdsensByDueDateAndCategoryDto(obj[0], obj[1], obj[2], obj[3], obj[4], obj[5]))
                .collect(Collectors.toList());
    }

}
