package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.dto.BatchesByProductDto;
import br.com.meli.desafio_final.model.entity.Product;
import br.com.meli.desafio_final.model.enums.Category;

import java.util.ArrayList;
import java.util.List;

public class ProductUtils {

    public static Product newProduct1ToSave() {
        return Product.builder()
                .id(1L)
                .name("Alface")
                .volumen(15.00)
                .category(Category.FRESH)
                .build();
    }

    public static Product newProduct2ToSave() {
        return Product.builder()
                .id(2L)
                .name("Tomate")
                .volumen(18.00)
                .category(Category.FRESH)
                .build();
    }

    public static Product newProduct3ToSave() {
        return Product.builder()
                .id(3L)
                .name("Frango")
                .volumen(40.00)
                .category(Category.FROZEN)
                .build();
    }

    public static Product newProduct4ToSave() {
        return Product.builder()
                .id(4L)
                .name("Manteiga")
                .volumen(5.00)
                .category(Category.REFRIGERATED)
                .build();
    }

    public static List<Product> productList() {
        List<Product> productList = new ArrayList<>();
        productList.add(newProduct1ToSave());
        productList.add(newProduct2ToSave());
        productList.add(newProduct3ToSave());
        productList.add(newProduct4ToSave());

        return productList;
    }

    public static List<Product> productListFresh() {
        List<Product> productList = new ArrayList<>();
        productList.add(newProduct1ToSave());
        productList.add(newProduct2ToSave());

        return productList;
    }

    public static BatchesByProductDto bachesByProduct() {
        return BatchesByProductDto.builder()
                .sectionId(3L)
                .warehouseId(1L)
                .productId(3L)
                .batchStock(BatchDtoUtils.generateBatchDtoList())
                .build();
    }
}
