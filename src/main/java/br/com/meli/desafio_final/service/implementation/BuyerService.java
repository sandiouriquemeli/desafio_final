package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.exception.NotFound;
import br.com.meli.desafio_final.model.entity.Buyer;
import br.com.meli.desafio_final.repository.BuyerRepository;
import br.com.meli.desafio_final.service.IBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService implements IBuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    /**
     * Nesse método retornamos comprador por Id
     * @param id
     * @return
     */
    @Override
    public Buyer findById(Long id) {
        return buyerRepository.findById(id)
                .orElseThrow(() -> { throw new NotFound("Comprador não cadastrado."); } );
    }

}
