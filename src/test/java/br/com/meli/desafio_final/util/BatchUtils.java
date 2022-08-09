package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.model.entity.Batch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BatchUtils {
    public static Batch newBatch1ToSave() {
        return Batch.builder()
                .batchNumber(1L)
                .adsense(AdsenseUtils.newAdsense1ToSave())
                .currentTemperature(10)
                .minimumTemperature(10F)
                .currentQuantity(100)
                .initialQuantity(100)
                .manufacturingDate(LocalDate.of(2022, 05, 03))
                .manufacturingTime(LocalDateTime.of(LocalDate.of(2022, 05, 03), LocalTime.of(10, 15, 33)))
                .dueDate(LocalDate.of(2022,12, 8))
                .inBoundOrder(null)
                .build();
    }

    public static Batch newBatch2ToSave() {
        return Batch.builder()
                .batchNumber(1L)
                .adsense(AdsenseUtils.newAdsense1ToSave())
                .currentTemperature(10)
                .minimumTemperature(10F)
                .currentQuantity(100)
                .initialQuantity(100)
                .manufacturingDate(LocalDate.of(2022, 05, 03))
                .manufacturingTime(LocalDateTime.of(LocalDate.of(2022, 05, 03), LocalTime.of(10, 15, 33)))
                .dueDate(LocalDate.of(2022,12, 8))
                .inBoundOrder(null)
                .build();
    }
    public static List<Batch> BatchList(){
        ArrayList<Batch> batchlist = new ArrayList<>();
        batchlist.add(newBatch1ToSave());
        return batchlist;
    }

}

//TODO: mudamos linha 12 de null para AdsenseUtils