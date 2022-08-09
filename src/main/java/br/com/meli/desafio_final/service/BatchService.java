package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.dto.BatchDto;
import br.com.meli.desafio_final.exception.NotFound;
import br.com.meli.desafio_final.model.entity.Batch;
import br.com.meli.desafio_final.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchService implements IBatchService{

    @Autowired
    BatchRepository batchRepository;

    @Override
    public Batch saveBatch(Batch batch) {
       return batchRepository.save(batch);
    }

    @Override
    public Batch findById(Long id){
        return batchRepository.findById(id).orElseThrow(() -> {throw new NotFound("Lote não encontrado");});
    }

    @Override
    public List<BatchDto> findAllByAdsenseId(Long adsenseId) {
        return BatchDto.convertDto(batchRepository.findAllByAdsenseId(adsenseId));
    }
}
