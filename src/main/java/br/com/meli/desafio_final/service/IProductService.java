package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Product;
import br.com.meli.desafio_final.model.enums.Category;

import java.util.List;

public interface IProductService {

    List<Product> getAllProducts();
    List<Product> getByCategory(Category category);

}
