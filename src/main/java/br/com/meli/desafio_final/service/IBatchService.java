package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Batch;

import java.util.List;

public interface IBatchService {
    Batch saveBatch(Batch batch);
    List<Batch> findBatchByAdsenseId(Long id);

    Batch findById(long id);

}
