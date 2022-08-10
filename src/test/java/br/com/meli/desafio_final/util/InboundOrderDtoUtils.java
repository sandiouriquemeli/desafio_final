package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.dto.InBoundOrderDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class InboundOrderDtoUtils {

    public static InBoundOrderDto newInBoundOrderDto1() {
        InBoundOrderDto inBoundOrderDto1 = new InBoundOrderDto();

         inBoundOrderDto1.setBatchNumber(1L);
        inBoundOrderDto1.setAdsense_id(AdsenseUtils.newAdsense1ToSave().getId());
        inBoundOrderDto1.setCurrentTemperature(10);
        inBoundOrderDto1.setMinimumTemperature(10F);
        inBoundOrderDto1.setCurrentQuantity(100);
        inBoundOrderDto1.setInitialQuantity(100);
        inBoundOrderDto1.setManufacturingDate(LocalDate.of(2022, 05, 03));
        inBoundOrderDto1.setManufacturingTime(LocalDateTime.of(LocalDate.of(2022, 05, 03), LocalTime.of(10, 15, 33)));
        inBoundOrderDto1.setDueDate(LocalDate.of(2022,12, 8));

        return inBoundOrderDto1;
    }

    public static List<InBoundOrderDto> inBoundOrderDtoList() {
        ArrayList<InBoundOrderDto> inBoundOrderDtoList = new ArrayList<>();
        inBoundOrderDtoList.add(newInBoundOrderDto1());
        return inBoundOrderDtoList;
    }
}
