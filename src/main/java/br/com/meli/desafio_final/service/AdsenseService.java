package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.repository.AdsenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdsenseService implements IAdsenseService {

    @Autowired
    private AdsenseRepository adsenseRepository;


    //Implementando findById para verificar existencia produtos do carrinho!
    //Lançar exception a nível de product, POST requisito 2!
    @Override
    public Adsense findById(Long id) {
        Optional<Adsense> adsenseFound = adsenseRepository.findById(id);
        if (adsenseFound.isPresent()) {
            return adsenseFound.get();
        } else {
            throw new RuntimeException("Adsense não encontrado!");
        }
    }

    //Implementando findAll para verificar existencia produtos do carrinho!
    @Override
    public List<Adsense> findAll() {
        return (List<Adsense>) adsenseRepository.findAll();
    }
}
