package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.model.entity.Batch;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BatchUtils {
    public static Batch newBatch1ToSave() {
        return Batch.builder()
                .batchNumber(1L)
                .adsense(null)
                .currentTemperature(10)
                .minimumTemperature(10F)
                .currentQuantity(100)
                .initialQuantity(100)
                .manufacturingDate(LocalDate.now())
                .manufacturingTime(LocalDateTime.now())
                .dueDate(LocalDate.of(2022,12, 8))
                .inBoundOrder(null)
                .build();
    }
}
