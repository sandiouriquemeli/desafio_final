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
    @JoinColumn(name = "section_id")
    private Section section;

    private LocalDate date = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "agent_id")
    @JsonIgnoreProperties("inBoundOrder")
    private Agent agent;

    @OneToMany(mappedBy = "inBoundOrder", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("inBoundOrder")
    private List<Batch> batchStock;
}
