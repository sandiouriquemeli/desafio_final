package br.com.meli.desafio_final.dto;

import br.com.meli.desafio_final.model.entity.Batch;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class InBoundOrderDto {

    private Long batchNumber;
    private Long adsense_id;
    private float currentTemperature;
    private float minimumTemperature;
    private int initialQuantity;
    private int currentQuantity;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingTime;
    private LocalDate dueDate;

    public InBoundOrderDto(Batch batch) {
        setBatchNumber(batch.getBatchNumber());
        setAdsense_id(batch.getAdsense().getId());
        setCurrentTemperature(batch.getCurrentTemperature());
        setMinimumTemperature(batch.getMinimumTemperature());
        setInitialQuantity(batch.getInitialQuantity());
        setCurrentQuantity(batch.getCurrentQuantity());
        setManufacturingDate(batch.getManufacturingDate());
        setManufacturingTime(batch.getManufacturingTime());
        setDueDate(batch.getDueDate());
    }
}
