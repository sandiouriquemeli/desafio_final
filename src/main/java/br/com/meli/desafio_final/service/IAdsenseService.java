package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Adsense;

import java.util.List;

public interface IAdsenseService {

    Adsense findById(long id);

    List<Adsense> findAll();

}
