package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.model.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {

    public static Item newItem1ToSave() {
        return Item.builder()
                .id(1L)
                .currentPrice(0.50)
                .quantity(3)
                .adsense(AdsenseUtils.newAdsense1ToSave())
                .build();
    }
    public static Item newItem2ToSave() {
        return Item.builder()
                .id(2L)
                .currentPrice(0.80)
                .quantity(8)
                .adsense(AdsenseUtils.newAdsense1ToSave())
                .build();
    }

    public static List<Item> generatedItemList(){
        List<Item> itemList = new ArrayList<>();
        itemList.add(newItem1ToSave());
        itemList.add(newItem2ToSave());
        return itemList;
    }
}
