package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Adsense;

import java.util.List;

public interface IAdsenseService {

    //Necessário POST requisito 2!
    Adsense findById(Long id);

    List<Adsense> findAll();

}
