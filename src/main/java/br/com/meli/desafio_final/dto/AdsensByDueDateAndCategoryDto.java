package br.com.meli.desafio_final.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdsensByDueDateAndCategoryDto {
    BigInteger batchNumber;
    BigInteger adsenseId;
    Integer quantity;
    Date dueDate;
    String category;
    BigInteger sectionId;

    public AdsensByDueDateAndCategoryDto(Object batchNumber, Object adsenseId, Object quantity, Object dueDate, Object category, Object sectionId) {
        this.batchNumber = (BigInteger) batchNumber;
        this.adsenseId = (BigInteger) adsenseId;
        this.quantity = (Integer) quantity;
        this.dueDate = (Date) dueDate;
        this.category = (String) category;
        this.sectionId = (BigInteger) sectionId;
    }
}
