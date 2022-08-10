package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.dto.AdsenseIdDto;
import br.com.meli.desafio_final.dto.BatchDto;
import br.com.meli.desafio_final.exception.NotFound;
import br.com.meli.desafio_final.model.entity.Batch;
import br.com.meli.desafio_final.repository.BatchRepository;
import br.com.meli.desafio_final.service.IBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchService implements IBatchService {

    @Autowired
    BatchRepository batchRepository;

    @Override
    public Batch saveBatch(Batch batch) {
       return batchRepository.save(batch);
    }

    public Batch findById(Long id){
        return batchRepository.findById(id).orElseThrow(() -> {throw new NotFound("Lote não encontrado");});
    }

    @Override
    public List<BatchDto> findAllByAdsenseId(Long adsenseId) {
        return BatchDto.convertDto(batchRepository.findAllByAdsenseId(adsenseId));
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
}
