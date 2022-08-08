package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.exception.NotFound;
import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.repository.AdsenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdsenseService implements IAdsenseService{
    @Autowired
    AdsenseRepository adsenseRepository;

    @Override
    public Adsense findById(long id) {
        return adsenseRepository.findById(id)
                .orElseThrow(() -> { throw new NotFound("Anúncio não cadastrado."); } );
    }
}