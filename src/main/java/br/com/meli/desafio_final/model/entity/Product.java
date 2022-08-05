package br.com.meli.desafio_final.model.entity;

import br.com.meli.desafio_final.model.enums.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter @Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double volumen;

    @Enumerated(EnumType.STRING)
    private Category category;
}
