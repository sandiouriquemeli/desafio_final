package br.com.meli.desafio_final.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter @Getter
public class InBoundOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "section_id")
    private Section section;

 //   @Column(name = "date")
    LocalDate date = LocalDate.now();

    @OneToMany(mappedBy = "inBoundOrder")
    @JsonIgnoreProperties("inBoundOrder")
    private List<Batch> batchStock;

   // @PrePersist
 //   protected void onCreate() {
 //       this.date = LocalDate.now();
  //  }
}
