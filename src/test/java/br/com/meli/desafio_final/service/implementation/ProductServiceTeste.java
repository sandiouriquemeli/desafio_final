package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.model.entity.Product;
import br.com.meli.desafio_final.model.enums.Category;
import br.com.meli.desafio_final.repository.ProductRepository;
import br.com.meli.desafio_final.util.ProductUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
// TODO: RENOMEAR ARQUIVO E CLASSE (ÚLTIMA PALAVRA), PARA MANTER O PADRÃO - REMOVER O "E" DA PALAVRA "TEST"
public class ProductServiceTeste {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    // TODO: REMOVER A PALAVRA "TEST" DOS NOMES DOS MÉTODOS, POIS A MAIORIA NÃO POSSUI
    // TODO: ADICIONAR @DisplayName() AOS TESTES QUE NÃO O POSSUI

    @Test
    public void testGetAllProducts() {

        BDDMockito.when(productRepository.findAll())
                .thenReturn(ProductUtils.productList());

        List<Product> productListResponse = productService.findAllProducts();

        assertThat(productListResponse.size()).isEqualTo(4);
        assertThat(productListResponse).isNotNull();
    }

    @Test
    public void testIfGetAllProductsThrowsException() {
        Exception exceptionResponse = null;
        BDDMockito.when(productRepository.findAll())
                .thenReturn(new ArrayList<>());
        try {
            productService.findAllProducts();
        } catch (Exception exception) {
            exceptionResponse = exception;
        }

        assertThat(exceptionResponse.getMessage()).isEqualTo("Lista de produtos não encontrada");
    }

    @Test
    public void testGetProductByCategory() {
        BDDMockito.when(productRepository.findByCategory(Category.FRESH))
                .thenReturn(ProductUtils.productListFresh());

        List<Product> productListResponse = productService.findByCategory(Category.FRESH);

        assertThat(productListResponse.size()).isEqualTo(2);
        assertThat(productListResponse.get(0).getCategory()).isEqualTo(Category.FRESH);
        assertThat(productListResponse.get(1).getCategory()).isEqualTo(Category.FRESH);
    }

    @Test
    public void testIfGetProductByCategoryThrowsException() {
        Exception exceptionResponse = null;

        BDDMockito.when(productRepository.findByCategory(Category.FRESH))
                .thenReturn(new ArrayList<>());

        try {
            productService.findByCategory(Category.FRESH);
        }catch (Exception exception) {
            exceptionResponse = exception;
        }
        assertThat(exceptionResponse.getMessage()).isEqualTo("Nenhum produto com essa categoria foi encontrado");


    }
}
