package br.com.meli.desafio_final.dto;

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
        this.quantity = (BigDecimal) warehouse;
        this.warehouse_id = (BigInteger) currentQantity;
    }
}
