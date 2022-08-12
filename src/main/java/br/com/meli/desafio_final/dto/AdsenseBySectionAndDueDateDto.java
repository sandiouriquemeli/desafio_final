package br.com.meli.desafio_final.dto;

import lombok.*;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdsenseBySectionAndDueDateDto {
    BigInteger batchNumber;
    BigInteger adsenseId;
    Integer quantity;
    Date dueDate;

    public AdsenseBySectionAndDueDateDto(Object batchNumber, Object adsenseId, Object quantity, Object dueDate) {

        this.batchNumber = (BigInteger) batchNumber;
        this.adsenseId = (BigInteger) adsenseId;
        this.quantity = (Integer) quantity;
        this.dueDate = (Date) dueDate;
    }
}
