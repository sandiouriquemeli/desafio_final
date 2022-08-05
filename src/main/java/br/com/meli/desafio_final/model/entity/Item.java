package br.com.meli.desafio_final.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter @Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double currentPrice;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "adsense_id")
    @JsonIgnoreProperties("item")
    private Adsense adsense;
}
