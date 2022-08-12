package br.com.meli.desafio_final.repository;

import br.com.meli.desafio_final.model.entity.Section;
import br.com.meli.desafio_final.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findByCategory(Category category);
}