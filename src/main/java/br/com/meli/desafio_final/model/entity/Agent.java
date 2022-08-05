package br.com.meli.desafio_final.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Agent extends Person{
    @OneToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
}
