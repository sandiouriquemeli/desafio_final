package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.dto.InBoundOrderDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class InboundOrderDTOUtils {
    public static InBoundOrderDto newInboudOrder(){
        return InBoundOrderDto.builder()
                .batchNumber(1L)
                .adsense_id(AdsenseUtils.newAdsense1ToSave().getId())
                .minimumTemperature(10F)
                .currentQuantity(100)
                .initialQuantity(100)
                .manufacturingDate(LocalDate.of(2022, 05, 03))
                .manufacturingTime(LocalDateTime.of(LocalDate.of(2022, 05, 03), LocalTime.of(10, 15, 33)))
                .dueDate(LocalDate.of(2022,12, 8))
                .build();
    }
}
