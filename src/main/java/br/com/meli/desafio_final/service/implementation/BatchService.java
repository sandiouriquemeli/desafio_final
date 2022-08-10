package br.com.meli.desafio_final.service.implementation;
import br.com.meli.desafio_final.dto.AdsenseIdDto;
import br.com.meli.desafio_final.dto.BatchDto;
import br.com.meli.desafio_final.dto.AdsenseByWarehouseDto;
import br.com.meli.desafio_final.exception.BadRequest;
import br.com.meli.desafio_final.exception.NotFound;
import br.com.meli.desafio_final.model.entity.Batch;
import br.com.meli.desafio_final.repository.BatchRepository;
import br.com.meli.desafio_final.service.IBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchService implements IBatchService {

    @Autowired
    private BatchRepository batchRepository;

    @Override
    public Batch saveBatch(Batch batch) {
       return batchRepository.save(batch);
    }

    @Override
    public List<BatchDto> findAllByAdsenseId(Long adsenseId) {
        List<Batch> batchList = batchRepository.findAllByAdsenseId(adsenseId);
        List<Batch> newListBacthValid = validateBatch(batchList);
        if (newListBacthValid.isEmpty()) {
            throw new NotFound("Lote do anúncio não encontrado!");
        }
        return BatchDto.convertDto(newListBacthValid);
    }

    @Override
    public List<BatchDto> returnBatchStock(List<AdsenseIdDto> adsenseList, String s) {
        List<BatchDto> result = new ArrayList<>();
        List<List<BatchDto>> batchDtoList = adsenseList.stream().map(adsenseId -> findAllByAdsenseId(adsenseId.getId()))
                .collect(Collectors.toList());
        batchDtoList.forEach(result::addAll);
        if (s != null) sortBatchList(result, s);
        return result;
    }

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

    private List<Batch> validateBatch(List<Batch> batchList) {
        List<Batch> newListBatch = new ArrayList<>();
        for (Batch batch : batchList) {
            if (batch.getCurrentQuantity() > 0){
                if ((LocalDate.now().plusWeeks(3)).isBefore(batch.getDueDate())) {
                    newListBatch.add(batch);
                }
            }
        }
        return newListBatch;
    }

    @Override
    public Batch findById(Long id){
        return batchRepository.findById(id).orElseThrow(() -> {throw new NotFound("Lote não encontrado");});
    }

    @Override
    public List<AdsenseByWarehouseDto> getAdsenseByWarehouseAndQuantity(long adsenseId) {
        return batchRepository.getAdsenseByWarehouse(adsenseId).stream().map(
                (obj) -> new AdsenseByWarehouseDto(obj[0], obj[1])).collect(Collectors.toList());
    }


}
