package br.com.meli.desafio_final.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Batch {

    @Id
    @NotNull(message = "O número do lote precisa ser informado.")
    private Long batchNumber;

    @ManyToOne
    @JoinColumn(name = "adsense_id")
    @JsonIgnoreProperties("batch")
    private Adsense adsense;

    @NotNull(message = "A temperatura do produto precisa ser informada.")
    private float currentTemperature;

    @NotNull(message = "A temperatura mínima do produto precisa ser informada.")
    private float minimumTemperature;

    @NotNull(message = "A quantidade do produto precissa ser informada.")
    private int currentQuantity;

    @NotNull(message = "A quantidade inicial do produto precisa ser informada.")
    private int initialQuantity;

    @NotNull(message = "A data de fabricação do produto precisa ser informada.")
    private LocalDate manufacturingDate;

    @NotNull(message = "A hora de fabricação do produto precisa ser informada.")
    private LocalDateTime manufacturingTime;

    @NotNull(message = "A data de validade do produto precisa ser informada.")
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "inBoundOrder_id")
    @JsonIgnoreProperties("batch")
    private InBoundOrder inBoundOrder;

}
