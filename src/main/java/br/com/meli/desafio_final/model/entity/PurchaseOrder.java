package br.com.meli.desafio_final.model.entity;

import br.com.meli.desafio_final.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.internal.util.stereotypes.Lazy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    @JsonIgnoreProperties("purchaseOrder")
    private Buyer buyer;
    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("purchaseOrder")
    private List<Item> itemList;
}
