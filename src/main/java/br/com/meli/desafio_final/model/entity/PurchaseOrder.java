package br.com.meli.desafio_final.model.entity;

import br.com.meli.desafio_final.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter @Getter
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Setando Enum como String
    @Enumerated(EnumType.STRING)
    private Status status;

    //Removendo set LocalDate.now() -> não está funcionando
    private LocalDate date;

    @ManyToOne()
    @JoinColumn(name = "buyer_id")
    @JsonIgnoreProperties("purchaseOrder")
    private Buyer buyer;


    //Adicionando Cascade para persistir ids item no banco
    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("purchaseOrder")
    private List<Item> itemList;

}
