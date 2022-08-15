package br.com.meli.desafio_final.dto;
import java.util.Date;


public interface AdsensByDueDateAndCategoryDto {
    Long getBatch_number();
    Long getAdsense_id();
    Integer getQuantity();
    Date getDue_date();
    String getCategory();
    Long getSection_id();
}



