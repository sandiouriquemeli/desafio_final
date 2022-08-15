package br.com.meli.desafio_final.dto;

import lombok.*;
import java.math.BigInteger;
import java.util.Date;

public interface AdsenseBySectionAndDueDateDto {
    Long getBatch_number();
    Long getAdsense_id();
    Integer getQuantity();
    Date getDue_date();

}
