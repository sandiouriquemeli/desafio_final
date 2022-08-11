package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.dto.BatchDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BatchDtoUtils {
    public static BatchDto newBatchDto1ToSave() {
        return BatchDto.builder()
                .batchNumber(1L)
                .currentQuantity(100)
                .dueDate(LocalDate.of(2022,12, 8))
                .build();
    }

    public static BatchDto newBatchDto2ToSave() {
        return BatchDto.builder()
                .batchNumber(2L)
                .currentQuantity(100)
                .dueDate(LocalDate.of(2022,5, 12))
                .build();
    }

    public static BatchDto newBatchDto3ToSave() {
        return BatchDto.builder()
                .batchNumber(3L)
                .currentQuantity(120)
                .dueDate(LocalDate.of(2022,10, 28))
                .build();
    }

    public static List<BatchDto> generateBatchDtoList() {
        List<BatchDto> list = new ArrayList<>();
        list.add(newBatchDto1ToSave());
        list.add(newBatchDto2ToSave());
        list.add(newBatchDto3ToSave());
        return list;
    }
}
