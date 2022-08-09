package br.com.meli.desafio_final.dto;

import br.com.meli.desafio_final.model.entity.Batch;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
public class BatchDto {
    private Long batchNumber;
    private int currentQuantity;
    private LocalDate dueDate;

    public BatchDto(Batch batch) {
        this.batchNumber = batch.getBatchNumber();
        this.currentQuantity = batch.getCurrentQuantity();
        this.dueDate = batch.getDueDate();
    }

    public static List<BatchDto> convertDto(List<Batch> batchList) {
        return batchList.stream().map(BatchDto::new).collect(Collectors.toList());
    }
}
