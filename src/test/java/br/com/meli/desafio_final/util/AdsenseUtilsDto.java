package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.dto.AdsenseDto;
import br.com.meli.desafio_final.dto.AdsenseIdDto;
import br.com.meli.desafio_final.model.entity.Adsense;

import java.util.ArrayList;
import java.util.List;

public class AdsenseUtilsDto {
    public static AdsenseIdDto newAdsenseDtoToSave() {
        return AdsenseIdDto.builder()
                .id(3L)
                .build();
    }

    public static List<AdsenseIdDto> generateAdsenseIdDtoList() {
        List<AdsenseIdDto> adsenseIdDtoList = new ArrayList<>();
        adsenseIdDtoList.add(newAdsenseDtoToSave());
        return adsenseIdDtoList;
    }

    public static AdsenseDto newAdsense1ToSave() {
        return AdsenseDto.builder()
                .price(20.00)
                .seller(SellerUtils.newSeller1ToSave())
                .product(ProductUtils.newProduct1ToSave())
                .build();
    }

    public static AdsenseDto newAdsense2ToSave() {
        return AdsenseDto.builder()
                .price(30.00)
                .seller(SellerUtils.newSeller2ToSave())
                .product(ProductUtils.newProduct2ToSave())
                .build();
    }


    public static List<AdsenseDto> generateAdsenseDtoList() {
        List<AdsenseDto> adsenseList = new ArrayList<>();
        adsenseList.add(newAdsense1ToSave());
        adsenseList.add(newAdsense2ToSave());
        return adsenseList;
    }
}
