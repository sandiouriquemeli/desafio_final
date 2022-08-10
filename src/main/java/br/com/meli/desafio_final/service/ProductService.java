package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.dto.AdsenseIdDto;
import br.com.meli.desafio_final.dto.BatchDto;
import br.com.meli.desafio_final.dto.BatchesByProductDto;
import br.com.meli.desafio_final.exception.NotFound;
import br.com.meli.desafio_final.model.entity.Product;
import br.com.meli.desafio_final.model.entity.Section;
import br.com.meli.desafio_final.model.enums.Category;
import br.com.meli.desafio_final.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private AdsenseService adsenseService;

    @Autowired
    private BatchService batchService;

    @Autowired
    private SectionService sectionService;

    @Override
    public List<Product> findAllProducts() {
        List<Product> products = repository.findAll();

        if (products.size() == 0) throw new NotFound("Lista de produtos não encontrada");

        return products;
    }

    @Override
    public List<Product> findByCategory(Category category) {
        List<Product> response = repository.findByCategory(category);
        if (response.size() == 0) {
            throw new RuntimeException("Nenhum produto com essa categoria foi encontrado");
        }
        return response;
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NotFound("Produto inexistente");
        });
    }

    public BatchesByProductDto findBatchByProduct(Long id) {
        List<BatchDto> result = new ArrayList<>();
        Product product = findById(id);
        Section section = sectionService.findByCategory(product.getCategory());
        List<AdsenseIdDto> adsenseList = adsenseService.findByProductId(product.getId());
        List<List<BatchDto>> batchDtoList = adsenseList.stream().map(adsenseId -> batchService.findAllByAdsenseId(adsenseId.getId()))
                .collect(Collectors.toList());
        batchDtoList.stream().forEach(batchDtos -> result.addAll(batchDtos));
        return new BatchesByProductDto(section, product.getId(), result);
    }


}
