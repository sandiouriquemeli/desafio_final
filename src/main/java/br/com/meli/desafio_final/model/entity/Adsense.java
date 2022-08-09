package br.com.meli.desafio_final.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Setter @Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Adsense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "seller_id")
    @JsonIgnoreProperties("adsense")
    @NotBlank(message = "O 'id' do seller precisa ser informado.")
    private Seller seller;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("adsense")
    @NotBlank(message = "O 'id' do produto precisa ser informado.")
    private Product product;

    @NotBlank(message = "O Preço do produto precisa ser informado.")
    private double price;

    @OneToMany(mappedBy = "adsense")
    @JsonIgnoreProperties("adsense")
    private List<Batch> batchList;

    @OneToMany(mappedBy = "adsense")
    @JsonIgnoreProperties("adsense")
    private List<Item> itemList;
}
