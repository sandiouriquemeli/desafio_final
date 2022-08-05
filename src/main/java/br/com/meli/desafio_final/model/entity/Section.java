package br.com.meli.desafio_final.model.entity;

import br.com.meli.desafio_final.model.enums.Type;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;

@Entity
@Setter @Getter
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @DecimalMax(value = "300")
    private Double capacity;

    private Type type;

}
