package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.model.entity.Adsense;

import java.util.ArrayList;
import java.util.List;

public class AdsenseUtils {

    public static Adsense newAdsense1ToSave() {
        return Adsense.builder()
                .id(1L)
                .price(20.00)
                .seller(SellerUtils.newSeller1ToSave())
                .product(ProductUtils.newProduct1ToSave())
                .build();
    }

    public static Adsense newAdsense2ToSave() {
        return Adsense.builder()
                .id(2L)
                .price(30.00)
                .seller(SellerUtils.newSeller2ToSave())
                .product(ProductUtils.newProduct2ToSave())
                .build();
    }

    public static Adsense newAdsense3ToSave() {
        return Adsense.builder()
                .id(3L)
                .price(25.00)
                .seller(SellerUtils.newSeller1ToSave())
                .product(ProductUtils.newProduct3ToSave())
                .build();
    }

    public static List<Adsense> generateAdsenseList() {
        List<Adsense> adsenseList = new ArrayList<>();
        adsenseList.add(newAdsense1ToSave());
        adsenseList.add(newAdsense2ToSave());
        adsenseList.add(newAdsense3ToSave());
        return adsenseList;
    }
}
