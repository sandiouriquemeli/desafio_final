package br.com.meli.desafio_final.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.internal.util.stereotypes.Lazy;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double currentPrice;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "purchaseOrder_id")
    @JsonIgnoreProperties("item")
    private PurchaseOrder purchaseOrder;


    @ManyToOne
    @JoinColumn(name = "adsense_id")
    @JsonIgnoreProperties("item")
    private Adsense adsense;
}
