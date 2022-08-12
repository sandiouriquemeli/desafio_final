package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.exception.BadRequest;
import br.com.meli.desafio_final.model.entity.Agent;
import br.com.meli.desafio_final.model.entity.Product;
import br.com.meli.desafio_final.model.entity.Section;
import br.com.meli.desafio_final.model.entity.Seller;
import br.com.meli.desafio_final.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ValidationService {

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BatchRepository batchRepository;

    @Autowired
    AgentRepository agentRepository;

    /**
     * Metodo Valida a existência de uma Section no DB.
     * @param section
     */
    public Section validateSection(Section section) {
       return sectionRepository.findById(section.getId())
                .orElseThrow(() -> {
                    throw new BadRequest("Section não encontrada");
                });
    }

    /**
     * Metodo Valida a existência de um Seller no DB.
     * @param seller
     */
    public void validateSeller(Seller seller) {
        sellerRepository.findById(seller.getId())
                .orElseThrow(() -> {
                    throw new BadRequest("Seller não encontrada");
                });
    }

    /**
     * Metodo Valida a existência de um Product no DB.
     * @param product
     */
    public void validateProduct(Product product) {
        productRepository.findById(product.getId())
                .orElseThrow(() -> {
                    throw new BadRequest("Product não encontrada");
                });
    }

    /**
     * Nesse método estamos validando um representante
     * @param id
     * @return
     */
    public Agent validateAgent(long id) {
        return agentRepository.findById(id)
                .orElseThrow(() -> {
                    throw new BadRequest("Representante não encontrado");
                });
    }
}
