package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.model.entity.Batch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
                .dueDate(LocalDate.of(2023,12, 10))
                .inBoundOrder(null)
                .build();
    }

    public static Batch newBatch2ToSave() {
        return Batch.builder()
                .batchNumber(2L)
                .adsense(AdsenseUtils.newAdsense1ToSave())
                .currentTemperature(10)
                .minimumTemperature(10F)
                .currentQuantity(100)
                .initialQuantity(100)
                .manufacturingDate(LocalDate.now())
                .manufacturingTime(LocalDateTime.now())
                .dueDate(LocalDate.of(2023,12, 8))
                .inBoundOrder(null)
                .build();
    }

    public static Batch newBatch3ToSave() {
        return Batch.builder()
                .batchNumber(2L)
                .adsense(AdsenseUtils.newAdsense1ToSave())
                .currentTemperature(10)
                .minimumTemperature(10F)
                .currentQuantity(100)
                .initialQuantity(100)
                .manufacturingDate(LocalDate.now())
                .manufacturingTime(LocalDateTime.now())
                .dueDate(LocalDate.of(2022,8, 10))
                .inBoundOrder(null)
                .build();
    }

    public static Batch newBatch4ToSave() {
        return Batch.builder()
                .batchNumber(2L)
                .adsense(AdsenseUtils.newAdsense1ToSave())
                .currentTemperature(10)
                .minimumTemperature(10F)
                .currentQuantity(0)
                .initialQuantity(0)
                .manufacturingDate(LocalDate.now())
                .manufacturingTime(LocalDateTime.now())
                .dueDate(LocalDate.of(2023,12, 10))
                .inBoundOrder(null)
                .build();
    }

    public static List<Batch> genetadBatchList(){
        List<Batch> batchList = new ArrayList<>();
        batchList.add(newBatch1ToSave());
        batchList.add(newBatch2ToSave());
        return batchList;
    }

    public static List<Batch> genetadBatchListDataFail() {
        List<Batch> batchList = new ArrayList<>();
        batchList.add(newBatch3ToSave());
        return batchList;
    }

    public static List<Batch> genetadBatchListBatchFail() {
        List<Batch> batchList = new ArrayList<>();
        batchList.add(newBatch4ToSave());
        return batchList;
    }
}
