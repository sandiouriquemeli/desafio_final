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

        batchList.add(BatchUtils.newBatch1ToSave());

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
