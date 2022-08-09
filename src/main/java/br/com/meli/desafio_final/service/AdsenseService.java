package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.model.enums.Category;
import br.com.meli.desafio_final.repository.AdsenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdsenseService implements IAdsenseService {

    @Autowired
    private AdsenseRepository adsenseRepository;

    //Implementando findById para verificar existencia produtos do carrinho!
    //Lançar exception a nível de product, POST requisito 2!
    @Override
    public Adsense findById(long id) {
        return adsenseRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("deu ruim");
                });
    }

    //Implementando findAll para verificar existencia produtos do carrinho!
    @Override
    public List<Adsense> findAll() {
        List<Adsense> adsenses = adsenseRepository.findAll();
        if (adsenses.size() == 0) throw new RuntimeException("💢 Lista de anúncios não encontrada");
        return adsenses;
    }

    @Override
    public List<Adsense> findByCategory(Category category) {
        return findAll().stream().filter(a -> a.getProduct().getCategory().equals(category))
                .collect(Collectors.toList());
    }

    // TODO: Tratar Exception
}
