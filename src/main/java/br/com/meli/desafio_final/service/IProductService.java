package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Product;

public interface IProductService {
    Product findProductById(long id);
}
