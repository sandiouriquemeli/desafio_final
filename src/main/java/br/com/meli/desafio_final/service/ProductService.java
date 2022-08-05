package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.exception.CategoryNotFoundException;
import br.com.meli.desafio_final.model.entity.Product;
import br.com.meli.desafio_final.model.enums.Category;
import br.com.meli.desafio_final.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getByCategory(Category category) {
        List<Product> response = repository.findByCategory(category);
        if (response.size() == 0) {
            throw new CategoryNotFoundException("Nenhum produto com essa categoria foi encontrado");
        }
        return response;
    }
}
