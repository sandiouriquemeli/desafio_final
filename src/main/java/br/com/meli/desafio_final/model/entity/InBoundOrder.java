package br.com.meli.desafio_final.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter @Getter
public class InBoundOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @NotBlank(message = "O 'id' do setor precisa ser informado.")
    @JoinColumn(name = "section_id")
    private Section section;

    LocalDate date = LocalDate.now();

    @OneToMany(mappedBy = "inBoundOrder")
    @JsonIgnoreProperties("inBoundOrder")
    @NotBlank(message = "Uma lista precisa ser passada na chave 'BatchStock")
    private List<Batch> batchStock;

}
