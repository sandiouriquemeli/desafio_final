package br.com.meli.desafio_final.dto;

import br.com.meli.desafio_final.model.entity.Section;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchesByProductDto {
    private Long sectionId;
    private Long warehouseId;
    private Long productId;
    private List<BatchDto> batchStock;

    public BatchesByProductDto(Section section, Long productId, List<BatchDto> batchStock) {
        this.sectionId = section.getId();
        this.warehouseId = section.getWarehouse().getId();
        this.productId = productId;
        this.batchStock = batchStock;
    }
}
