package br.com.meli.desafio_final.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface AdsenseByWarehouseDto {
    Integer getQuantity();
    Long getWarehouse_id();
}
