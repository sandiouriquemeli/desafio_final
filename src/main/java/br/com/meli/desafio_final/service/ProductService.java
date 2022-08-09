package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Product;
import br.com.meli.desafio_final.model.enums.Category;
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
        List<Product> products = repository.findAll();

        if (products.size() == 0) throw new ExNotFound("Lista de produtos não encontrada");

        return products;
    }

    public List<Product> getByCategory(Category category) {
        List<Product> response = repository.findByCategory(category);
        if (response.size() == 0) {
            throw new CategoryNotFoundException("Nenhum produto com essa categoria foi encontrado");
        }
        return response;
    }
}
