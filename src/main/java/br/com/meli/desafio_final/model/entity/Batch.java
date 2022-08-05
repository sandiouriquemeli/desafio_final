package br.com.meli.desafio_final.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Batch {
    Long id;
    Adsense adsense;
    float currentTemperature;
    float minimunTemperature;
    int currentQuantity;
    int initialQuantity;
    LocalDate manufacturingDate;
    LocalDateTime manufacturingTime;
    LocalDate dueDate;
}
