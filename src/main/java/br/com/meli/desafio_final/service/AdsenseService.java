package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.repository.AdsenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdsenseService implements IAdsenseService {

    @Autowired
    private AdsenseRepository adsenseRepository;

    @Override
    public Adsense findById(long id) {
        return adsenseRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("deu ruim");
                });
        // TODO: Tratar Exception
    }

    @Override
    public List<Adsense> findAll() {
        return adsenseRepository.findAll();
    }
}
