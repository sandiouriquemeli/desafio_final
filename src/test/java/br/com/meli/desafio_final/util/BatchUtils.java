package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.model.entity.Batch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
                .batchNumber(2L)
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

    public static Batch newBatch3ToSave() {
        return Batch.builder()
                .batchNumber(3L)
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

    public static Batch newBatch4ToSave() {
        return Batch.builder()
                .batchNumber(4L)
                .adsense(AdsenseUtils.newAdsense1ToSave())
                .currentTemperature(10)
                .minimumTemperature(10F)
                .currentQuantity(0)
                .initialQuantity(0)
                .manufacturingDate(LocalDate.of(2022, 05, 03))
                .manufacturingTime(LocalDateTime.of(LocalDate.of(2022, 05, 03), LocalTime.of(10, 15, 33)))
                .dueDate(LocalDate.of(2022,12, 8))
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

    public static List<Batch> BatchList(){
        ArrayList<Batch> batchlist = new ArrayList<>();
        batchlist.add(newBatch1ToSave());
        return batchlist;
    }

}
