package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.dto.BatchesByProductDto;
import br.com.meli.desafio_final.model.entity.Product;
import br.com.meli.desafio_final.model.enums.Category;
import br.com.meli.desafio_final.service.implementation.ProductService;
import br.com.meli.desafio_final.util.ProductUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;


    // TODO: ADICIONAR @DisplayName() AOS TESTES QUE NÃO O POSSUI

    @Test
    public void testGetAllProducts() {
        BDDMockito.when(productService.findAllProducts())
                .thenReturn(ProductUtils.productList());

        ResponseEntity<List<Product>> productResponse = productController.getAll();
        assertThat(productResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(productResponse.getBody()).isNotNull();
        assertThat(productResponse.getBody().size()).isNotNull().isPositive().isEqualTo(4);
    }

    @Test
    public void testProductsByCategory() {
        BDDMockito.when(productService.findByCategory(Category.FRESH))
                .thenReturn(ProductUtils.productListFresh());

        ResponseEntity<List<Product>> productResponse = productController.getByCategory(Category.FRESH);
        assertThat(productResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(productResponse.getBody()).isNotNull();
        assertThat(productResponse.getBody().size()).isNotNull().isPositive().isEqualTo(2);
    }

    @Test
    public void testFindBatchByProduct() {
        BatchesByProductDto batchesByProductDto = ProductUtils.bachesByProduct();
        BDDMockito.when(productService.findBatchByProduct(1L, null))
                .thenReturn(batchesByProductDto);

        ResponseEntity<BatchesByProductDto> productResponse = productController.findBatchByProduct(1L, null);

        assertThat(productResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(productResponse.getBody()).isNotNull();
        assertThat(productResponse.getBody()).isEqualTo(batchesByProductDto);
    }
}
