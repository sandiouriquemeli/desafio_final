package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Batch;

public interface IBatchService {
    Batch saveBatch(Batch batch);

    Batch findById(long id);

}
