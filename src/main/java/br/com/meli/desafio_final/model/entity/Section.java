package br.com.meli.desafio_final.model.entity;

import br.com.meli.desafio_final.model.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    private Double totalCapacity;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Double usedCapacity;

}
