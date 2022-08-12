package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.exception.NotAcceptable;
import br.com.meli.desafio_final.exception.NotFound;
import br.com.meli.desafio_final.model.entity.Section;
import br.com.meli.desafio_final.model.enums.Category;
import br.com.meli.desafio_final.repository.SectionRepository;
import br.com.meli.desafio_final.service.ISectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService implements ISectionService {

    @Autowired
    SectionRepository sectionRepository;

    @Override
    public Section update(Section section) {
        return sectionRepository.save(section);
    }

    /**
     * Nesse método estamos validando capacidade de um setor
     * @param batchVolumen
     * @param section
     */
    public void setAndUpdateCapacity(double batchVolumen, Section section){
        if (section.getUsedCapacity() >= batchVolumen){
            section.setUsedCapacity(section.getUsedCapacity() - batchVolumen);
            sectionRepository.save(section);
        }else{
            throw new NotAcceptable("Setor sem espaço para armazer o lote");
        }
    }

    /**
     * Nesse metodo estamos retornando uma lista setor por categoria
     * @param category
     * @return
     */
    @Override
    public List<Section> findByCategory(Category category) {
        try {
            return sectionRepository.findByCategory(category);
        }catch (Exception e){
            throw new NotFound("Categoria não localizada.");
        }
    }
}
