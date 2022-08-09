package br.com.meli.desafio_final.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter @Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seller{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
