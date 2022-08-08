package br.com.meli.desafio_final.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "adsense_id")
    @JsonIgnoreProperties("batch")
    private Adsense adsense;

    private float currentTemperature;
    private float minimunTemperature;
    private int currentQuantity;
    private int initialQuantity;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingTime;
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "inBoundOrder_id")
    @JsonIgnoreProperties("batch")
    private InBoundOrder inBoundOrder;
}
