package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.model.entity.Batch;
import br.com.meli.desafio_final.model.entity.InBoundOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestAdsenseGenerator {

    public static Adsense getAdsenseWithId() {
        Adsense adsense = new Adsense();
        InBoundOrder inBoundOrder = new InBoundOrder();
        List<Batch> batchList = new ArrayList<>();

        Batch batch1 = new Batch(
            1L,
            adsense,
            10,
            5,
            100,
            100,
            LocalDate.now(),
            LocalDateTime.now(),
            LocalDate.now(),
            inBoundOrder);

        batchList.add(batch1);

        return Adsense.builder()
            .id(1L)
            .seller(null)
            .product(null)
            .price(5.99)
            .batchList(batchList)
            .itemList(null)
            .build();
    }
}
