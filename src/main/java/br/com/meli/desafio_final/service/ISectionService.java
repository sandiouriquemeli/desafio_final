package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Section;
import br.com.meli.desafio_final.model.enums.Category;

import java.util.List;

public interface ISectionService {
    Section update(Section section);
    void setAndUpdateCapacity(double batchVolumen, Section section);
    List<Section> findByCategory(Category category);
}
