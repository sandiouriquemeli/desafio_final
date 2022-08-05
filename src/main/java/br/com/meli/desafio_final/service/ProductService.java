package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.exception.ExProductNotFound;
import br.com.meli.desafio_final.model.entity.Product;
import br.com.meli.desafio_final.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public List<Product> getAllProducts() {
        List<Product> products = (List<Product>) repository.findAll();
        if (products.size() == 0) throw new ExProductNotFound("Lista não encotrada");
        return products;
    }
}
