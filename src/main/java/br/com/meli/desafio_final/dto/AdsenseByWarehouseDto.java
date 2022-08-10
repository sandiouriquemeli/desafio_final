package br.com.meli.desafio_final.dto;

import br.com.meli.desafio_final.model.entity.Batch;
import br.com.meli.desafio_final.model.entity.Section;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdsenseByWarehouseDto {
    BigDecimal quantity;
    BigInteger warehouse_id;

    public AdsenseByWarehouseDto(Object warehouse, Object currentQantity) {
        System.out.println(warehouse.getClass());
        this.quantity = (BigDecimal) warehouse;
        this.warehouse_id = (BigInteger) currentQantity;
    }
}
