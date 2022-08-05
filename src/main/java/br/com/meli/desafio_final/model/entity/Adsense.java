package br.com.meli.desafio_final.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter @Getter
public class Adsense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Seller seller;
    Product product;
    float price;

    @OneToMany(mappedBy = "adsense")
    @JsonIgnoreProperties("adsense")
    private List<Batch> batchList;
}
