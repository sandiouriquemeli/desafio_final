package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.dto.AdsenseByWarehouseDto;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
class AdsenseByWarehouseDtoFake implements AdsenseByWarehouseDto {
    private Long warehouse_id;
    private Integer quantity;

    @Override
    public Integer getQuantity() {
        return null;
    }

    @Override
    public Long getWarehouse_id() {
        return null;
    }
}

public class AdsenseByWarehouseDtoUtils {

    public static List<AdsenseByWarehouseDto> AdsenseByWarehouseDtoListDto() {
        List<AdsenseByWarehouseDto> listDto = new ArrayList<>();
        listDto.add(new AdsenseByWarehouseDtoFake( 1L, 30));
        listDto.add(new AdsenseByWarehouseDtoFake( 2L, 30));
        listDto.add(new AdsenseByWarehouseDtoFake( 3L,30));
        listDto.add(new AdsenseByWarehouseDtoFake( 4L, 30));
        return listDto;
    }

}
