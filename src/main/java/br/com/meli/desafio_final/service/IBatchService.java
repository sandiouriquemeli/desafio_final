package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.dto.BatchDto;
import br.com.meli.desafio_final.model.entity.Batch;

import java.util.List;

public interface IBatchService {
    Batch saveBatch(Batch batch);
    Batch findById(Long id);
    List<BatchDto> findAllByAdsenseId(Long adsenseId);
}
