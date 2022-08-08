package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Product;
import br.com.meli.desafio_final.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService{
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product findProductById(long id) {
        return productRepository.findById(id).orElseThrow(() -> {throw new RuntimeException("Produto não existe em nosso catágolo");});
    }
    //TODO: fazer exception
}
