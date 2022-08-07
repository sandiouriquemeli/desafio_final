package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Batch;
import br.com.meli.desafio_final.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchService implements IBatchService{

    @Autowired
    BatchRepository batchRepository;

    @Override
    public Batch saveBatch(Batch batch) {
       return batchRepository.save(batch);
    }
}
