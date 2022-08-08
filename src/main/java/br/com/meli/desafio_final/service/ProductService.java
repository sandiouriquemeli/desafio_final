package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.exception.ExNotFound;
import br.com.meli.desafio_final.model.entity.Product;
import br.com.meli.desafio_final.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = (List<Product>) repository.findAll();

        if (products.size() == 0) throw new ExNotFound("Lista de produtos não encontrada");

        return products;
    }

}
