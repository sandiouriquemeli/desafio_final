package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Product;
import br.com.meli.desafio_final.model.entity.Section;
import br.com.meli.desafio_final.model.entity.Seller;
import br.com.meli.desafio_final.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * Metodo Valida a existência de uma Section no DB.
     * @param section
     */
    void validateSection(Section section) {
        sectionRepository.findById(section.getId())
                .orElseThrow(() -> {
                    throw new RuntimeException("Section não encontrada");
                });
    }

    /**
     * Metodo Valida a existência de um Seller no DB.
     * @param seller
     */
    void validateSeller(Seller seller) {
        sellerRepository.findById(seller.getId())
                .orElseThrow(() -> {
                    throw new RuntimeException("Seller não encontrada");
                });
    }

    /**
     * Metodo Valida a existência de um Product no DB.
     * @param product
     */
    void validateProduct(Product product) {
        productRepository.findById(product.getId())
                .orElseThrow(() -> {
                    throw new RuntimeException("Product não encontrada");
                });
    }
    //TODO: Fazer exceptions para Seller, Section e Product notFound
    // TODO: lembrar de criar um service pra cada ou um service validations
}
