package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.exception.NotFound;
import br.com.meli.desafio_final.model.entity.Batch;
import br.com.meli.desafio_final.repository.BatchRepository;
import br.com.meli.desafio_final.service.IBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchService implements IBatchService {

    @Autowired
    private BatchRepository batchRepository;

    @Override
    public Batch saveBatch(Batch batch) {
       return batchRepository.save(batch);
    }

    @Override
    public List<Batch> findBatchByAdsenseId(Long id) {
        List<Batch> batchList = batchRepository.findBatchesByAdsenseId(id);
        if(batchList.size() == 0) throw new NotFound("Lote do anúncio não encontrado!");
        return batchList;
    }

    public Batch findById(long id){
        return batchRepository.findById(id).orElseThrow(() -> {throw new NotFound("Lote não encontrado");});
    }
}
