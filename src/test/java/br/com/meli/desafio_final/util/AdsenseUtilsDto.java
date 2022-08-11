package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.dto.AdsenseIdDto;

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
}
