package br.com.meli.desafio_final.dto;

import br.com.meli.desafio_final.model.entity.Batch;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BatchDto implements Comparable<BatchDto>{
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

    @Override
    public int compareTo(BatchDto batch) {
        if (this.batchNumber < batch.getBatchNumber()) return -1;
        if (this.batchNumber > batch.getBatchNumber()) return 1;
        if (this.currentQuantity < batch.getCurrentQuantity()) return -1;
        if (this.currentQuantity > batch.getCurrentQuantity()) return 1;
        if (this.dueDate.isBefore(batch.getDueDate())) return -1;
        if (this.dueDate.isAfter(batch.getDueDate())) return 1;
        return 0;
    }
}